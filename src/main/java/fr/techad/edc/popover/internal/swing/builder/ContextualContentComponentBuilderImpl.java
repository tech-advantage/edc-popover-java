package fr.techad.edc.popover.internal.swing.builder;

import com.google.common.collect.Sets;
import fr.techad.edc.client.EdcClient;
import fr.techad.edc.client.internal.TranslationConstants;
import fr.techad.edc.client.internal.io.HttpReaderImpl;
import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.client.model.DocumentationItem;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.builder.ContextualContentComponentBuilder;
import fr.techad.edc.popover.internal.swing.components.Popover;
import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.utils.OpenUrlAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

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
    private ErrorBehavior errorBehavior;
    private final JLabel friendlyMessage = new JLabel("Contextual help is coming soon.");
    private final JLabel errorMessage = new JLabel("<html>An error occurred when fetching data ! <br/> Check the brick keys provided to the EdcHelp component.</html>");
    private HttpReaderImpl httpReader;
    private String languageCode = "en";
    private boolean enableRelatedTopics = true;
    private Color popoverSectionTitleColor = Color.BLACK;
    private Font popoverSectionTitleFont = new Font("Dialog", Font.BOLD, 12);
    private boolean enableArticle = true;

    @Inject
    public ContextualContentComponentBuilderImpl(EdcClient edcClient, OpenUrlAction openUrlAction, Popover popover, HttpReaderImpl httpReader) {
        this.edcClient = edcClient;
        this.openUrlAction = openUrlAction;
        this.popover = popover;
        this.httpReader = httpReader;
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
    public ContextualContentComponentBuilder<JComponent> setErrorBehavior(ErrorBehavior errorBehavior) {
        this.errorBehavior = errorBehavior;
        return this;
    }
  
    @Override
    public ContextualContentComponentBuilder<JComponent> enableRelatedTopics(boolean enable) {
        this.enableRelatedTopics = enable;
        LOGGER.debug("Set related topics display: {}", this.enableRelatedTopics);
        return this;
    }
  
    @Override
    public ContextualContentComponentBuilder<JComponent> setPopoverSectionTitleFont(Font fontAttr) {
        this.popoverSectionTitleFont = fontAttr;
        LOGGER.debug("Set popover section title font attributes: {}", this.popoverSectionTitleFont);
        return this;
    }

    @Override
    public ContextualContentComponentBuilder<JComponent> setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        LOGGER.debug("Set language code: {}", this.languageCode);
        return this;
    }
  
    @Override
    public ContextualContentComponentBuilder<JComponent> setPopoverSectionTitleColor(Color titleColor) {
        this.popoverSectionTitleColor = titleColor;
        LOGGER.debug("Set popover section title color: {}", this.popoverSectionTitleColor);
        return this;
    }
  
    @Override
    public ContextualContentComponentBuilder<JComponent> enableArticle(boolean enable) {
        this.enableArticle = enable;
        LOGGER.debug("Set article display: {}", this.enableArticle);
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
            try{
                container.add(getFailure(), BorderLayout.CENTER);
            } catch(InvalidUrlException | IOException err) {
                LOGGER.error("Error during the get failure method creation", err);
            }
        }

        return container;
    }

    private JComponent getHeader() {
        JLabel label = new JLabel();
        if (contextItem != null) {
            label.setText(contextItem.getDescription());
            Font f = label.getFont();
            label.setFont(f.deriveFont((float) (f.getSize() + 1)));
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
            if (this.enableArticle && contextItem.articleSize() != 0) {
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
            if (this.enableRelatedTopics && contextItem.linkSize() != 0) {
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

    public String addChar(String str, String character, int position) {
        return str.substring(0, ++position) + character + str.substring(position);
    }

    private JComponent getFailure() throws InvalidUrlException, IOException {

        Set<String> languagesLists = Sets.newHashSet(languageCode);
        Map<String, Map<String, String>> labelsFromLanguage = httpReader.readLabels(languagesLists);
        //Map<String, Map<String, String>> labelsErrorFromLanguage = httpReader.readErrorsLabel(languagesLists);

        JLabel jLabelError = new JLabel();

        if (errorBehavior == ErrorBehavior.ERROR_SHOWN) {
            String errMessageByKey = labelsErrorFromLanguage.get(languageCode).get("failedData");
            if(errMessageByKey != null){
                errorMessage.setText("<html>" + addChar(errMessageByKey, "<br/>", errMessageByKey.indexOf("!")) + "</html>");
            }
            jLabelError = errorMessage;
        }

        if (errorBehavior == ErrorBehavior.FRIENDLY_MSG) {
            String friendlyMessageByKey = labelsFromLanguage.get(languageCode).get("comingSoon");
            if(friendlyMessageByKey != null){
                friendlyMessage.setText(friendlyMessageByKey);
            }
            jLabelError = friendlyMessage;
        }

        jLabelError.setHorizontalAlignment(SwingConstants.CENTER);
        Font f = jLabelError.getFont();
        jLabelError.setFont(f.deriveFont(f.getStyle() ^ Font.PLAIN));
        jLabelError.setBorder(BorderFactory.createEmptyBorder(6, 10, 0, 10));

        return jLabelError;
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
