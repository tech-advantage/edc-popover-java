package fr.techad.edc.popover.builder;

import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.popover.model.ErrorBehavior;

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
  
    /** Enable the related topics display
     *
     * @param enable
     * @return true if is related topics enabled
     */
    ContextualContentComponentBuilder<T> enableRelatedTopics(boolean enable);

     /** Define the article title color
     *
     * @param titleColor
     * @return title color
     */
    ContextualContentComponentBuilder<T> setPopoverSectionTitleColor(Color titleColor);

    /**
     * Define the font attributes of article title
     *
     * @param fontAttr
     * @return the builder
     */
    ContextualContentComponentBuilder<T> setPopoverSectionTitleFont(Font fontAttr);

     /** Enable the article display
     *
     * @param enable
     * @return true if is article enabled
     */
    ContextualContentComponentBuilder<T> enableArticle(boolean enable);

    /**
     * Build the contextual component.
     *
     * @return the contextual component
     */
    T build();
}
