package fr.techad.edc.popover.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * TECH ADVANTAGE
 * All right reserved
 * <p>
 * Utility class to open an url in a browser.
 */
public class OpenUrlAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenUrlAction.class);

    public OpenUrlAction() {
        super();
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
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(new URL(url).toURI());
        }
    }
}
