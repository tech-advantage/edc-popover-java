package fr.techad.edc.popover.builder;

import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.model.IconState;

/**
 * Builder to configure a contextual component.
 */
public interface ContextualComponentBuilder<T> {

    /**
     * Define the keys for the component
     *
     * @param mainKey      the main key
     * @param subKey       the sub key
     * @param languageCode the language code
     * @return the builder
     */
    ContextualComponentBuilder<T> setKeys(String mainKey, String subKey, String languageCode);

    /**
     * Define the label for the component
     *
     * @param label the label
     * @return the builder
     */
    ContextualComponentBuilder<T> setLabel(String label);

    /**
     * Define the iconpath
     *
     * @param iconPath the icon path to set
     * @return the builder
     */
    ContextualComponentBuilder<T> setIconPath(String iconPath);

    /**
     * Define the icon dark mode path
     *
     * @param iconPath
     * @return the builder
     */
    ContextualComponentBuilder<T> setIconDarkModePath(String iconPath);

    /**
     * Enable darkMode
     *
     * @param enable
     * @return the builder
     */
    ContextualComponentBuilder<T> setDarkMode(boolean enable);

    /**
     * Define the error iconpath
     *
     * @param iconPath the error icon path to set
     * @return the builder
     */
    ContextualComponentBuilder<T> setErrorIconPath(String iconPath);

    /**
     * Define the error behavior
     *
     * @param errorBehavior the error behavior to set
     * @return the builder
     */
    ContextualComponentBuilder<T> setErrorBehavior(ErrorBehavior errorBehavior);

    /**
     * Define the icon state
     *
     * @param iconState the icon state to set
     * @return the builder
     */
    ContextualComponentBuilder<T> setIconState(IconState iconState);

    /**
     * Enable the context item
     *
     * @param enable
     * @return the builder
     */
    ContextualComponentBuilder<T> setEnableContextItem(boolean enable);
    
    /**
     * Define the visibility of tooltip label
     *
     * @param enable
     * @return true if enabled
     */
    ContextualComponentBuilder<T> showTooltip(boolean enable);

    /**
     * Build the contextual component.
     *
     * @return the contextual component
     */
    T build();
}
