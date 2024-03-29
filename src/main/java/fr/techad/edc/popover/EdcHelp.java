package fr.techad.edc.popover;

import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.model.IconState;
import fr.techad.edc.popover.model.PopoverPlacement;
import fr.techad.edc.popover.model.HelpViewer;

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
     * Define the path for the error icon to use.
     *
     * @param iconPath the icon path to set
     */
    void setErrorIconPath(String iconPath);

    /**
     * Define the path for the dark icon to use.
     *
     * @param iconPath
     */
    void setIconDarkModePath(String iconPath);

    /**
     * Enable the dark mode
     *
     * @param enable
     */
    void setDarkMode(boolean enable);

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
     * @param enable the new status
     */
    void setPopoverDisplay(boolean enable);

  
    /**
     * Active or deactivate the popover display when the mouse is over it.
     *
     * @param enable
     */
    void setHoverDisplayPopover(boolean enable);

    /**
     * Define the browser to use : true to use the internal brower, false to use the system browser.
     * Define the browser to use with Enum Type: DESKTOP_VIEWER, INTERNAL_BROWSER, BROWSER
     *
     * @param viewer
     */
    void setHelpViewer(HelpViewer viewer);

    /**
     * Get the viewer browser
     *
     * @return
     */
    HelpViewer getHelpViewer();

    /**
     * Define the path of the executable location
     *
     * @param path to executable
     */
    void setViewerDesktopPath(String path);

    /**
     * Define the host of viewer desktop url
     *
     * @param host
     */
    void setViewerDesktopServerURL(String host);

    /**
     * Define the viewer desktop window width
     *
     * @param width
     */
    void setViewerDesktopWidth(int width);

    /**
     * Define the viewer desktop window height
     *
     * @param height
     */
    void setViewerDesktopHeight(int height);

    /**
     * Show the title if enabled
     * Define the placement of popover use with Enum Type: TOP, RIGHT, BOTTOM, LEFT
     *
     * @param popoverPlacement
     */
    void setPopoverPlacement(PopoverPlacement popoverPlacement);

    /**
     * Define the behavior : If true, the component will be disabled if the content is missing
     *
     * @param enable true to enable the title display.
     */
    void setTitleDisplay(boolean enable);

    /**
     * Show the tooltip if enabled
     *
     * @param enable true to enable the tooltip display.
     */
    void setTooltipDisplay(boolean enable);

     /** 
      * Define the error behavior of popover use with Enum Type: ERROR_SHOWN, FRIENDLY_MSG, NO_POPOVER
      * Show the separator if enabled
      *
      * @param enable true to enable the separator display.
     */
    void setSeparatorDisplay(boolean enable);

    /**
     * Show the title if enabled
     *
     * @param errorBehavior
     */
    void setErrorBehavior(ErrorBehavior errorBehavior);

    /**
     * Define the icon state of popover use with Enum Type: ERROR, SHOWN, HIDDEN, DISABLED
     *
     * @param iconState
     */
    void setIconState(IconState iconState);

    /**
     * Show the related topics if enabled
     *
     * @param enable true to enable the related topics display.
     */
    void setRelatedTopicsDisplay(boolean enable);

     /** Show the article if enabled
     *
     * @param enable true to enable the article display.
     */
    void setArticleDisplay(boolean enable);
}
