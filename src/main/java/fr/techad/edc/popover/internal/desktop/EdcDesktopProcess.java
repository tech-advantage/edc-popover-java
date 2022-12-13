package fr.techad.edc.popover.internal.desktop;

import fr.techad.edc.popover.desktop.DesktopProcess;

import java.io.IOException;

public class EdcDesktopProcess implements DesktopProcess {

    private Process edcDesktopProcess;

    @Override
    public void createProcess(String path) throws IOException {
        if(edcDesktopProcess == null || !edcDesktopProcess.isAlive()){
            edcDesktopProcess = Runtime.getRuntime().exec(path);
        }
    }

    @Override
    public Process getProcess() {
        return edcDesktopProcess;
    }
}
