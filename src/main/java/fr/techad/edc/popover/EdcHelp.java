package fr.techad.edc.popover;

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
     * Define the path for the close icon to use. This icon is used in the summary component only.
     *
     * @param iconPath the icon path to set
     */
    void setCloseIconPath(String iconPath);


    /**
     * Define the language code which will be used to display the documentation.
     * The language code is 2 digits and lowercase: en, fr, ....
     *
     * @param languageCode
     */
    void setLanguageCode(String languageCode);

    /**
     * Define the tooltip label which will be display on the icon.
     *
     * @param label the label to set
     */
    void setTooltipLabel(String label);

    /**
     * Active or deactivate the summary display. A summary display is displayed on click on the component help else the help is opened.
     *
     * @param enable the new status
     */
    void setSummaryDisplay(boolean enable);

    /**
     * Define the browser to use : true to use the internal brower, false to use the system browser.
     *
     * @param state true to use the internal brower
     */
    void setInternalBrowser(boolean state);

}
