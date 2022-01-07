package fr.techad.edc.popover.builder;

import fr.techad.edc.client.model.ContextItem;

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
     * Enable the related topics display
     *
     * @param enable
     * @return true if is related topics enabled
     */
    ContextualContentComponentBuilder<T> enableRelatedTopics(boolean enable);

    /**
     * Build the contextual component.
     *
     * @return the contextual component
     */
    T build();
}
