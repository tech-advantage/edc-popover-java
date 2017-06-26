package fr.techad.edc.popover;

import fr.techad.edc.client.EdcClient;

/**
 * Define the base functionality to manage the help display.
 */
public interface EdcHelp {

    /**
     * Define the path for the icon to use.
     * You can define a new icon.
     *
     * @param iconPath the icon path to set
     */
    void setIconPath(String iconPath);

    /**
     * Define the language code which will be used to display the documentation.
     * The language code is 2 digits and lowercase: en, fr, ....
     *
     * @param languageCode
     */
    void setLanguageCode(String languageCode);

}
