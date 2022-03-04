package fr.techad.edc.popover.internal.swing.builder;

import com.google.common.collect.Sets;
import fr.techad.edc.client.internal.io.HttpReaderImpl;
import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.client.model.I18NContent;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.builder.ContextualTitleComponentBuilder;
import fr.techad.edc.popover.model.ErrorBehavior;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


/**
 * TECH ADVANTAGE
 * All right reserved
 * Created by cochin on 02/04/2019.
 */
public class ContextualTitleComponentBuilderImpl implements ContextualTitleComponentBuilder<JComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextualTitleComponentBuilderImpl.class);
    private ContextItem contextItem;
    private HttpReaderImpl httpReader;
    private boolean showTitle = true;
    private Color backgroundColor = Color.WHITE;
    private ErrorBehavior errorBehavior;
    private String languageCode = "en";
    private String errorTitle = "Error";
    private Color titleColor = Color.BLACK;
    private Font headerFontAttributes;

    @Inject
    public ContextualTitleComponentBuilderImpl(HttpReaderImpl httpReader) {
        this.httpReader = httpReader;
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
        LOGGER.debug("Set background color: {}", this.backgroundColor);
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setHeaderFontAttributes(Font fontAttributes) {
        this.headerFontAttributes = fontAttributes;
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setHeaderTitleColor(Color titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> enableTitle(boolean showTitle) {
        this.showTitle = showTitle;
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setErrorBehavior(ErrorBehavior errorBehavior) {
        this.errorBehavior = errorBehavior;
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        return this;
    }

    @Override
    public JComponent build() throws InvalidUrlException, IOException {
        LOGGER.debug("Build the context item: {}", contextItem != null ? contextItem.getLabel() : "null");

        JPanel container = new JPanel();
        container.setBackground(backgroundColor);
        container.setLayout(new BorderLayout());

        container.add(getBody(), BorderLayout.CENTER);


        return container;
    }

    private JComponent getBody() throws InvalidUrlException, IOException {
        Set<String> languagesCodes = Sets.newHashSet();
        languagesCodes.add(languageCode);

        String errorTitleFromLanguage = httpReader.readLabel(languagesCodes).getLabel(languageCode, "errorTitle");

        JLabel label;
        String title = StringUtils.EMPTY;
        if (this.showTitle || errorBehavior == ErrorBehavior.ERROR_SHOWN) {
            String errorTitleByKey = errorTitleFromLanguage;
            if(errorTitleByKey != null){
                errorTitle = errorTitleByKey;
            }
            title = errorTitle;
        }

        if (this.showTitle || errorBehavior != ErrorBehavior.FRIENDLY_MSG) {
            if (contextItem != null) {
                title = contextItem.getLabel();
            } else {
                title = errorTitle;
            }
        }

        label = new JLabel(title);

        label.setFont(headerFontAttributes);
        label.setForeground(titleColor);
        label.setBorder(BorderFactory.createEmptyBorder(8, 10, 0, 10));
        return label;
    }
}
