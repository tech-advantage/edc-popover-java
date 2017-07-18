package fr.techad.edc.popover.internal.swing.components;

import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * TECH ADVANTAGE
 * All right reserved
 * Created by cochon on 13/07/2017.
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
     * Creates a new popover in the vertical direction (arrow pointing up).
     */
    public Popover() {
        this(VERTICAL);
    }

    /**
     * Creates a new popover in the specified direction, either <code>JSPopover.VERTICAL</code> for
     * an upward-pointing arrow, or <code>JSPopover.HORIZONTAL</code> for a left-pointing arrow.
     *
     * @param direction an integer representing the direction for the arrow to point.
     */
    public Popover(int direction) {
        this.direction = direction;
        this.closablePosition = TOP;
        setLayout(new BorderLayout());
        setUndecorated(true);
        setAlwaysOnTop(true);
        setFocusableWindowState(false);
        getRootPane().putClientProperty("apple.awt.draggableWindowBackground", Boolean.FALSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.setBackground(Color.WHITE);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 1));
        contentPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(0, 5, 10, 5));
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        setClosePosition(this.closablePosition);
        add(mainPanel);
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

    public void setIconPath(String iconPath) {
        if (iconPath != null && !iconPath.equals(this.iconPath)) {
            this.iconPath = iconPath;
            setClosePosition(this.closablePosition);
        }
    }

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

    /**
     * Sets the direction for the arrow to point in, either <code>JSPopover.VERTICAL</code> for
     * an upward-pointing arrow, or <code>JSPopover.HORIZONTAL</code> for a left-pointing arrow.
     *
     * @param direction an integer representing the direction for the arrow to point.
     */
    public void setDirection(int direction) {
        if (this.direction != direction) {
            Point old = getLocationOnScreen();
            this.direction = direction;
            if (this.direction == VERTICAL) {
                setSize(getWidth() - 20, getHeight());
                old.y += (getHeight() / 2) - 10;
            } else {
                setSize(getWidth(), getHeight() - 20);
                old.x += (getWidth() / 2) - 10;
            }
            repaint();
            setLocation(old);
        }
    }

    private JComponent getHeader() {
        JPanel header = new JPanel();
        header.setBackground(contentPanel.getBackground());
        header.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton(createImageIcon(iconPath));
        closeButton.setMargin(new java.awt.Insets(1, 2, 1, 2));
        closeButton.setBorderPainted(false);
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
        JButton closeButton = new JButton(createImageIcon(iconPath));
        closeButton.addActionListener(e -> this.setVisible(false));
        header.add(closeButton);

        return header;
    }

    /**
     * Create an {@link ImageIcon}
     *
     * @param path the path of the icon
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
