package fr.techad.edc.popover.desktop;

import fr.techad.edc.popover.swing.EdcSwingHelp;
import java.io.IOException;

public interface EdcDesktop {

    /**
     * Create the desktop process
     *
     * @param path
     * @throws IOException
     * @throws InterruptedException
     */
    void createProcess(String path) throws IOException, InterruptedException;

    /**
     * Configuration of desktop process
     *
     * @param edcHelp
     * @param appPath
     * @throws IOException
     */
    void ConfigureDesktopProcess(EdcSwingHelp edcHelp, String appPath) throws IOException, InterruptedException;

    /**
     * Get the process
     *
     * @return the process
     */
    Process getProcess();

    /**
     * Shut down the edc desktop process
     *
     * @throws IOException
     */
    void shutDownDesktopProcess() throws IOException;

    /**
     * Return the desktop viewer url api
     *
     * @return the desktop viewer url api
     */
    String getDesktopViewerUrlApi();

    /**
     * Handle the viewer url post method
     *
     * @param url
     * @throws IOException
     */
    void handleDesktopPostViewerUrl(String url) throws IOException, InterruptedException;
}
