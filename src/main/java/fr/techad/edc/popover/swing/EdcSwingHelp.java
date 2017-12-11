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
     * @param subKey  the subket
     * @return the GUI component
     */
    JComponent createComponent(String mainKey, String subKey);

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
}
