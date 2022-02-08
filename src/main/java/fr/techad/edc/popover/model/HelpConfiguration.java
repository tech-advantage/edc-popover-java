package fr.techad.edc.popover.model;

import java.awt.Color;
import java.awt.Font;

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
     * Return the status for the popover display.
     *
     * @return the state
     */
    boolean isHoverDisplayPopover();

    /**
     * If true, the popover will be displayed when the mouse is hover it
     *
     * @param enabled the new state to set
     */
    void setHoverDisplayPopover(boolean enabled);

    /**
     * Return true if the title is shown in the component
     *
     * @return true if visible
     */
    boolean isShowTitle();

    /**
     * Set the visibility of the title
     *
     * @param showTitle the visibility status to set
     */
    void setShowTitle(boolean showTitle);

    /**
     * Return true if the related topics are shown in the component
     *
     * @return true if visible
     */
    boolean isShowRelatedTopics();

    /**
     * Set the visibility of the related topics
     *
     * @param showRelatedTopics the visibility status to set
     */
    void setShowRelatedTopics(boolean showRelatedTopics);

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
     * Return the underline color
     *
     * @return the underline color
     */
    int getUnderlineColor();

    /**
     * Define the underline color for the title component
     *
     * @param underlineColor the color to set
     */
    void setUnderlineColor(int underlineColor);

    /**
     * Return true if the browser to use is the internal browser
     *
     * @return true if the browser to use is the internal browser
     */
    boolean isInternalBrowser();

    /**
     * Return the selected viewer type between : SYSTEM_BROWSER, EDC_DESKTOP_VIEWER and EMBEDDED_VIEWER
     *
     * @return Enum for the viewer selected
     */
    HelpViewer getHelpViewer();

    /**
     * Define the selected viewer type to display documentation
     *
     * @param viewer the viewer type to set
     */
    void setHelpViewer(HelpViewer viewer);


    /**
     * Define the path to the location of the application executable
     *
     * @param path to executable
     */
    void setViewerDesktopPath(String path);

    /**
     * Return the path of the executable
     *
     * @return the path
     */
    String getViewerDesktopPath();

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

    /**
     * Return the font attributes of the header title
     *
     * @return the fonts attributes
     */
    Font getHeaderFontAttributes();

    /**
     * Define the fonts attributes for the header title
     *
     * @param fontAttributes
     */
    void setHeaderFontAttributes(Font fontAttributes);

    /**
     * Return the header title color
     *
     * @return the header title color
     */
    Color getHeaderTitleColor();

    /**
     * Define the foreground color for the header title
     *
     * @param titleColor
     */
    void setHeaderTitleColor(Color titleColor);
}
