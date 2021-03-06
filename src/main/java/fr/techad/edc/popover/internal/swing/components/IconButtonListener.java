package fr.techad.edc.popover.internal.swing.components;

import fr.techad.edc.client.EdcClient;
import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.builder.ContextualContentComponentBuilder;
import fr.techad.edc.popover.builder.ContextualTitleComponentBuilder;
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
    private final ContextualTitleComponentBuilder<JComponent> contextualTitleComponentBuilder;
    private final Popover popover;
    private final OpenUrlAction openUrlAction;

    private String mainKey;
    private String subKey;

    @Inject
    public IconButtonListener(EdcClient edcClient,
                              HelpConfiguration helpConfiguration,
                              ContextualContentComponentBuilder<JComponent> contextualContentComponentBuilder,
                              ContextualTitleComponentBuilder<JComponent> contextualTitleComponentBuilder,
                              Popover popover,
                              OpenUrlAction openUrlAction) {
        this.edcClient = edcClient;
        this.helpConfiguration = helpConfiguration;
        this.contextualContentComponentBuilder = contextualContentComponentBuilder;
        this.contextualTitleComponentBuilder = contextualTitleComponentBuilder;
        this.popover = popover;
        this.openUrlAction = openUrlAction;
    }

    @Override
    public void setKeys(String mainKey, String subKey) {
        this.mainKey = mainKey;
        this.subKey = subKey;
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
            url = edcClient.getContextWebHelpUrl(mainKey, subKey, this.helpConfiguration.getLanguageCode());
            openUrlAction.openUrl(url);
        } catch (InvalidUrlException e) {
            LOGGER.error("Impossible to get the url for key ({}, {}) and languageCode: {}", mainKey, subKey, this.helpConfiguration.getLanguageCode());
        } catch (URISyntaxException e) {
            LOGGER.error("Impossible to open the browser with url:{}", url);
        } catch (IOException e) {
            LOGGER.error("Error on IO", e);
        }
    }

    private void openPopover(int x, int y) {
        try {
            ContextItem contextItem = edcClient.getContextItem(mainKey, subKey, this.helpConfiguration.getLanguageCode());
            if (contextItem != null || !helpConfiguration.isAutoDisabledInMissingContent()) {
                JComponent jBodyComponent = contextualContentComponentBuilder.setContextItem(contextItem).setBackgroundColor(helpConfiguration.getBackgroundColor()).build();
                JComponent jTitleComponent = contextualTitleComponentBuilder.setContextItem(contextItem).setBackgroundColor(helpConfiguration.getBackgroundColor()).enableTitle(helpConfiguration.isShowTitle()).build();
                Color bgColor = new Color(helpConfiguration.getBackgroundColor());
                popover.setContentBackground(bgColor);
                popover.setSeparatorColor(helpConfiguration.isShowTitle() ? new Color(helpConfiguration.getUnderlineColor()) : bgColor);
                popover.clear();
                popover.setTitle(jTitleComponent);
                popover.add(jBodyComponent);
                popover.setIconPath(helpConfiguration.getCloseIconPath());
                popover.pack();
                popover.setVisible(true);
                popover.setLocation(x, y);
                LOGGER.debug("Popover size: {}", popover.getSize());
                LOGGER.debug("component size: {}", jBodyComponent.getSize());
            }
        } catch (InvalidUrlException | IOException e) {
            LOGGER.error("Impossible to get the context item for key ({}, {}) and languageCode: {}", mainKey, subKey, this.helpConfiguration.getLanguageCode());
        }
    }
}
