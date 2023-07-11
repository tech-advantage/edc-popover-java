package fr.techad.edc.popover.internal.swing.builder;

import com.google.common.collect.Sets;
import fr.techad.edc.client.EdcClient;

import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.builder.ContextualTitleComponentBuilder;
import static fr.techad.edc.client.model.I18nTranslation.ERROR_TITLE_KEY;
import fr.techad.edc.popover.model.ErrorBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Set;



/**
 * TECH ADVANTAGE
 * All right reserved
 * Created by cochin on 02/04/2019.
 */
public class ContextualTitleComponentBuilderImpl implements ContextualTitleComponentBuilder<JComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextualTitleComponentBuilderImpl.class);
    private final EdcClient edcClient;
    private ContextItem contextItem;
    private boolean showTitle = true;
    private Color backgroundColor = Color.WHITE;
    private ErrorBehavior errorBehavior;
    private String languageCode = "en";
    private final JLabel errorTitle = new JLabel("Error");
    private Color titleColor = Color.BLACK;
    private Font headerTitleFont;

    @Inject
    public ContextualTitleComponentBuilderImpl(EdcClient edcClient) {
        this.edcClient = edcClient;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setContextItem(ContextItem contextItem) {
        this.contextItem = contextItem;
        LOGGER.debug("Set Context Item: {}", contextItem);
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setBackgroundColor(int rgbColor) {
        this.backgroundColor = new Color(rgbColor);
        LOGGER.debug("Set Background Color: {}", backgroundColor);
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setShowTitle(boolean enable) {
        this.showTitle = enable;
        LOGGER.debug("Set Show Title: {}", showTitle);
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setHeaderTitleFont(Font fontAttr) {
        this.headerTitleFont = fontAttr;
        LOGGER.debug("Set Header Title Font Attributes: {}", headerTitleFont);
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setHeaderTitleColor(Color titleColor) {
        this.titleColor = titleColor;
        LOGGER.debug("Set Header Title Color: {}", titleColor);
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setErrorBehavior(ErrorBehavior errorBehavior) {
        this.errorBehavior = errorBehavior;
        LOGGER.debug("Set Error Behavior: {}", errorBehavior);
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        LOGGER.debug("Set Language Code: {}", languageCode);
        return this;
    }

    @Override
    public JComponent build() {
        LOGGER.debug("Build the context item: {}", contextItem != null ? contextItem.getLabel() : "null");

        JPanel container = new JPanel();
        container.setBackground(backgroundColor);
        container.setLayout(new BorderLayout());

        try {
            container.add(getBody(), BorderLayout.CENTER);
        } catch (InvalidUrlException | IOException e) {
            LOGGER.error("Error during the body creation", e);
        }

        return container;
    }

    private JComponent getBody() throws InvalidUrlException, IOException {
        Set<String> languagesCodes = Sets.newHashSet();
        languagesCodes.add(languageCode);
        String errorTitleFromLanguage = getLabel(ERROR_TITLE_KEY.getValue(), languageCode, null);
        JLabel jLabelTitle = new JLabel();

        if(this.showTitle){
            if(contextItem != null){
                jLabelTitle.setText(contextItem.getLabel());
            } else {
                if(errorBehavior == ErrorBehavior.ERROR_SHOWN){
                    if(!errorTitleFromLanguage.isEmpty()){
                        errorTitle.setText(errorTitleFromLanguage);
                    }
                    jLabelTitle = errorTitle;
                }
            }
        } else {
            jLabelTitle.setText("");
        }

        jLabelTitle.setFont(headerTitleFont);
        jLabelTitle.setForeground(titleColor);
        jLabelTitle.setBorder(BorderFactory.createEmptyBorder(8, 10, 0, 10));
        return jLabelTitle;
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
