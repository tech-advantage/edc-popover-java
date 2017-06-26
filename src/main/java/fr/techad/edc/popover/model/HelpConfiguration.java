package fr.techad.edc.popover.model;

/**
 * Define the help configuration
 */
public interface HelpConfiguration {

    /**
     * Return the icon path
     *
     * @return the icon path
     */
    String getIconPath();

    /**
     * Set the icon path
     *
     * @param iconPath the icon path to set
     */
    void setIconPath(String iconPath);

    /**
     * Get the language code
     *
     * @return the language code
     */
    String getLanguageCode();

    /**
     * Define the language code which will be used to display the documentation.
     * The language code is 2 digits and lowercase: en, fr, ....
     *
     * @param languageCode the language code to set
     */
    void setLanguageCode(String languageCode);
}
