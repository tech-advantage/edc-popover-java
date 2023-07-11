package fr.techad.edc.popover.model;

import java.awt.Color;
import java.awt.Font;

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
     * Return the error icon path
     *
     * @return the error icon path
     */
    String getErrorIconPath();

    /**
     * Set the error icon path
     *
     * @param iconPath the error icon path to set
     */
    void setErrorIconPath(String iconPath);

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
    boolean getPopoverDisplay();

    /**
     * If false, the help will be displayed in the help component else a component will display it partially (ie popover for example)
     *
     * @param enabled the new state to set
     */
    void setPopoverDisplay(boolean enabled);

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
  
    /** Return true if the article is shown in the component
     *
     * @return true if visible
     */
    boolean isShowArticle();

    /**
     * Set the visibility of the article
     *
     * @param showArticle the visibility status to set
     */
    void setShowArticle(boolean showArticle);

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
     * Return true if the separator is shown in the component
     *
     * @return true if visible
     */
    boolean isShowSeparator();

    /**
     * Set the visibility of the separator
     *
     * @param showSeparator the visibility status to set
     */
    void setShowSeparator(boolean showSeparator);

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
     * Return the path of the executable
     *
     * @return the path
     */
    String getViewerDesktopPath();

    /**
     * Define the path to the location of the application executable
     *
     * @param path to executable
     */
    void setViewerDesktopPath(String path);

    /**
     * Return the url of desktop viewer
     *
     * @return the url
     */
    String getViewerDesktopServerURL();

    /**
     * Define the url of viewer desktop
     *
     * @param url
     */
    void setViewerDesktopServerURL(String url);

    /**
     * Return the viewer desktop width
     *
     * @return the viewer desktop width
     */
    int getViewerDesktopWidth();

    /**
     * Define the viewer desktop width
     *
     * @param width
     */
    void setViewerDesktopWidth(int width);

    /**
     * Return the viewer desktop height
     *
     * @return the viewer desktop height
     */
    int getViewerDesktopHeight();

    /**
     * Define the viewer desktop height
     *
     * @param height
     */
    void setViewerDesktopHeight(int height);

    /**
     * Return the enum placement
     *
     * @return Enum
     */
    PopoverPlacement getPopoverPlacement();

    /**
     * Define the placement of popover
     *
     * @param popoverPlacement
     */
    void setPopoverPlacement(PopoverPlacement popoverPlacement);

    /**
     * Return the error behavior
     *
     * @return error behavior
     */
    ErrorBehavior getErrorBehavior();

    /**
     * Define the error behavior
     *
     * @param errorBehavior
     */
    void setErrorBehavior(ErrorBehavior errorBehavior);

    /**
     * Return the icon state
     *
     * @return state
     */
    IconState getIconState();

    /**
     * Return true if the tooltip is shown when mouse is hover the component
     *
     * @return true if visible
     */
    boolean isShowTooltip();

    /**
     * Set the visibility of the tooltip
     *
     * @param showTooltip the visibility status to set
     */
    void setShowTooltip(boolean showTooltip);

    /**
     * Define the icon state
     *
     * @param state
     */
    void setIconState(IconState state);

    /**
     * Return the icon Dark mode path
     *
     * @return the icon Dark mode path
     */
    String getIconDarkModePath();

    /**
     * Set the icon Dark mode path
     *
     * @param iconPath
     */
    void setIconDarkModePath(String iconPath);

    /**
     * Return true if enable
     *
     * @return true if enable
     */
    boolean isDarkMode();

    /**
     * Enable the dark mode
     *
     * @param enable
     */
    void setDarkMode(boolean enable);

    /** Return the font attributes of the header title
     *
     * @return the fonts attributes
     */
    Font getHeaderTitleFont();

    /**
     * Define the fonts attributes for the header title
     *
     * @param fontAttr
     */
    void setHeaderTitleFont(Font fontAttr);

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

    /**
     * Return the font attributes of the popover section title
     *
     * @return the font attributes
     */
    Font getPopoverSectionTitleFont();

    /**
     * Define font attributes for the popover section title
     *
     * @param fontAttr
     */
    void setPopoverSectionTitleFont(Font fontAttr);

    /**
     * Return the popover section title color
     *
     * @return the color
     */
    Color getPopoverSectionTitleColor();

    /**
     * Define the popover section title color
     *
     * @param popoverTitleColor
     */
    void setPopoverSectionTitleColor(Color popoverTitleColor);

    /**
     * Define the color for the Popover description
     *
     * @param descColor
     */
    void setPopoverDescriptionColor(Color descColor);

    /**
     * Define the fonts description of the Popover
     *
     * @param fontAttr
     */
    void setPopoverDescriptionFont(Font fontAttr);

    /**
     * Return the description fonts attributes
     *
     * @return the description fonts attributes
     */
    Font getPopoverDescriptionFont();

    /**
     * Return the Popover description color
     *
     * @return the Popover description color
     */
    Color getPopoverDescriptionColor();

    /**
     * Define the foreground color for the links
     *
     * @param linksColor
     */
    void setPopoverLinksColor(Color linksColor);

    /**
     * Return the links color
     *
     * @return the links color
     */
    Color getPopoverLinksColor();

    /**
     * Define the fonts attributes for the Popover links
     *
     * @param fontAttr
     */
    void setPopoverLinksFont(Font fontAttr);

    /**
     * Return the links fonts attributes
     *
     * @return the links fonts attributes
     */
    Font getPopoverLinksFont();
}
