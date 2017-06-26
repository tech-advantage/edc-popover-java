package fr.techad.edc.popover.internal.swing.builder;

import fr.techad.edc.popover.injector.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.injector.provider.HelpActionListenerProvider;
import fr.techad.edc.popover.internal.swing.components.IconButton;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.swing.HelpActionListener;
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
    private final HelpActionListenerProvider helpActionListenerProvider;
    private final HelpConfiguration helpConfiguration;
    private String mainKey;
    private String subKey;

    @Inject
    public ContextualComponentBuilderImpl(HelpActionListenerProvider helpActionListenerProvider, HelpConfiguration helpConfiguration) {
        this.helpActionListenerProvider = helpActionListenerProvider;
        this.helpConfiguration = helpConfiguration;
    }

    @Override
    public ContextualComponentBuilder<JComponent> setKeys(String mainKey, String subKey) {
        this.mainKey = mainKey;
        this.subKey = subKey;
        return this;
    }

    @Override
    public JComponent build() {
        ImageIcon imageIcon = createImageIcon(helpConfiguration.getIconPath(), null);
        IconButton iconButton = new IconButton(imageIcon);
        HelpActionListener helpActionListener = helpActionListenerProvider.get();
        helpActionListener.setKeysAndLanguageCode(mainKey, subKey, helpConfiguration.getLanguageCode());
        iconButton.addActionListener(helpActionListener);
        return iconButton;
    }

    /**
     * Create an {@link ImageIcon}
     * @param path the path of the icon
     * @param description the description
     * @return the ImageIcon
     */
    private ImageIcon createImageIcon(String path, String description) {
        URL imgURL = ClassLoader.getSystemClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            LOGGER.error("Couldn't find file: {}", path);
        }
        return new ImageIcon(path, description);
    }
}
