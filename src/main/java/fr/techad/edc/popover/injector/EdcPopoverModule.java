/*
 * Copyright (c) 2017. All rights reserved
 */

package fr.techad.edc.popover.injector;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import fr.techad.edc.popover.browser.Browser;
import fr.techad.edc.popover.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.builder.ContextualContentComponentBuilder;
import fr.techad.edc.popover.builder.ContextualTitleComponentBuilder;
import fr.techad.edc.popover.injector.provider.HelpListenerProvider;
import fr.techad.edc.popover.internal.browser.SwingBrowser;
import fr.techad.edc.popover.internal.model.HelpConfigurationImpl;
import fr.techad.edc.popover.internal.swing.EdcSwingHelpImpl;
import fr.techad.edc.popover.internal.swing.builder.ContextualComponentBuilderImpl;
import fr.techad.edc.popover.internal.swing.builder.ContextualContentComponentBuilderImpl;
import fr.techad.edc.popover.internal.swing.builder.ContextualTitleComponentBuilderImpl;
import fr.techad.edc.popover.internal.swing.components.Popover;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.swing.EdcSwingHelp;
import fr.techad.edc.popover.swing.HelpListener;

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
        bind(new TypeLiteral<ContextualComponentBuilder<JComponent>>() {}).to(ContextualComponentBuilderImpl.class);
        bind(new TypeLiteral<ContextualContentComponentBuilder<JComponent>>() {}).to(ContextualContentComponentBuilderImpl.class);
        bind(new TypeLiteral<ContextualTitleComponentBuilder<JComponent>>() {}).to(ContextualTitleComponentBuilderImpl.class);

        // Provider
        bind(HelpListener.class).toProvider(HelpListenerProvider.class);

        // The Helper
        bind(EdcSwingHelp.class).to(EdcSwingHelpImpl.class).in(Scopes.SINGLETON);
        bind(Popover.class).in(Scopes.SINGLETON);
        bind(Browser.class).to(SwingBrowser.class).in(Scopes.SINGLETON);
    }
}
