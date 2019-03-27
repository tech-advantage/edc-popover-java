package fr.techad.edc.popover.model;

/**
 * Define the help configuration
 */
public interface HelpConfiguration {

    /**
     * Return the browser width
     *
     * @return the browser width
     */
    int getWidthBrowser();

    /**
     * Define the browser width
     *
     * @param width the width
     */
    void setWidthBrowser(int width);

    /**
     * Return the browser height
     *
     * @return the browser height
     */
    int getHeightBrowser();

    /**
     * Define the browser height
     *
     * @param height the height
     */
    void setHeightBrowser(int height);

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
     * Return the close icon path
     *
     * @return the icon path
     */
    String getCloseIconPath();

    /**
     * Set the close icon path
     *
     * @param iconPath the icon path to set
     */
    void setCloseIconPath(String iconPath);

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

    /**
     * Return the tooltip label
     *
     * @return the label
     */
    String getTooltipLabel();

    /**
     * Define the tooltip
     *
     * @param label the label to set
     */
    void setTooltipLabel(String label);

    /**
     * Return the status for the summary display of the help.
     *
     * @return the state
     */
    boolean getSummaryDisplay();

    /**
     * If false, the help will be displayed in the help component else a component will display it partially (ie popover for example)
     *
     * @param enabled the new state to set
     */
    void setSummaryDisplay(boolean enabled);

    /**
     * Return the background color
     *
     * @return the background color
     */
    int getBackgroundColor();

    /**
     * Define the background color for the component summary
     *
     * @param backgroundColor the color to set
     */
    void setBackgroundColor(int backgroundColor);

    /**
     * Return true if the browser to use is the internal browser
     *
     * @return true if the browser to use is the internal browser
     */
    boolean isInternalBrowser();

    /**
     * Define the browser to use : true to use the internal browser, false to use the system browser.
     *
     * @param state true to use the internal browser
     */
    void setInternalBrowser(boolean state);

    /**
     * Return the mode
     *
     * @return true if the auto disabled mode is active
     */
    boolean isAutoDisabledInMissingContent();

    /**
     * Define the behavior to auto disabled the component if the content doesn't exist.
     *
     * @param state the state to defined
     */
    void setAutoDisabledInMissingContent(boolean state);
}
