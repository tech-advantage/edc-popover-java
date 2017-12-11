package fr.techad.edc.popover.internal.swing;

import fr.techad.edc.popover.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.injector.provider.HelpListenerProvider;
import fr.techad.edc.popover.internal.EdcHelpImpl;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.swing.EdcSwingHelp;
import fr.techad.edc.popover.swing.HelpListener;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Edc Help implementation for Swing component
 */
public class EdcSwingHelpImpl extends EdcHelpImpl implements EdcSwingHelp {

    private final ContextualComponentBuilder<JComponent> contextualComponentBuilder;
    private final HelpListenerProvider helpListenerProvider;

    @Inject
    public EdcSwingHelpImpl(ContextualComponentBuilder<JComponent> contextualComponentBuilder, HelpConfiguration helpConfiguration, HelpListenerProvider helpListenerProvider) {
        super(helpConfiguration);
        this.contextualComponentBuilder = contextualComponentBuilder;
        this.helpListenerProvider = helpListenerProvider;
    }

    @Override
    public JComponent createComponent(String mainKey, String subKey) {
        HelpConfiguration helpConfiguration = getHelpConfiguration();
        return contextualComponentBuilder.setKeys(mainKey, subKey, helpConfiguration.getLanguageCode())
                .setIconPath(helpConfiguration.getIconPath())
                .setLabel(helpConfiguration.getTooltipLabel())
                .build();
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
