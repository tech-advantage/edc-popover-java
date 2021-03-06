package fr.techad.edc.popover.internal.model;

import fr.techad.edc.popover.model.HelpConfiguration;

import java.awt.*;

/**
 * Help Configuration implementation
 */
public class HelpConfigurationImpl implements HelpConfiguration {
    private String iconPath = "icons/icon-32px.png";
    private String closeIconPath = "popover/close1.png";
    private String languageCode = "en";
    private String tooltipLabel = null;
    private boolean summaryDisplay = false;
    private int backgroundColor;
    private boolean internalBrowser = true;
    private boolean autoDisabled = true;
    private int widthBrowser = 1024;
    private int heightBrowser = 600;
    private int underlineColor;
    private boolean showTitle = true;

    public HelpConfigurationImpl() {
        this.backgroundColor = Color.WHITE.getRGB();
        this.underlineColor = new Color(60, 141, 188).getRGB();
    }

    @Override
    public int getWidthBrowser() {
        return widthBrowser;
    }

    @Override
    public void setWidthBrowser(int width) {
        this.widthBrowser = width;
    }

    @Override
    public int getHeightBrowser() {
        return heightBrowser;
    }

    @Override
    public void setHeightBrowser(int height) {
        this.heightBrowser = height;
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
    public boolean getSummaryDisplay() {
        return this.summaryDisplay;
    }

    @Override
    public void setSummaryDisplay(boolean enabled) {
        this.summaryDisplay = enabled;
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
    public boolean isInternalBrowser() {
        return internalBrowser;
    }

    @Override
    public void setInternalBrowser(boolean state) {
        this.internalBrowser = state;
    }

    @Override
    public boolean isAutoDisabledInMissingContent() {
        return autoDisabled;
    }

    @Override
    public void setAutoDisabledInMissingContent(boolean state) {
        this.autoDisabled = state;
    }

    @Override
    public boolean isShowTitle() {
        return showTitle;
    }

    @Override
    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }
}
