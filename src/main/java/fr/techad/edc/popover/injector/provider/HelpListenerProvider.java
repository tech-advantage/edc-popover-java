package fr.techad.edc.popover.injector.provider;

import fr.techad.edc.client.EdcClient;
import fr.techad.edc.popover.builder.ContextualContentComponentBuilder;
import fr.techad.edc.popover.internal.swing.components.IconButtonListener;
import fr.techad.edc.popover.internal.swing.components.Popover;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.swing.HelpListener;
import fr.techad.edc.popover.utils.OpenUrlAction;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.*;

/**
 * Provider of {@link HelpListener}
 * <p>
 * Get a {@link IconButtonListener}
 */
public class HelpListenerProvider implements Provider<HelpListener> {
    private final EdcClient edcClient;
    private final HelpConfiguration helpConfiguration;
    private final ContextualContentComponentBuilder<JComponent> contextualContentComponentBuilder;
    private final Popover popover;
    private final OpenUrlAction openUrlAction;

    @Inject
    HelpListenerProvider(EdcClient edcClient,
                         HelpConfiguration helpConfiguration,
                         ContextualContentComponentBuilder<JComponent> contextualContentComponentBuilder,
                         Popover popover,
                         OpenUrlAction openUrlAction) {
        this.edcClient = edcClient;
        this.helpConfiguration = helpConfiguration;
        this.contextualContentComponentBuilder = contextualContentComponentBuilder;
        this.popover = popover;
        this.openUrlAction = openUrlAction;
    }

    @Override
    public HelpListener get() {
        return new IconButtonListener(edcClient, helpConfiguration, contextualContentComponentBuilder, popover, openUrlAction);
    }
}
