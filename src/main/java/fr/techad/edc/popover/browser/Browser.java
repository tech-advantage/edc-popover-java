package fr.techad.edc.popover.browser;

/**
 * TECH ADVANTAGE
 * All right reserved
 * Created by cochon on 19/07/2017.
 */
public interface Browser {

    /**
     * Set the browser size
     *
     * @param width  the width
     * @param height the height
     */
    void setSize(int width, int height);

    /**
     * Load the url
     *
     * @param url the url to load
     */
    void loadURL(final String url);

    /**
     * Show the browser
     *
     * @param show the visibility
     */
    void showBrowser(boolean show);
}
