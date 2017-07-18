package fr.techad.edc.popover.internal.swing;

import fr.techad.edc.popover.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.internal.EdcHelpImpl;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.swing.EdcSwingHelp;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

/**
 * Edc Help implementation for Swing component
 */
public class EdcSwingHelpImpl extends EdcHelpImpl implements EdcSwingHelp {

    private final ContextualComponentBuilder<JComponent> contextualComponentBuilder;

    @Inject
    public EdcSwingHelpImpl(ContextualComponentBuilder<JComponent> contextualComponentBuilder, HelpConfiguration helpConfiguration) {
        super(helpConfiguration);
        this.contextualComponentBuilder = contextualComponentBuilder;
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
    public void setBackgroundColor(Color backgroundColor) {
        getHelpConfiguration().setBackgroundColor(backgroundColor.getRGB());
    }
}
