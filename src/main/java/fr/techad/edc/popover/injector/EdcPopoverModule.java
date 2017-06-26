/*
 * Copyright (c) 2017. All rights reserved
 */

package fr.techad.edc.popover.injector;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import fr.techad.edc.popover.injector.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.injector.provider.HelpActionListenerProvider;
import fr.techad.edc.popover.internal.model.HelpConfigurationImpl;
import fr.techad.edc.popover.internal.swing.EdcSwingHelpImpl;
import fr.techad.edc.popover.internal.swing.builder.ContextualComponentBuilderImpl;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.swing.EdcSwingHelp;
import fr.techad.edc.popover.swing.HelpActionListener;

import javax.swing.*;

/**
 * The guice module to configure injection
 */
public class EdcPopoverModule extends AbstractModule {
    @Override
    protected void configure() {
        // Model
        bind(HelpConfiguration.class).to(HelpConfigurationImpl.class).in(Scopes.SINGLETON);

        // Builder
        bind(new TypeLiteral<ContextualComponentBuilder<JComponent>>(){}).to(ContextualComponentBuilderImpl.class);

        // Provider
        bind(HelpActionListener.class).toProvider(HelpActionListenerProvider.class);

        // The Helper
        bind(EdcSwingHelp.class).to(EdcSwingHelpImpl.class).in(Scopes.SINGLETON);
    }
}
