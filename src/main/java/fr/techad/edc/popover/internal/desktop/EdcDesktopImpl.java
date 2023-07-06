package fr.techad.edc.popover.internal.desktop;

import fr.techad.edc.popover.HttpRestRequest;
import fr.techad.edc.popover.desktop.EdcDesktop;
import fr.techad.edc.popover.model.HelpConfiguration;
import fr.techad.edc.popover.model.HelpViewer;
import fr.techad.edc.popover.swing.EdcSwingHelp;
import fr.techad.edc.popover.utils.StreamGobbler;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.Executors;

public class EdcDesktopImpl implements EdcDesktop {

    private Process edcDesktopProcess;
    private HttpRestRequest httpRequest;
    private String desktopViewerApiPath = "http://localhost:60000";
    private HelpConfiguration helpConfiguration;

    @Inject
    public EdcDesktopImpl(HttpRestRequest httpRequest, HelpConfiguration helpConfiguration) {
        this.httpRequest = httpRequest;
        this.helpConfiguration = helpConfiguration;
    }

    @Override
    public void createProcess(String path) throws IOException {
        if(edcDesktopProcess == null || !edcDesktopProcess.isAlive()){
            edcDesktopProcess = Runtime.getRuntime().exec(path);
        }
    }

    public void ConfigureDesktopProcess(EdcSwingHelp edcHelp, String appPath) throws IOException {
        this.createProcess(appPath);
        if (edcDesktopProcess.isAlive())
        {
            edcHelp.setViewerDesktopPath(appPath);
        }
    }
    @Override
    public void shutDownDesktopProcess() throws IOException {
        if(helpConfiguration.getHelpViewer() == HelpViewer.EDC_DESKTOP_VIEWER){
            String shutDownApiPath = desktopViewerApiPath + "/api/helpviewer/shutdown";
            this.httpRequest.postData(shutDownApiPath, "{\"shutDown\": true}");
        }
    }

    @Override
    public Process getProcess() {
        return edcDesktopProcess;
    }

    @Override
    public String getDesktopViewerUrlApi() {
        if(!helpConfiguration.getViewerDesktopServerURL().isEmpty()){
            desktopViewerApiPath = helpConfiguration.getViewerDesktopServerURL();
        }
        return desktopViewerApiPath;
    }

    public void handleDesktopPostViewerUrl(String url) throws IOException {
        String viewerUrlApi = this.getDesktopViewerUrlApi() + "/api";
        String data = "{\"url\": \"" + url + "\"}";

        if (!edcDesktopProcess.isAlive()) {
            this.createProcess(helpConfiguration.getViewerDesktopPath());
            StreamGobbler streamGobbler = new StreamGobbler(edcDesktopProcess.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
        }
        this.httpRequest.postData(viewerUrlApi + "/helpviewer", data);
    }
}
