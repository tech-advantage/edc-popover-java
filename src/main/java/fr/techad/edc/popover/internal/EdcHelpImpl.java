package fr.techad.edc.popover.internal;

import fr.techad.edc.popover.EdcHelp;
import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.model.HelpViewer;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.model.IconState;
import fr.techad.edc.popover.model.PopoverPlacement;

import java.awt.Font;
import javax.inject.Inject;
import java.awt.Font;

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
    public void setErrorIconPath(String iconPath) {
        helpConfiguration.setErrorIconPath(iconPath);
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
    public void setSummaryDisplay(boolean enable) { helpConfiguration.setSummaryDisplay(enable); }

    @Override
    public void setHoverDisplayPopover(boolean enable) { helpConfiguration.setHoverDisplayPopover(enable); }

    @Override
    public void setHelpViewer(HelpViewer state) {
        helpConfiguration.setHelpViewer(state);
    }

    @Override
    public void setViewerDesktopPath(String path) {
        helpConfiguration.setViewerDesktopPath(path);
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

    public void setPopoverPlacement(PopoverPlacement placement) {
        helpConfiguration.setPopoverPlacement(placement);
    }

    @Override
    public void setTooltipDisplay(boolean enable) {
        helpConfiguration.setShowTooltip(enable);
    }

    @Override
    public void setArticleDisplay(boolean enable) {
        helpConfiguration.setShowArticle(enable);
    }

    @Override
    public void setSeparatorDisplay(boolean enable) {
        helpConfiguration.setShowSeparator(enable);
    }

    @Override
    public void setSeparatorDisplay(boolean enable) {
        helpConfiguration.setShowSeparator(enable);
    }

    @Override
    public void setRelatedTopicsDisplay(boolean enable) {
        helpConfiguration.setShowRelatedTopics(enable);
    }
    
    @Override
    public void setPopoverSectionTitleFont(Font fontAttr) { helpConfiguration.setPopoverSectionTitleFont(fontAttr); }


    @Override
    public void setHeaderFontAttributes(Font fontAttributes) { helpConfiguration.setHeaderFontAttributes(fontAttributes); }


    protected HelpConfiguration getHelpConfiguration() {
        return helpConfiguration;
    }
}
