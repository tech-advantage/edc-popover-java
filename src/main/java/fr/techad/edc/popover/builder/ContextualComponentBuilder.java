package fr.techad.edc.popover.builder;

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
     * Define the error iconpath
     *
     * @param iconPath the error icon path to set
     * @return the builder
     */
    ContextualComponentBuilder<T> setErrorIconPath(String iconPath);

    /**
     * Build the contextual component.
     *
     * @return the contextual component
     */
    T build();
}
