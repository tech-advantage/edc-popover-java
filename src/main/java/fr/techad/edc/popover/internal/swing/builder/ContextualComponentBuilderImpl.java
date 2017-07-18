package fr.techad.edc.popover.internal.swing.builder;

import fr.techad.edc.popover.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.injector.provider.HelpListenerProvider;
import fr.techad.edc.popover.internal.swing.components.IconButton;
import fr.techad.edc.popover.swing.HelpListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.net.URL;

/**
 * Implementation of {@link ContextualComponentBuilder} in case of swing.
 */
public class ContextualComponentBuilderImpl implements ContextualComponentBuilder<JComponent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContextualComponentBuilderImpl.class);
    private final HelpListenerProvider helpListenerProvider;

    private String mainKey;
    private String subKey;
    private String languageCode;
    private String label = null;
    private String iconPath;

    @Inject
    public ContextualComponentBuilderImpl(HelpListenerProvider helpListenerProvider) {
        this.helpListenerProvider = helpListenerProvider;
    }

    @Override
    public ContextualComponentBuilder<JComponent> setKeys(String mainKey, String subKey, String languageCode) {
        this.mainKey = mainKey;
        this.subKey = subKey;
        this.languageCode=languageCode;
        return this;
    }

    @Override
    public ContextualComponentBuilder<JComponent> setIconPath(String iconPath) {
        this.iconPath = iconPath;
        return this;
    }

    @Override
    public ContextualComponentBuilder<JComponent> setLabel(String label) {
        this.label = label;
        return this;
    }

    @Override
    public JComponent build() {
        ImageIcon imageIcon = createImageIcon(iconPath);
        IconButton iconButton = new IconButton(label, imageIcon);
        HelpListener helpListener = helpListenerProvider.get();
        helpListener.setKeysAndLanguageCode(mainKey, subKey, languageCode);
        iconButton.addMouseListener(helpListener);
        return iconButton;
    }

    /**
     * Create an {@link ImageIcon}
     *
     * @param path        the path of the icon
     * @return the ImageIcon
     */
    private ImageIcon createImageIcon(String path) {
        URL imgURL = ClassLoader.getSystemClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            LOGGER.error("Couldn't find file: {}", path);
        }
        return new ImageIcon(path);
    }
}
