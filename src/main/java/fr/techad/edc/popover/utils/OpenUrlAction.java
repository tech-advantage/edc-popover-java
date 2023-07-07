package fr.techad.edc.popover.utils;

import fr.techad.edc.popover.desktop.EdcDesktop;
import fr.techad.edc.popover.model.HelpViewer;
import fr.techad.edc.popover.model.HelpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.awt.*;
import java.io.*;
import java.net.*;

/**
 * TECH ADVANTAGE
 * All right reserved
 * <p>
 * Utility class to open an url in a browser.
 */
public class OpenUrlAction{
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenUrlAction.class);
    private final EdcDesktop edcDesktopProcess;
    private final HelpConfiguration helpConfiguration;

    @Inject
    public OpenUrlAction(EdcDesktop process, HelpConfiguration helpConfiguration) {
        super();
        this.edcDesktopProcess = process;
        this.helpConfiguration = helpConfiguration;
    }

    /**
     * Open the url in a browser.
     *
     * @param url the url to open
     * @throws IOException        if the connexion failed
     * @throws URISyntaxException the url is malformed
     */
    public void openUrl(String url) throws Exception {
        LOGGER.info("Open the url: {}", url);
        if(helpConfiguration.getHelpViewer() == HelpViewer.EDC_DESKTOP_VIEWER){
            if(helpConfiguration.getViewerDesktopPath().isEmpty()){
                LOGGER.error("The path of the application must be entered");
            }else{
                this.edcDesktopProcess.handleDesktopPostViewerUrl(url);
            }
        }else{
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URL(url).toURI());
            }
        }
    }
}
