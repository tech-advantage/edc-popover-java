package fr.techad.edc.popover.internal.swing;

import fr.techad.edc.popover.injector.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.internal.EdcHelpImpl;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.swing.EdcSwingHelp;

import javax.inject.Inject;
import javax.swing.*;

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
        return contextualComponentBuilder.setKeys(mainKey, subKey).build();
    }
}
