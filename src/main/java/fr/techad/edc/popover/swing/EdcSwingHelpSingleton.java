package fr.techad.edc.popover.swing;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fr.techad.edc.client.EdcClient;
import fr.techad.edc.client.injector.EdcClientModule;
import fr.techad.edc.popover.desktop.EdcDesktop;
import fr.techad.edc.popover.injector.EdcPopoverModule;
import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.model.IconState;
import fr.techad.edc.popover.model.PopoverPlacement;
import fr.techad.edc.popover.model.HelpViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * This classe declare a singleton to configure and create a Swing component.
 * <p>
 * DO NOT USE IT if to use injection.
 */
public class EdcSwingHelpSingleton implements EdcSwingHelp {
    private static EdcSwingHelpSingleton instance = null;
    private EdcSwingHelp edcSwingHelp;
    private EdcClient edcClient;
    private EdcDesktop edcDesktop;
    /**
     * A private constructor to avoid to instantiate it.
     * Use the method {@link EdcSwingHelpSingleton#getInstance()}
     */
    private EdcSwingHelpSingleton() {
        super();
    }

    /**
     * Return the unique instance of this utility class
     *
     * @return the instance
     */
    public static EdcSwingHelpSingleton getInstance() {
        if (instance == null) {
            instance = new EdcSwingHelpSingleton();
            instance.init();
        }
        return instance;
    }

    /**
     * Initialize the instance.
     */
    private void init() {
        // Init the injector with Client and Popover Module.
        Injector injector = Guice.createInjector(new EdcClientModule(), new EdcPopoverModule());
        // Get the instance
        edcSwingHelp = injector.getInstance(EdcSwingHelp.class);
        edcClient = injector.getInstance(EdcClient.class);
        edcDesktop = injector.getInstance(EdcDesktop.class);
    }

    /**
     * Return the EdcClient instance.
     * <p>
     * Warning, if you don't use the injection, do not use the {@link fr.techad.edc.client.EdcClientSingleton} because
     * the injection scope is different and the instance will be different.
     *
     * @return the edc client instance.
     */
    public EdcClient getEdcClient() {
        return this.edcClient;
    }

    /**
     * Return desktopProcess instance
     *
     * @return desktopProcess instance
     */
    public EdcDesktop getEdcDesktop() {
        return this.edcDesktop;
    }

    @Override
    public JComponent createComponent(String mainKey, String subKey) {
        return edcSwingHelp.createComponent(mainKey, subKey);
    }

    @Override
    public JComponent createComponent(String mainKey, String subKey, String iconPath){
        return edcSwingHelp.createComponent(mainKey, subKey, iconPath);
    }

    @Override
    public MouseListener getMouseListener(String mainKey, String subKey) {
        return edcSwingHelp.getMouseListener(mainKey, subKey);
    }

    @Override
    public void setIconPath(String iconPath) {
        edcSwingHelp.setIconPath(iconPath);
    }

    @Override
    public void setIconDarkModePath(String iconPath) { edcSwingHelp.setIconDarkModePath(iconPath); }

    @Override
    public void setDarkMode(boolean enable) { edcSwingHelp.setDarkMode(enable); }

    @Override
    public void setCloseIconPath(String iconPath) {
        edcSwingHelp.setCloseIconPath(iconPath);
    }

    @Override
    public void setErrorIconPath(String iconPath) {
        edcSwingHelp.setErrorIconPath(iconPath);
    }

    @Override
    public void setLanguageCode(String languageCode) { edcSwingHelp.setLanguageCode(languageCode); }

    @Override
    public void setTooltipLabel(String label) {
        edcSwingHelp.setTooltipLabel(label);
    }

    @Override
    public void setPopoverDisplay(boolean enable) {
        edcSwingHelp.setPopoverDisplay(enable);
    }

    @Override
    public void setHoverDisplayPopover(boolean enable) {
        edcSwingHelp.setHoverDisplayPopover(enable);
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        edcSwingHelp.setBackgroundColor(backgroundColor);
    }

    @Override
    public void setSeparatorColor(Color separatorColor) { edcSwingHelp.setSeparatorColor(separatorColor); }

    @Override
    public void setHelpViewer(HelpViewer viewer) {
        edcSwingHelp.setHelpViewer(viewer);
    }

    @Override
    public HelpViewer getHelpViewer() {
       return edcSwingHelp.getHelpViewer();
    }

    @Override
    public void setPopoverPlacement(PopoverPlacement popoverPlacement) { edcSwingHelp.setPopoverPlacement(popoverPlacement); }
    
    @Override
    public void setViewerDesktopPath(String path) { edcSwingHelp.setViewerDesktopPath(path); }

    @Override
    public void setViewerDesktopServerURL(String url) { edcSwingHelp.setViewerDesktopServerURL(url); }

    @Override
    public void setViewerDesktopWidth(int width) { edcSwingHelp.setViewerDesktopWidth(width); }

    @Override
    public void setViewerDesktopHeight(int height) { edcSwingHelp.setViewerDesktopHeight(height); }

    @Override
    public void setSeparatorDisplay(boolean state) {
        edcSwingHelp.setSeparatorDisplay(state);
    }

    @Override
    public void setTitleDisplay(boolean enable) {
        edcSwingHelp.setTitleDisplay(enable);
    }
    
    @Override
    public void setHeaderTitleFont(Font fontAttr) { edcSwingHelp.setHeaderTitleFont(fontAttr); }

    @Override
    public void setHeaderTitleColor(Color titleColor) { edcSwingHelp.setHeaderTitleColor(titleColor); }

    @Override
    public void setPopoverDescriptionColor(Color descColor) { edcSwingHelp.setPopoverDescriptionColor(descColor); }

    @Override
    public void setPopoverDescriptionFont(Font fontAttr) { edcSwingHelp.setPopoverDescriptionFont(fontAttr); }

    @Override
    public void setPopoverSectionTitleFont(Font fontAttr) { edcSwingHelp.setPopoverSectionTitleFont(fontAttr); }

    @Override
    public void setPopoverSectionTitleColor(Color titleColor) { edcSwingHelp.setPopoverSectionTitleColor(titleColor); }
    @Override
    public void setPopoverLinksColor(Color linkColor) { edcSwingHelp.setPopoverLinksColor(linkColor); }

    @Override
    public void setPopoverLinksFont(Font fontAttr) { edcSwingHelp.setPopoverLinksFont(fontAttr); }

    @Override
    public void setErrorBehavior(ErrorBehavior errorBehavior) {
        edcSwingHelp.setErrorBehavior(errorBehavior);
    }
  
    @Override
    public void setTooltipDisplay(boolean enable) {
        edcSwingHelp.setTooltipDisplay(enable);
    }
  
    @Override
    public void setIconState(IconState iconState) {
        edcSwingHelp.setIconState(iconState);
    }
  
    @Override
    public void setRelatedTopicsDisplay(boolean enable) {
        edcSwingHelp.setRelatedTopicsDisplay(enable);
    }
  
    @Override
    public void setArticleDisplay(boolean enable) {
        edcSwingHelp.setArticleDisplay(enable);
    }
}
