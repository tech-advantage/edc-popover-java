package fr.techad.edc.popover.builder;

import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.popover.model.ErrorBehavior;

import java.awt.Font;
import java.awt.Color;

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
     * Define the header title color
     *
     * @param titleColor
     * @return the builder
     */
    ContextualTitleComponentBuilder<T> setHeaderTitleColor(Color titleColor);

    /**
     * Define the fonts attributes of the header title
     *
     * @param fontAttributes
     * @return the builder
     */
    ContextualTitleComponentBuilder<T> setHeaderFontAttributes(Font fontAttributes);

    /**
     * Show the title
     * @param enable true to show the component
     * @return the builder
     */
    ContextualTitleComponentBuilder<T> setShowTitle(boolean enable);

    /**
     * Define the error behavior
     *
     * @param errorBehavior
     * @return the builder
     */
    ContextualTitleComponentBuilder<T> setErrorBehavior(ErrorBehavior errorBehavior);

    /**
     * Define the language code
     *
     * @param languageCode
     * @return the builder
     */
    ContextualTitleComponentBuilder<T> setLanguageCode(String languageCode);

    /**
     * Build the contextual title component.
     *
     * @return the contextual component
     */
    T build();
}
