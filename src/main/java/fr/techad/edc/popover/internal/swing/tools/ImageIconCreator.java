package fr.techad.edc.popover.internal.swing.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.net.URL;

/**
 * TECH ADVANTAGE
 * All right reserved
 * Created by cochon on 19/07/2017.
 */
public class ImageIconCreator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageIconCreator.class);

    /**
     * Create an {@link ImageIcon}
     *
     * @param path the path of the icon
     * @return the ImageIcon
     */
    public static ImageIcon createImageIcon(String path) {
        URL imgURL = ClassLoader.getSystemClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            LOGGER.error("Couldn't find file: {}", path);
        }
        return new ImageIcon(path);
    }
}
