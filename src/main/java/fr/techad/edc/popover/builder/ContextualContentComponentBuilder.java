package fr.techad.edc.popover.builder;

import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.model.IconState;

/**
 * Builder to configure a contextual component.
 */
public interface ContextualContentComponentBuilder<T> {

    /**
     * Define the context item for the component
     *
     * @param contextItem the context item
     * @return the builder
     */
    ContextualContentComponentBuilder<T> setContextItem(ContextItem contextItem);

    /**
     * Define the rgb color for the background
     *
     * @param rgbColor the rgb color
     * @return the builder
     */
    ContextualContentComponentBuilder<T> setBackgroundColor(int rgbColor);

    /**
     * Define the error behavior
     *
     * @param errorBehavior
     * @return
     */
    ContextualContentComponentBuilder<T> setErrorBehavior(ErrorBehavior errorBehavior);

    /**
     * Define the language code
     *
     * @param languageCode
     * @return
     */
    ContextualContentComponentBuilder<T> setLanguageCode(String languageCode);

    /**
     * Build the contextual component.
     *
     * @return the contextual component
     */
    T build();
}
