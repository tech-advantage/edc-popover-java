package fr.techad.edc.popover.desktop;

import java.io.IOException;

public interface DesktopProcess {

    /**
     * Create the desktop process
     */
    void createProcess(String path) throws IOException;

    /**
     * Get the process
     *
     * @return the process
     */
    Process getProcess();
}
