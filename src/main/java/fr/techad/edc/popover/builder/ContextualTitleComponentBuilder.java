package fr.techad.edc.popover.builder;

import fr.techad.edc.client.model.ContextItem;

/**
 * Builder to configure a contextual title component.
 */
public interface ContextualTitleComponentBuilder<T> {

    /**
     * Define the context item for the component
     *
     * @param contextItem the context item
     * @return the builder
     */
    ContextualTitleComponentBuilder<T> setContextItem(ContextItem contextItem);

    /**
     * Define the rgb color for the background
     *
     * @param rgbColor the rgb color
     * @return the builder
     */
    ContextualTitleComponentBuilder<T> setBackgroundColor(int rgbColor);

    /**
     * Show the title
     * @param enable true to show the component
     * @return the builder
     */
    ContextualTitleComponentBuilder<T> setShowTitle(boolean enable);

    /**
     * Build the contextual title component.
     *
     * @return the contextual component
     */
    T build();
}
