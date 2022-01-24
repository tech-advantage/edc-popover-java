package fr.techad.edc.popover.builder;

import fr.techad.edc.client.model.ContextItem;

import java.awt.*;

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
     * Define the article title color
     *
     * @param titleColor
     * @return title color
     */
    ContextualContentComponentBuilder<T> setArticleTitleColor(Color titleColor);

    /**
     * Define the font attributes of article title
     *
     * @param fontAttributes
     * @return the builder
     */
    ContextualContentComponentBuilder<T> setArticleFontAttributes(Font fontAttributes);

    /**
     * Build the contextual component.
     *
     * @return the contextual component
     */
    T build();
}
