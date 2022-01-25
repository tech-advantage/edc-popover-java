package fr.techad.edc.popover.swing;

import fr.techad.edc.popover.EdcHelp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Use i to create a Swing Component to display the documentation.
 */
public interface EdcSwingHelp extends EdcHelp {

    /**
     * Create a Swing component which will display the documentation.
     *
     * @param mainKey the main key
     * @param subKey  the subkey
     * @return the GUI component
     */
    JComponent createComponent(String mainKey, String subKey);

    /**
     * Create a Swing component which will display the documentation.
     *
     * @param mainKey  the main key
     * @param subKey   the subkey
     * @param iconPath the icon path to override the default
     * @return the GUI component
     */
    JComponent createComponent(String mainKey, String subKey, String iconPath);

    /**
     * Create a Swing Mouse Listener which will manage the mouse event to display the documentation
     *
     * @param mainKey the main key
     * @param subKey  the subket
     * @return the Mouse Listener
     */
    MouseListener getMouseListener(String mainKey, String subKey);

    /**
     * Define the background color for the component summary
     *
     * @param backgroundColor the color to set
     */
    void setBackgroundColor(Color backgroundColor);

    /**
     * Define the separator color for the title component
     *
     * @param separatorColor the color to set
     */
    void setSeparatorColor(Color separatorColor);

    /**
     * Define the fonts attributes
     *
     * @param fontAttributes
     */
    void setHeaderFontAttributes(Font fontAttributes);

    /**
     * Define the browser size
     *
     * @param width  the browser width
     * @param height the browser height
     */
    void setBrowserSize(int width, int height);

    /**
     * Define the header title color for the component
     *
     * @param titleColor
     */
    void setHeaderTitleColor(Color titleColor);
}
