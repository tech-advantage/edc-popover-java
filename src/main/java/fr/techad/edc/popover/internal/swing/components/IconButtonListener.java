package fr.techad.edc.popover.internal.swing.components;

import fr.techad.edc.client.EdcClient;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.swing.HelpActionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Implementation of {@link HelpActionListener} for the {@link IconButton}
 */
public class IconButtonListener implements HelpActionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(IconButton.class);
    private EdcClient edcClient;

    private String maiKey;
    private String subKey;
    private String languageCode;

    @Inject
    public IconButtonListener(EdcClient edcClient) {
        this.edcClient = edcClient;
    }

    @Override
    public void setKeysAndLanguageCode(String mainKey, String subKey, String languageCode) {
        this.maiKey = mainKey;
        this.subKey = subKey;
        this.languageCode = languageCode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                String url = edcClient.getContextWebHelpUrl(maiKey, subKey, languageCode);
                try {
                    desktop.browse(new URL(url).toURI());
                } catch (URISyntaxException | IOException ex) {
                    LOGGER.error("Impossible to open the browser with url:{}", url);
                }
            } catch (InvalidUrlException | IOException e1) {
                LOGGER.error("Impossible to get the url for key ({}, {}) and languageCode: {}", maiKey, subKey, languageCode);
            }
        }
    }
}
