package fr.techad.edc.popover.builder;

import fr.techad.edc.client.model.ContextItem;

import java.awt.Color;
import java.awt.Font;

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
     * Define the related topics title color
     *
     * @param titleColor
     * @return title color
     */
    ContextualContentComponentBuilder<T> setTopicsTitleColor(Color titleColor);

    /**
     * Define the font attributes of related topics title
     *
     * @param fontAttributes
     * @return the builder
     */
    ContextualContentComponentBuilder<T> setTopicsFontAttributes(Font fontAttributes);

    /**
     * Build the contextual component.
     *
     * @return the contextual component
     */
    T build();
}
