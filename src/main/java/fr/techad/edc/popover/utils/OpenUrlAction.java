package fr.techad.edc.popover.utils;

import fr.techad.edc.popover.browser.Browser;
import fr.techad.edc.popover.model.HelpViewer;
import fr.techad.edc.popover.model.HelpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

/**
 * TECH ADVANTAGE
 * All right reserved
 * <p>
 * Utility class to open an url in a browser.
 */
public class OpenUrlAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenUrlAction.class);

    private final Browser browser;
    private final HelpConfiguration helpConfiguration;

    @Inject
    public OpenUrlAction(Browser browser, HelpConfiguration helpConfiguration) {
        super();
        this.browser = browser;
        this.helpConfiguration = helpConfiguration;
    }

    /**
     * Open the url in a browser.
     *
     * @param url the url to open
     * @throws IOException        if the connexion failed
     * @throws URISyntaxException the url is malformed
     */
    public void openUrl(String url) throws IOException, URISyntaxException {
        LOGGER.debug("Open the url: {}", url);

        if(helpConfiguration.getHelpViewer() == HelpViewer.EDC_DESKTOP_VIEWER){

            try
            {
                if(helpConfiguration.getViewerDesktopPath().isEmpty()){
                    LOGGER.error("The path of the application must be entered");
                }else{
                    Process process = new ProcessBuilder(helpConfiguration.getViewerDesktopPath(), "edchelp", "fr.edc.ide.eclipse.help").start();
                    InputStream is = process.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String line;



                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }

                    LOGGER.info("Desktop viewer is running");
                }
            }
            catch (IOException e)
            {
                LOGGER.error(String.valueOf(e));
            }
        }else if(helpConfiguration.getHelpViewer() == HelpViewer.EMBEDDED_VIEWER){
            browser.setSize(helpConfiguration.getWidthBrowser(), helpConfiguration.getHeightBrowser());
            browser.showBrowser(true);
            browser.loadURL(url);
        }else{
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URL(url).toURI());
            }
        }
    }
}
