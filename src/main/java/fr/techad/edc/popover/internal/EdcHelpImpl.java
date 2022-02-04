package fr.techad.edc.popover.internal;

import fr.techad.edc.popover.EdcHelp;
import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.model.IconState;

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
    public void setTitleDisplay(boolean enable) {
        helpConfiguration.setShowTitle(enable);
    }

    @Override
    public void setErrorBehavior(ErrorBehavior errorBehavior) {
        helpConfiguration.setErrorBehavior(errorBehavior);
    }

    @Override
    public void setIconState(IconState iconState) {
        helpConfiguration.setIconState(iconState);
    }


    protected HelpConfiguration getHelpConfiguration() {
        return helpConfiguration;
    }
}
