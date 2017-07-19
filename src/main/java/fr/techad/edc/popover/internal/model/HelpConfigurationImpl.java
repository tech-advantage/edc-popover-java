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

    public HelpConfigurationImpl() {
        this.backgroundColor = Color.WHITE.getRGB();
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
    public boolean isInternalBrowser() {
        return internalBrowser;
    }

    @Override
    public void setInternalBrowser(boolean state) {
        this.internalBrowser = state;
    }


}
