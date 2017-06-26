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
    public void setLanguageCode(String languageCode) {
        helpConfiguration.setLanguageCode(languageCode);
    }
}
