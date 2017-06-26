package fr.techad.edc.popover.injector.builder;

/**
 * Builder to configure a contextual component.
 */
public interface ContextualComponentBuilder<T> {

    /**
     * Define the keys for the component
     *
     * @param mainKey the main key
     * @param subKey  the sub key
     * @return the builder
     */
    ContextualComponentBuilder<T> setKeys(String mainKey, String subKey);

    /**
     * Build the contextual component.
     *
     * @return the contextual component
     */
    T build();
}
