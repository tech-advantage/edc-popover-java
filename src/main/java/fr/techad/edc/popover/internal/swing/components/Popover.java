package fr.techad.edc.popover.internal.swing.components;

import fr.techad.edc.popover.internal.swing.tools.ImageIconCreator;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * Popover to display the documentation.
 */
public class Popover extends JFrame {
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 0;

    public static final int TOP = 0;
    public static final int BOTTOM = 1;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Popover.class);
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JComponent closableComponent;
    private int direction;
    private int closablePosition;
    private String iconPath = "popover/close1.png";

    /**
     * Creates a new popover in the vertical direction (pad the popover on X Axis)
     */
    public Popover() {
        this(VERTICAL);
    }

    /**
     * Creates a new popover with a specific direction (create a padding according the direction)
     *
     * @param direction an integer representing the direction for the padding.
     */
    public Popover(int direction) {
        this.direction = direction;
        this.closablePosition = TOP;
        setLayout(new BorderLayout());
        setUndecorated(true);
        setAlwaysOnTop(true);
        setFocusableWindowState(true);
        getRootPane().putClientProperty("apple.awt.draggableWindowBackground", Boolean.FALSE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 1));
        contentPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(0, 8, 8, 5));
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        setClosePosition(this.closablePosition);

        add(mainPanel);

        this.addWindowFocusListener(new WindowFocusListener() {
            private boolean gained = false;

            @Override
            public void windowGainedFocus(WindowEvent e) {
                gained = true;
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                if (gained) {
                    Popover.this.setVisible(false);
                    gained = false;
                }
            }
        });
    }

    /**
     * Sets the color for the background of the usable portion of the popover.
     *
     * @param c the color to use for the popover's background.
     */
    public void setContentBackground(Color c) {
        LOGGER.debug("Define new content background color: {}", c);
        contentPanel.setBackground(c);
        mainPanel.setBackground(c);
    }

    /**
     * Define the icon path for the close button
     *
     * @param iconPath the icon path
     */
    public void setIconPath(String iconPath) {
        if (iconPath != null && !iconPath.equals(this.iconPath)) {
            this.iconPath = iconPath;
            setClosePosition(this.closablePosition);
        }
    }

    /**
     * Define the position for the close button : TOP or BOTTOM position.
     *
     * @param closePosition the TOP or BOTTOM position.
     */
    public final void setClosePosition(int closePosition) {
        if (this.closableComponent != null)
            mainPanel.remove(this.closableComponent);
        String borderLayout = BorderLayout.NORTH;
        if (closePosition == TOP) {
            this.closableComponent = getHeader();
        } else {
            this.closableComponent = getFooter();
            borderLayout = BorderLayout.SOUTH;
        }
        this.closablePosition = closePosition;
        mainPanel.add(this.closableComponent, borderLayout);
    }

    @Override
    public void setLocation(int x, int y) {
        LOGGER.debug("actual location: {}", getLocation());
        LOGGER.debug("new location: ({}, {})", x, y);
        int width = getWidth();
        int height = getHeight();

        GraphicsConfiguration config = this.getGraphicsConfiguration();
        GraphicsDevice currentDevice = config.getDevice();
        int widthDisplay = currentDevice.getDisplayMode().getWidth();
        int heightDisplay = currentDevice.getDisplayMode().getHeight();

        int newX = x;
        int newY = y;
        LOGGER.debug("width: {}, height: {}, widthDisplay: {}, heightDisplay: {}, currentDevice: {}", width, height, widthDisplay, heightDisplay, currentDevice);

        int padX = 0;
        int padY = 0;
        boolean reverseX = false;
        boolean reverseY = false;

        if (direction == HORIZONTAL)
            padY = 5;
        else
            padX = 5;

        LOGGER.debug("full width: {}", x + width + padX);
        if (x + width + padX > widthDisplay) {
            newX = x - width;
            reverseX = true;
            LOGGER.debug("Reverse width, newX: {}", newX);
        }
        if (y + height + padY > heightDisplay) {
            newY = y - height;
            reverseY = true;
        }
        if (direction == HORIZONTAL)
            newY = newY + (reverseY ? -padY : padX);
        else
            newX = newX + (reverseX ? -padX : padX);
        LOGGER.debug("New computed location: ({}, {})", newX, newY);

        super.setLocation(newX, newY);
    }

    /**
     * Clear the content panel.
     */
    public void clear() {
        contentPanel.removeAll();
    }

    @Override
    public Component add(Component comp) {
        if (comp != mainPanel) {
            LOGGER.debug("Add component in the contentPanel inside the popover");
            return contentPanel.add(comp);
        } else {
            LOGGER.debug("Add component in Popover");
            return super.add(comp);
        }
    }

    private JComponent getHeader() {
        JPanel header = new JPanel();
        header.setBackground(contentPanel.getBackground());
        header.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        ImageIcon imageIcon = ImageIconCreator.createImageIcon(iconPath);
        IconButton closeButton = new IconButton("Close", imageIcon);
        closeButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setOpaque(false);
        closeButton.setBackground(contentPanel.getBackground());
        closeButton.addActionListener(e -> this.setVisible(false));
        header.add(closeButton);

        return header;
    }

    private JComponent getFooter() {
        JPanel header = new JPanel();
        header.setBackground(contentPanel.getBackground());
        header.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        JButton closeButton = new JButton(ImageIconCreator.createImageIcon(iconPath));
        closeButton.addActionListener(e -> this.setVisible(false));
        header.add(closeButton);

        return header;
    }
}
