package fr.techad.edc.popover.internal.swing.builder;

import fr.techad.edc.popover.builder.ContextualComponentBuilder;
import fr.techad.edc.popover.injector.provider.HelpListenerProvider;
import fr.techad.edc.popover.internal.swing.components.IconButton;
import fr.techad.edc.popover.internal.swing.tools.ImageIconCreator;
import fr.techad.edc.popover.model.ErrorBehavior;
import fr.techad.edc.popover.model.IconState;
import fr.techad.edc.popover.swing.HelpListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;

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
    private String errorIconPath;
    private ErrorBehavior errorBehavior;
    private IconState iconState;
    private boolean enableMainKey;
    private boolean showTooltip = true;

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
    public ContextualComponentBuilder<JComponent> setEnableContextItem(boolean enable) {
        this.enableMainKey = enable;
        return this;
    }

    @Override
    public ContextualComponentBuilder<JComponent> setErrorIconPath(String iconPath) {
        this.errorIconPath = iconPath;
        return this;
    }

    @Override
    public ContextualComponentBuilder<JComponent> setErrorBehavior(ErrorBehavior errorBehavior) {
        this.errorBehavior = errorBehavior;
        return this;
    }

    @Override
    public ContextualComponentBuilder<JComponent> setIconState(IconState iconState) {
        this.iconState = iconState;
        return this;
    }

    @Override
    public ContextualComponentBuilder<JComponent> setLabel(String label) {
        this.label = label;
        return this;
    }

    @Override
    public JComponent build(){
        ImageIcon imageIcon = ImageIconCreator.
                createImageIcon(errorBehavior == ErrorBehavior.NO_POPOVER && !enableMainKey ||
                                iconState == IconState.HIDDEN && !enableMainKey ?
                                "" : iconState == IconState.ERROR && !enableMainKey ?
                                errorIconPath : iconPath
                );
        IconButton iconButton = new IconButton(label, imageIcon);
    }
  
    @Override
    public ContextualComponentBuilder<JComponent> showTooltip(boolean enable) {
        this.showTooltip = enable;
        return this;
    }

    @Override
    public JComponent build() {
        ImageIcon imageIcon = ImageIconCreator.createImageIcon(iconPath);
        IconButton iconButton = new IconButton(showTooltip ? label : null, imageIcon);
        HelpListener helpListener = helpListenerProvider.get();
        helpListener.setKeys(mainKey, subKey);
        iconButton.addMouseListener(helpListener);
        return iconButton;
    }
}
