package fr.techad.edc.popover.internal.swing;

import fr.techad.edc.client.EdcClient;
import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.injector.provider.HelpListenerProvider;
import fr.techad.edc.popover.internal.EdcHelpImpl;
import fr.techad.edc.popover.model.HelpConfiguration;
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

    @Inject
    public EdcSwingHelpImpl(EdcClient edcClient, ContextualComponentBuilder<JComponent> contextualComponentBuilder, HelpConfiguration helpConfiguration, HelpListenerProvider helpListenerProvider) {
        super(helpConfiguration);
        this.edcClient = edcClient;
        this.contextualComponentBuilder = contextualComponentBuilder;
        this.helpListenerProvider = helpListenerProvider;
    }

    @Override
    public JComponent createComponent(String mainKey, String subKey) {
        HelpConfiguration helpConfiguration = getHelpConfiguration();
        String languageCode = helpConfiguration.getLanguageCode();

        JComponent component = contextualComponentBuilder.setKeys(mainKey, subKey, languageCode)
                .setIconPath(helpConfiguration.getIconPath())
                .setLabel(helpConfiguration.getTooltipLabel())
                .build();
        if (helpConfiguration.isAutoDisabledInMissingContent()) {
            try {
                ContextItem contextItem = edcClient.getContextItem(mainKey, subKey, languageCode);
                if (contextItem == null)
                    component.setEnabled(false);
            } catch (InvalidUrlException | IOException e) {
                LOGGER.error("Impossible to get the context item for key ({}, {}) and languageCode: {}", mainKey, subKey, languageCode);
            }
        }
        return component;
    }

    @Override
    public MouseListener getMouseListener(String mainKey, String subKey) {
        HelpListener helpListener = helpListenerProvider.get();
        helpListener.setKeysAndLanguageCode(mainKey, subKey, getHelpConfiguration().getLanguageCode());
        return helpListener;
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        getHelpConfiguration().setBackgroundColor(backgroundColor.getRGB());
    }
}
