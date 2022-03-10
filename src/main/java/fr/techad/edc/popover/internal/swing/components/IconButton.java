package fr.techad.edc.popover.internal.swing.components;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Define a extra button to add effect for the help.
 * <p>
 * On mouse over, the icon is scaled.
 */
public class IconButton extends JButton {
    private static final Logger LOGGER = LoggerFactory.getLogger(IconButton.class);

    /**
     * The constructor
     *
     * @param icon  the icon to display
     */
    public IconButton(String label, Icon icon) {
        super(icon);
        if (StringUtils.isNotBlank(label))
            setToolTipText(label);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusable(false);
    }


    @Override
    public void setIcon(Icon defaultIcon) {
        super.setIcon(defaultIcon);
        setMargin(new Insets(0, 0, 0, 0));
        final Dimension size = new Dimension(defaultIcon.getIconWidth() + 4, defaultIcon.getIconHeight() + 4);
        setPreferredSize(size);
        setMaximumSize(size);

        if (defaultIcon instanceof ImageIcon) {
            try {
                ImageIcon scaled = getScaledIcon((ImageIcon) defaultIcon, defaultIcon.getIconWidth() + 4, defaultIcon.getIconHeight() + 4, Image.SCALE_SMOOTH);
                setRolloverIcon(scaled);
            } catch (Exception ex) {
                LOGGER.error("Exception on scaling");
            }
        }
    }

    /**
     * Create a scaled image from base icon.
     *
     * @param baseIcon the icon to scale
     * @param width    the new width
     * @param height   the new height
     * @param hints    the new hints
     * @return the scaled icon
     */
    private ImageIcon getScaledIcon(ImageIcon baseIcon, int width, int height, int hints) {
        final Image scaledImage = baseIcon.getImage().getScaledInstance(width, height, hints);
        return new ImageIcon(scaledImage);
    }
}
