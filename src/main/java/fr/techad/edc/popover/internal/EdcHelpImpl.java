package fr.techad.edc.popover.internal;

import fr.techad.edc.popover.EdcHelp;
import fr.techad.edc.popover.model.HelpConfiguration;

import javax.inject.Inject;

/**
 * Edc Help Implementation.
 */
public class EdcHelpImpl implements EdcHelp {
    private final HelpConfiguration helpConfiguration;

    @Inject
    public EdcHelpImpl(HelpConfiguration helpConfiguration) {
        this.helpConfiguration = helpConfiguration;
    }

    @Override
    public void setIconPath(String iconPath) {
        helpConfiguration.setIconPath(iconPath);
    }

    @Override
    public void setCloseIconPath(String iconPath) {
        helpConfiguration.setCloseIconPath(iconPath);
    }

    @Override
    public void setLanguageCode(String languageCode) {
        helpConfiguration.setLanguageCode(languageCode);
    }

    @Override
    public void setTooltipLabel(String label) {
        helpConfiguration.setTooltipLabel(label);
    }

    @Override
    public void setSummaryDisplay(boolean enable) {
        helpConfiguration.setSummaryDisplay(enable);
    }

    @Override
    public void setInternalBrowser(boolean state) {
        helpConfiguration.setInternalBrowser(state);
    }

    @Override
    public void setAutoDisabledMode(boolean state) {
        helpConfiguration.setAutoDisabledInMissingContent(state);
    }

    @Override
    public void setTooltipDisplay(boolean enable) {
        helpConfiguration.setShowTooltip(enable);
    }

    @Override
    public void setTitleDisplay(boolean enable) {
        helpConfiguration.setShowTitle(enable);
    }

    protected HelpConfiguration getHelpConfiguration() {
        return helpConfiguration;
    }
}
