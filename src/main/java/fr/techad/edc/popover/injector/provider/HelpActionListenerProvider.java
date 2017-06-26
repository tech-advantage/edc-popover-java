package fr.techad.edc.popover.injector.provider;

import fr.techad.edc.client.EdcClient;
import fr.techad.edc.popover.internal.swing.components.IconButtonListener;
import fr.techad.edc.popover.swing.HelpActionListener;
import fr.techad.edc.popover.model.HelpConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Provider of {@link HelpActionListener}
 *
 * Get a {@link IconButtonListener}
 */
public class HelpActionListenerProvider implements Provider<HelpActionListener> {
    private final EdcClient edcClient;

    @Inject
    HelpActionListenerProvider(EdcClient edcClient) {
        this.edcClient = edcClient;
    }

    @Override
    public HelpActionListener get() {
        return new IconButtonListener(edcClient);
    }
}
