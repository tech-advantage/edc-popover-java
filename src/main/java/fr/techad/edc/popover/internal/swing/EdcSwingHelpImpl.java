package fr.techad.edc.popover.internal.swing;

import fr.techad.edc.client.EdcClient;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.injector.provider.HelpListenerProvider;
import fr.techad.edc.popover.internal.EdcHelpImpl;
import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.model.IconState;
import fr.techad.edc.popover.swing.EdcSwingHelp;
import fr.techad.edc.popover.swing.HelpListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Edc Help implementation for Swing component
 */
public class EdcSwingHelpImpl extends EdcHelpImpl implements EdcSwingHelp {
    private static final Logger LOGGER = LoggerFactory.getLogger(EdcSwingHelpImpl.class);

    private final EdcClient edcClient;
    private final ContextualComponentBuilder<JComponent> contextualComponentBuilder;
    private final HelpListenerProvider helpListenerProvider;
    private boolean enableMainKey = false;

    @Inject
    public EdcSwingHelpImpl(EdcClient edcClient, ContextualComponentBuilder<JComponent> contextualComponentBuilder, HelpConfiguration helpConfiguration, HelpListenerProvider helpListenerProvider) {
        super(helpConfiguration);
        this.edcClient = edcClient;
        this.contextualComponentBuilder = contextualComponentBuilder;
        this.helpListenerProvider = helpListenerProvider;
    }

    @Override
    public JComponent createComponent(String mainKey, String subKey, String iconPath) {
        HelpConfiguration helpConfiguration = getHelpConfiguration();
        String languageCode = helpConfiguration.getLanguageCode();

        try {
            enableMainKey = edcClient.getContextItem(mainKey, subKey, languageCode) != null;
        } catch (InvalidUrlException | IOException e) {
            LOGGER.error("Impossible to get the context item for key ({}, {}) and languageCode: {}", mainKey, subKey, languageCode);
        }

        JComponent component = contextualComponentBuilder
                .setKeys(mainKey, subKey, languageCode)
                .setIconPath(helpConfiguration.getErrorBehavior() == ErrorBehavior.NO_POPOVER && !enableMainKey || helpConfiguration.getIconState() == IconState.HIDDEN && !enableMainKey ? "" :
                        helpConfiguration.getIconState() == IconState.ERROR && !enableMainKey ? helpConfiguration.getErrorIconPath() : iconPath)
                .setErrorIconPath(helpConfiguration.getErrorIconPath())
                .setLabel(helpConfiguration.getTooltipLabel())
                .build();

        if (helpConfiguration.getIconState() == IconState.DISABLED) {
            boolean enabled = false;
            try {
                enabled = edcClient.getContextItem(mainKey, subKey, languageCode) != null;
            } catch (InvalidUrlException | IOException e) {
                LOGGER.error("Impossible to get the context item for key ({}, {}) and languageCode: {}", mainKey, subKey, languageCode);
            }
            component.setEnabled(enabled);
        }

        return component;
    }


    @Override
    public JComponent createComponent(String mainKey, String subKey) {
        HelpConfiguration helpConfiguration = getHelpConfiguration();
        return createComponent(mainKey, subKey, helpConfiguration.getIconPath());
    }

    @Override
    public MouseListener getMouseListener(String mainKey, String subKey) {
        HelpListener helpListener = helpListenerProvider.get();
        helpListener.setKeys(mainKey, subKey);
        return helpListener;
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        getHelpConfiguration().setBackgroundColor(backgroundColor.getRGB());
    }

    @Override
    public void setSeparatorColor(Color separatorColor) {
        getHelpConfiguration().setUnderlineColor(separatorColor.getRGB());
    }

    @Override
    public void setHeaderFontAttributes(Font fontAttributes) {
        getHelpConfiguration().setHeaderFontAttributes(fontAttributes);
    }


    @Override
    public void setBrowserSize(int width, int height) {
        HelpConfiguration helpConfiguration = getHelpConfiguration();
        helpConfiguration.setWidthBrowser(width);
        helpConfiguration.setHeightBrowser(height);
    }

    @Override
    public void setPopoverSectionTitleColor(Color titleColor) {
        getHelpConfiguration().setPopoverSectionTitleColor(titleColor);
    }

    @Override
    public void setPopoverSectionTitleFont(Font fontAttr) {
        getHelpConfiguration().setPopoverSectionTitleFont(fontAttr);
    }

    @Override
    public void setHeaderTitleColor(Color titleColor) {
        getHelpConfiguration().setHeaderTitleColor(titleColor);
    }
}
