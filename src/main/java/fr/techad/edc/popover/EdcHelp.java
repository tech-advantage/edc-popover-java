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
     * Define the language code which will be used to display the documentation and the popover labels.
     * If content was not found in the requested language, publication default language will be used.
     *
     * Popover label translations can be modified in the i18n json files present in the documentation export
     * (at [yourDocPath]/i18n/ (*.json))
     *
     * The language code is 2 digits and lowercase: en, fr, ....
     *
     * @param languageCode the language code
     */
    void setLanguageCode(String languageCode);

    /**
     * Define the tooltip label which will be display on the icon.
     *
     * @param label the label to set
     */
    void setTooltipLabel(String label);

    /**
     * Active or deactivate the summary display. A summary is displayed on click on the component help else the help is opened.
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

    /**
     * Define the behavior : If true, the component will be disabled if the content is missing
     *
     * @param state the state to set
     */
    void setAutoDisabledMode(boolean state);

    /**
     * Show the title if enabled
     *
     * @param enable true to enable the title display.
     */
    void setTitleDisplay(boolean enable);
}
