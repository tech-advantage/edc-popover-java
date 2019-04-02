package fr.techad.edc.popover.internal.swing.builder;

import fr.techad.edc.client.model.ContextItem;
import fr.techad.edc.popover.builder.ContextualTitleComponentBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * TECH ADVANTAGE
 * All right reserved
 * Created by cochin on 02/04/2019.
 */
public class ContextualTitleComponentBuilderImpl implements ContextualTitleComponentBuilder<JComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextualTitleComponentBuilderImpl.class);
    private ContextItem contextItem;
    private boolean showTitle = true;
    private Color backgroundColor = Color.WHITE;

    @Override
    public ContextualTitleComponentBuilder<JComponent> setContextItem(ContextItem contextItem) {
        this.contextItem = contextItem;
        LOGGER.debug("Set Context Item: {}", contextItem);
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> setBackgroundColor(int rgbColor) {
        this.backgroundColor = new Color(rgbColor);
        LOGGER.debug("Set background color: {}", this.backgroundColor);
        return this;
    }

    @Override
    public ContextualTitleComponentBuilder<JComponent> enableTitle(boolean showTitle) {
        this.showTitle = showTitle;
        return this;
    }


    @Override
    public JComponent build() {
        LOGGER.debug("Build the context item: {}", contextItem != null ? contextItem.getLabel() : "null");

        JPanel container = new JPanel();
        container.setBackground(backgroundColor);
        container.setLayout(new BorderLayout());
        container.add(getBody(), BorderLayout.CENTER);
        return container;
    }

    private JComponent getBody() {
        JLabel label;
        String title = StringUtils.EMPTY;
        if (this.showTitle) {
            if (contextItem != null) {
                title = contextItem.getLabel();
            } else {
                title = "No title";
            }
        }
        label = new JLabel(title);
        Font labelFont = label.getFont();
        Font boldFont = labelFont.deriveFont(labelFont.getStyle() ^ Font.BOLD);
        Font font = boldFont.deriveFont(labelFont.getSize() + 5.0f);
        label.setFont(font);
        label.setBorder(BorderFactory.createEmptyBorder(8, 10, 0, 10));
        return label;
    }
}
