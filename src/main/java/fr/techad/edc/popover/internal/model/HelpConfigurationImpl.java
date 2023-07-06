package fr.techad.edc.popover.internal.model;

import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.model.IconState;
import fr.techad.edc.popover.model.PopoverPlacement;
import fr.techad.edc.popover.model.HelpViewer;

import java.awt.Font;
import java.awt.Color;

/**
 * Help Configuration implementation
 */
public class HelpConfigurationImpl implements HelpConfiguration {
    private String iconPath = "icons/icon-32px.png";
    private String closeIconPath = "popover/close1.png";
    private String errorIconPath = "icons/icon_exclamation-32px.png";
    private String languageCode = "en";
    private String tooltipLabel = null;
    private boolean popoverDisplay = false;
    private boolean hoverPopoverDisplay = false;
    private int backgroundColor;
    private int widthBrowser = 1024;
    private int heightBrowser = 600;
    private int underlineColor;
    private boolean showTitle = true;
    private ErrorBehavior errorBehavior = ErrorBehavior.FRIENDLY_MSG;
    private IconState iconState = IconState.SHOWN;
    private boolean showTooltip = true;
    private boolean showRelatedTopics = true;
    private Font popoverSectionTitleFont = new Font("Dialog", Font.BOLD, 12);
    private Color popoverSectionTitleColor = Color.BLACK;
    private boolean showArticle = true;
    private PopoverPlacement popoverPlacement;
    private boolean showSeparator = true;
    private HelpViewer helpViewer;
    private String desktopViewerPath = "";
    private Font headerTitleFont = new Font("Dialog", Font.BOLD, 20);
    private Color titleColor = Color.BLACK;
    private Color popoverLinksColor = Color.BLUE;
    private Font popoverLinksFont = new Font("Dialog", Font.PLAIN, 12);
    private Color popoverDescriptionColor = Color.BLACK;
    private Font popoverDescriptionFont = new Font("Dialog", Font.PLAIN, 12);
    private String viewerDesktopServerURL = "http://localhost:60000";

    public HelpConfigurationImpl() {
        this.backgroundColor = Color.WHITE.getRGB();
        this.underlineColor = new Color(60, 141, 188).getRGB();
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }

    @Override
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Override
    public String getCloseIconPath() {
        return closeIconPath;
    }

    @Override
    public void setCloseIconPath(String iconPath) {
        this.closeIconPath = iconPath;
    }

    @Override
    public String getErrorIconPath() {
        return errorIconPath;
    }

    @Override
    public void setErrorIconPath(String iconPath) {
        this.errorIconPath = iconPath;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public String getTooltipLabel() {
        return tooltipLabel;
    }

    @Override
    public void setTooltipLabel(String label) {
        this.tooltipLabel = label;
    }

    @Override
    public boolean getPopoverDisplay() { return this.popoverDisplay; }

    @Override
    public void setPopoverDisplay(boolean enabled) { this.popoverDisplay = enabled; }

    @Override
    public boolean isHoverDisplayPopover() {
        return this.hoverPopoverDisplay;
    }

    @Override
    public void setHoverDisplayPopover(boolean enabled) {
        this.hoverPopoverDisplay = enabled;
    }

    @Override
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public int getUnderlineColor() {
        return this.underlineColor;
    }

    @Override
    public void setUnderlineColor(int underlineColor) {
        this.underlineColor = underlineColor;
    }

    @Override
    public HelpViewer getHelpViewer(){
        return this.helpViewer;
    }
  
    @Override
    public PopoverPlacement getPopoverPlacement() {
        if(this.popoverPlacement == null) {
            this.popoverPlacement = popoverPlacement.BOTTOM;
        }
        return this.popoverPlacement;
    }

    @Override
    public void setPopoverPlacement(PopoverPlacement placement) {
        this.popoverPlacement = placement;
    }
    
    @Override
    public void setHelpViewer(HelpViewer viewer) { this.helpViewer = viewer; }

    @Override
    public void setViewerDesktopPath(String desktopViewerPath) {
        this.desktopViewerPath = desktopViewerPath;
    }

    @Override
    public String getViewerDesktopServerURL() { return viewerDesktopServerURL; }

    @Override
    public void setViewerDesktopServerURL(String url) { this.viewerDesktopServerURL = url; }

    @Override
    public String getViewerDesktopPath() { return this.desktopViewerPath; }

    @Override
    public ErrorBehavior getErrorBehavior() {
        return this.errorBehavior;
    }

    @Override
    public void setErrorBehavior(ErrorBehavior errorBehavior) { this.errorBehavior = errorBehavior; }

    @Override
    public IconState getIconState() { return this.iconState; }

    @Override
    public void setIconState(IconState iconState) { this.iconState = iconState; }

    @Override
    public boolean isShowSeparator() { return showSeparator; }

    @Override
    public void setShowSeparator(boolean showSeparator) { this.showSeparator = showSeparator; }
    
    @Override
    public boolean isShowTooltip() { return showTooltip; }

    @Override
    public void setShowTooltip(boolean showTooltip) { this.showTooltip = showTooltip; }
    
    @Override
    public boolean isShowTitle() { return showTitle; }

    @Override
    public void setShowTitle(boolean showTitle) { this.showTitle = showTitle; }

    @Override
    public boolean isShowRelatedTopics() {
        return showRelatedTopics;
    }

    @Override
    public void setShowRelatedTopics(boolean showRelatedTopics) {
        this.showRelatedTopics = showRelatedTopics;
    }
    
    @Override
    public Font getPopoverSectionTitleFont() { return popoverSectionTitleFont; }

    @Override
    public void setPopoverSectionTitleFont(Font fontAttr) { this.popoverSectionTitleFont = fontAttr; }

    @Override
    public Color getPopoverSectionTitleColor() {
        return popoverSectionTitleColor;
    }

    @Override
    public void setPopoverSectionTitleColor(Color titleColor) {
        this.popoverSectionTitleColor = titleColor;
    }

    @Override
    public boolean isShowArticle() {
        return showArticle;
    }

    @Override
    public void setShowArticle(boolean showArticle) {
        this.showArticle = showArticle;
    }

    @Override
    public Font getHeaderTitleFont() { return headerTitleFont; }

    @Override
    public void setHeaderTitleFont(Font fontAttr) { this.headerTitleFont = fontAttr; }

    @Override
    public Color getHeaderTitleColor() { return titleColor; }

    @Override
    public void setHeaderTitleColor(Color titleColor) { this.titleColor = titleColor; }

    @Override
    public void setPopoverLinksColor(Color linksColor) { this.popoverLinksColor = linksColor; }
    @Override
    public Color getPopoverLinksColor() { return popoverLinksColor; }

    @Override
    public void setPopoverLinksFont(Font fontAttr) { this.popoverLinksFont = fontAttr; }

    @Override
    public Font getPopoverLinksFont() { return popoverLinksFont; }

    @Override
    public void setPopoverDescriptionColor(Color descColor) { this.popoverDescriptionColor = descColor; }

    @Override
    public Color getPopoverDescriptionColor() { return popoverDescriptionColor; }

    @Override
    public void setPopoverDescriptionFont(Font fontAttr) { this.popoverDescriptionFont = fontAttr; }

    @Override
    public Font getPopoverDescriptionFont() { return popoverDescriptionFont; }
}
