package fr.techad.edc.popover.internal.swing.components;

import fr.techad.edc.client.EdcClient;
import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.builder.ContextualContentComponentBuilder;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.swing.HelpListener;
import fr.techad.edc.popover.utils.OpenUrlAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Implementation of {@link HelpListener} for the {@link IconButton}
 */
public class IconButtonListener implements HelpListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(IconButton.class);
    private final EdcClient edcClient;
    private final HelpConfiguration helpConfiguration;
    private final ContextualContentComponentBuilder<JComponent> contextualContentComponentBuilder;
    private final Popover popover;
    private final OpenUrlAction openUrlAction;

    private String mainKey;
    private String subKey;
    private String languageCode;

    @Inject
    public IconButtonListener(EdcClient edcClient,
                              HelpConfiguration helpConfiguration,
                              ContextualContentComponentBuilder<JComponent> contextualContentComponentBuilder,
                              Popover popover,
                              OpenUrlAction openUrlAction) {
        this.edcClient = edcClient;
        this.helpConfiguration = helpConfiguration;
        this.contextualContentComponentBuilder = contextualContentComponentBuilder;
        this.popover = popover;
        this.openUrlAction = openUrlAction;
    }

    @Override
    public void setKeysAndLanguageCode(String mainKey, String subKey, String languageCode) {
        this.mainKey = mainKey;
        this.subKey = subKey;
        this.languageCode = languageCode;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.helpConfiguration.getSummaryDisplay()) {
            openPopover(e.getXOnScreen(), e.getYOnScreen());
        } else {
            openBrowser();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void openBrowser() {
        String url = "";
        try {
            url = edcClient.getContextWebHelpUrl(mainKey, subKey, languageCode);
            openUrlAction.openUrl(url);
        } catch (InvalidUrlException e) {
            LOGGER.error("Impossible to get the url for key ({}, {}) and languageCode: {}", mainKey, subKey, languageCode);
        } catch (URISyntaxException e) {
            LOGGER.error("Impossible to open the browser with url:{}", url);
        } catch (IOException e) {
            LOGGER.error("Error on IO", e);
        }
    }

    private void openPopover(int x, int y) {
        try {
            ContextItem contextItem = edcClient.getContextItem(mainKey, subKey, languageCode);
            if (contextItem != null || !helpConfiguration.isAutoDisabledInMissingContent()) {
                JComponent jComponent = contextualContentComponentBuilder.setContextItem(contextItem).setBackgroundColor(helpConfiguration.getBackgroundColor()).build();
                Color color = new Color(helpConfiguration.getBackgroundColor());
                popover.setContentBackground(color);
                popover.clear();
                popover.add(jComponent);
                popover.setIconPath(helpConfiguration.getCloseIconPath());
                popover.pack();
                popover.setVisible(true);
                popover.setLocation(x, y);
                LOGGER.debug("Popover size: {}", popover.getSize());
                LOGGER.debug("component size: {}", jComponent.getSize());
            }
        } catch (InvalidUrlException | IOException e) {
            LOGGER.error("Impossible to get the context item for key ({}, {}) and languageCode: {}", mainKey, subKey, languageCode);
        }
    }
}
