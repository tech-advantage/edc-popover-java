package fr.techad.edc.popover.internal.swing;

import fr.techad.edc.client.EdcClient;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.injector.provider.HelpListenerProvider;
import fr.techad.edc.popover.internal.EdcHelpImpl;
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
    private boolean enableContextItem = false;

    @Inject
    public EdcSwingHelpImpl(
            EdcClient edcClient,
            ContextualComponentBuilder<JComponent> contextualComponentBuilder,
            HelpConfiguration helpConfiguration,
            HelpListenerProvider helpListenerProvider
    ) {
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
            enableContextItem = edcClient.getContextItem(mainKey, subKey, languageCode) != null;
        } catch (InvalidUrlException | IOException e) {
            LOGGER.error("Impossible to get the context item for key ({}, {}) and languageCode: {}", mainKey, subKey, languageCode);
        }

        JComponent component = contextualComponentBuilder
                .setKeys(mainKey, subKey, languageCode)
                .setErrorBehavior(helpConfiguration.getErrorBehavior())
                .setIconState(helpConfiguration.getIconState())
                .setEnableContextItem(enableContextItem)
                .setIconPath(iconPath)
                .setErrorIconPath(helpConfiguration.getErrorIconPath())
                .setLabel(helpConfiguration.getTooltipLabel())
                .showTooltip(helpConfiguration.isShowTooltip())
                .build();

        if (helpConfiguration.getIconState() == IconState.DISABLED) {
            component.setEnabled(enableContextItem);
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
    public void setPopoverSectionTitleColor(Color titleColor) {
        getHelpConfiguration().setPopoverSectionTitleColor(titleColor);
    }

    @Override
    public void setHeaderTitleFont(Font fontAttr) {
        getHelpConfiguration().setHeaderTitleFont(fontAttr);
    }
    @Override
    public void setHeaderTitleColor(Color titleColor) {
        getHelpConfiguration().setHeaderTitleColor(titleColor);
    }

    @Override
    public void setViewerDesktopServerURL(String url) {
        getHelpConfiguration().setViewerDesktopServerURL(url);
    }

    @Override
    public void setPopoverSectionTitleFont(Font fontAttr) {
        getHelpConfiguration().setPopoverSectionTitleFont(fontAttr);
    }

    @Override
    public void setPopoverLinksColor(Color linkColor) {
        getHelpConfiguration().setPopoverLinksColor(linkColor);
    }

    @Override
    public void setPopoverLinksFont(Font fontAttr) {
        getHelpConfiguration().setPopoverLinksFont(fontAttr);
    }

    @Override
    public void setPopoverDescriptionColor(Color descColor) {
        getHelpConfiguration().setPopoverDescriptionColor(descColor);
    }

    @Override
    public void setPopoverDescriptionFont(Font fontAttr) {
        getHelpConfiguration().setPopoverDescriptionFont(fontAttr);
    }
}
