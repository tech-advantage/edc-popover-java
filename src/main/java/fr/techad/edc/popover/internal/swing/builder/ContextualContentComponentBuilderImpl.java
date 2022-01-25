package fr.techad.edc.popover.internal.swing.builder;

import fr.techad.edc.client.EdcClient;
import fr.techad.edc.client.internal.TranslationConstants;
import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.client.model.DocumentationItem;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.builder.ContextualContentComponentBuilder;
import fr.techad.edc.popover.internal.swing.components.Popover;
import fr.techad.edc.popover.utils.OpenUrlAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * TECH ADVANTAGE
 * All right reserved
 * Created by cochon on 13/07/2017.
 */
public class ContextualContentComponentBuilderImpl implements ContextualContentComponentBuilder<JComponent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContextualContentComponentBuilderImpl.class);
    private final EdcClient edcClient;
    private final OpenUrlAction openUrlAction;
    private final Popover popover;
    private ContextItem contextItem;
    private Color backgroundColor = Color.WHITE;
    private Color popoverSectionTitleColor = Color.BLACK;
    private Font popoverSectionTitleFont;

    @Inject
    public ContextualContentComponentBuilderImpl(EdcClient edcClient, OpenUrlAction openUrlAction, Popover popover) {
        this.edcClient = edcClient;
        this.openUrlAction = openUrlAction;
        this.popover = popover;
    }

    @Override
    public ContextualContentComponentBuilder<JComponent> setContextItem(ContextItem contextItem) {
        this.contextItem = contextItem;
        LOGGER.debug("Set Context Item: {}", contextItem);
        return this;
    }

    @Override
    public ContextualContentComponentBuilder<JComponent> setBackgroundColor(int rgbColor) {
        this.backgroundColor = new Color(rgbColor);
        LOGGER.debug("Set background color: {}", this.backgroundColor);
        return this;
    }

    @Override
    public ContextualContentComponentBuilder<JComponent> setPopoverSectionTitleFont(Font fontAttr) {
        this.popoverSectionTitleFont = fontAttr;
        LOGGER.debug("Set popover section title font attributes: {}", this.popoverSectionTitleFont);
        return this;
    }

    @Override
    public ContextualContentComponentBuilder<JComponent> setPopoverSectionTitleColor(Color titleColor) {
        this.popoverSectionTitleColor = titleColor;
        LOGGER.debug("Set popover section title color: {}", this.popoverSectionTitleColor);
        return this;
    }

    @Override
    public JComponent build() {
        LOGGER.debug("Build the context item: {}", contextItem != null ? contextItem.getLabel() : "null");

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBackground(this.backgroundColor);
        container.add(getHeader(), BorderLayout.NORTH);
        try {
            container.add(getBody(), BorderLayout.CENTER);
        } catch (InvalidUrlException | IOException e) {
            LOGGER.error("Error during the body creation", e);
            container.add(getFailure(), BorderLayout.CENTER);
        }

        return container;
    }

    private JComponent getHeader() {
        JLabel label;
        if (contextItem != null) {
            label = new JLabel(contextItem.getDescription());
            Font f = label.getFont();
            label.setFont(f.deriveFont((float) (f.getSize() + 1)));
            label.setBorder(BorderFactory.createEmptyBorder(8, 10, 0, 10));
        } else {
            label = new JLabel("Warning");
            Font f = label.getFont();
            label.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD ^ f.getSize() + 1));
            label.setBorder(BorderFactory.createEmptyBorder(8, 10, 0, 10));
        }
        return label;
    }

    private JComponent getBody() throws IOException, InvalidUrlException {
        JPanel body = new JPanel();
        body.setLayout(new BorderLayout());
        body.setBackground(this.backgroundColor);
        if (contextItem != null) {
            LOGGER.debug("article size: {}", contextItem.articleSize());
            if (contextItem.articleSize() != 0) {
                JPanel articlePanel = new JPanel();
                articlePanel.setBackground(this.backgroundColor);
                articlePanel.setLayout(new BoxLayout(articlePanel, BoxLayout.Y_AXIS));
                articlePanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 0, 0));
                JLabel title = new JLabel(getLabel(TranslationConstants.ARTICLES_KEY, contextItem.getLanguageCode(), contextItem.getPublicationId()));
                title.setForeground(popoverSectionTitleColor);
                title.setFont(popoverSectionTitleFont);
                articlePanel.add(title, BorderLayout.NORTH);
                int i = 0;
                for (DocumentationItem documentationItem : contextItem.getArticles()) {
                    LOGGER.debug("Display article: {}", documentationItem);
                    String url = edcClient.getContextWebHelpUrl(contextItem.getMainKey(), contextItem.getSubKey(), i++, contextItem.getLanguageCode());
                    articlePanel.add(createButton(url, documentationItem.getLabel()));
                }
                body.add(articlePanel, BorderLayout.NORTH);
            }
            LOGGER.debug("link size: {}", contextItem.linkSize());
            if (contextItem.linkSize() != 0) {
                JPanel linkPanel = new JPanel();
                linkPanel.setLayout(new BorderLayout());
                linkPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                linkPanel.setBackground(this.backgroundColor);
                JLabel title = new JLabel(getLabel(TranslationConstants.LINKS_KEY, contextItem.getLanguageCode(), contextItem.getPublicationId()));
                title.setForeground(popoverSectionTitleColor);
                title.setFont(popoverSectionTitleFont);
                linkPanel.add(title, BorderLayout.NORTH);
                JPanel linkContentPanel = new JPanel();
                linkContentPanel.setLayout(new BoxLayout(linkContentPanel, BoxLayout.Y_AXIS));
                linkContentPanel.setBackground(this.backgroundColor);
                linkPanel.add(linkContentPanel, BorderLayout.CENTER);
                for (DocumentationItem documentationItem : contextItem.getLinks()) {
                    LOGGER.debug("Display link: {}", documentationItem);
                    String url = edcClient.getDocumentationWebHelpUrl(documentationItem.getId(), contextItem.getLanguageCode(), contextItem.getPublicationId());
                    linkContentPanel.add(createButton(url, documentationItem.getLabel()));
                }
                body.add(linkPanel, BorderLayout.CENTER);
            }
        } else {
            body.add(getFailure());
        }
        return body;
    }

    private JComponent getFailure() {
        return new JLabel("Error to get information about this component");
    }

    private void openUrl(String url) {
        try {
            openUrlAction.openUrl(url);
            popover.setVisible(false);
        } catch (IOException e) {
            LOGGER.error("Error on IO", e);
        } catch (URISyntaxException e) {
            LOGGER.error("Impossible to open the browser with url:{}", url);
        }
    }

    private JButton createButton(String url, String label) {
        JButton button = new JButton();
        button.setText("<HTML><FONT color=\"#000099\"><U>" + label + "</U></FONT></HTML>");
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBackground(this.backgroundColor);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> openUrl(url));
        return button;
    }

    /**
     * Return the translated label for the given key
     * (Need more..., Related topics..)
     *
     * If there's no exported content in the requested language,
     * or the labels translation file was not found,
     * publication default language will be used instead
     *
     * @param key the translation key
     * @param languageCode the language code
     * @param publicationId the publication identifier, for the default language
     * @return the translated label corresponding to the key, in the requested or default language
     */
    private String getLabel(String key, String languageCode, String publicationId) throws IOException, InvalidUrlException {
        LOGGER.debug("Getting label translation for key {}, language code: {}, publication id {}", key, languageCode, publicationId);
        return this.edcClient.getLabel(key ,languageCode, publicationId);
    }
}
