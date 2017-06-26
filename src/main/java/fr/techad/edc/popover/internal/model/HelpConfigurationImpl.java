package fr.techad.edc.popover.internal.model;

import fr.techad.edc.popover.model.HelpConfiguration;

/**
 * Help Configuration implementation
 */
public class HelpConfigurationImpl implements HelpConfiguration {
    private String iconPath = "icons/icon-32px.png";
    private String languageCode = "en";

    @Override
    public String getIconPath() {
        return iconPath;
    }

    @Override
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
