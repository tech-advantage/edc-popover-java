package fr.techad.edc.popover.utils;

import fr.techad.edc.popover.browser.Browser;
import fr.techad.edc.popover.desktop.DesktopProcess;
import fr.techad.edc.popover.model.HelpViewer;
import fr.techad.edc.popover.model.HelpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;

/**
 * TECH ADVANTAGE
 * All right reserved
 * <p>
 * Utility class to open an url in a browser.
 */
public class OpenUrlAction{
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenUrlAction.class);

    private final Browser browser;
    private final DesktopProcess edcDesktopProcess;
    private final HelpConfiguration helpConfiguration;
    private String viewerUrl;

    @Inject
    public OpenUrlAction(Browser browser, DesktopProcess process, HelpConfiguration helpConfiguration) {
        super();
        this.browser = browser;
        this.edcDesktopProcess = process;
        this.helpConfiguration = helpConfiguration;
    }

    private HttpURLConnection createHttpConnection() throws IOException {
        viewerUrl = helpConfiguration.getViewerDesktopServerURL() + "/viewerurl";
        URL obj = new URL(viewerUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        return con;
    }

    private int postViewerURL(String url) throws IOException {

        HttpURLConnection httpConnection = createHttpConnection();

        String input = "{\"url\": \"" + url + "\"}";
        // Send post request
        httpConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
        wr.writeBytes(input);
        wr.flush();
        wr.close();

        int responseCode = httpConnection.getResponseCode();
        LOGGER.debug("Sending 'POST' request to URL : " + viewerUrl);
        LOGGER.debug("Post Data : " + input);
        LOGGER.debug("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(httpConnection.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        //printing result from response
        LOGGER.debug(response.toString());

        return responseCode;
    }

    private void handleDesktopPostRequest(String url) throws IOException {
        if(!edcDesktopProcess.getProcess().isAlive()) {
            edcDesktopProcess.createProcess(helpConfiguration.getViewerDesktopPath());
            StreamGobbler streamGobbler = new StreamGobbler(edcDesktopProcess.getProcess().getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            if(edcDesktopProcess.getProcess().isAlive()){
                postViewerURL(url);
            }
        }
        postViewerURL(url);
    }

    /**
     * Open the url in a browser.
     *
     * @param url the url to open
     * @throws IOException        if the connexion failed
     * @throws URISyntaxException the url is malformed
     */
    public void openUrl(String url) throws Exception {
        LOGGER.info("Open the url: {}", url);
        if(helpConfiguration.getHelpViewer() == HelpViewer.EDC_DESKTOP_VIEWER){
            if(helpConfiguration.getViewerDesktopPath().isEmpty()){
                LOGGER.error("The path of the application must be entered");
            }else{
                handleDesktopPostRequest(url);
            }
        }else if(helpConfiguration.getHelpViewer() == HelpViewer.EMBEDDED_VIEWER){
            browser.setSize(helpConfiguration.getWidthBrowser(), helpConfiguration.getHeightBrowser());
            browser.showBrowser(true);
            browser.loadURL(url);
        }else{
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URL(url).toURI());
            }
        }
    }
}
