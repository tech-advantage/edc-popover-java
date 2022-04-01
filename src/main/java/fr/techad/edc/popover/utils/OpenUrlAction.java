package fr.techad.edc.popover.utils;

import fr.techad.edc.popover.browser.Browser;
import fr.techad.edc.popover.model.HelpViewer;
import fr.techad.edc.popover.model.HelpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
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
    private final HelpConfiguration helpConfiguration;
    private Process edcDesktopProcess = null;

    @Inject
    public OpenUrlAction(Browser browser, HelpConfiguration helpConfiguration) {
        super();
        this.browser = browser;
        this.helpConfiguration = helpConfiguration;

    }

    private HttpURLConnection postViewerURL(String url) throws IOException {
        String viewerUrl = "http://localhost:60000/viewerurl";
        URL obj = new URL(viewerUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");

        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");

        String input = "{\"url\": \"" + url + "\"}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(input);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        LOGGER.debug("Sending 'POST' request to URL : " + url);
        LOGGER.debug("Post Data : " + input);
        LOGGER.debug("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        LOGGER.debug(response.toString());

        con.disconnect();
        return con;
    }


    private void isAlive() throws IOException {

        if(edcDesktopProcess == null || !edcDesktopProcess.isAlive()){
            edcDesktopProcess = Runtime.getRuntime().exec(helpConfiguration.getViewerDesktopPath());
        }
    }

    private void setInterval(String url){
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run(){
                try {
                    if(postViewerURL(url).getResponseCode() != 200){
                        postViewerURL(url);
                    } else {
                        timer.cancel();
                    }
                } catch (IOException e) {
                    LOGGER.error("Connection Refused Exception, server is not running, retry in a few seconds... " + e.getMessage());
                }
            }
        },0, 1000);
    }

    /**
     * Open the url in a browser.
     *
     * @param url the url to open
     * @throws IOException        if the connexion failed
     * @throws URISyntaxException the url is malformed
     */
    public void openUrl(String url) throws Exception {
        LOGGER.debug("Open the url: {}", url);

        if(helpConfiguration.getHelpViewer() == HelpViewer.EDC_DESKTOP_VIEWER){
            if(helpConfiguration.getViewerDesktopPath().isEmpty()){
                LOGGER.error("The path of the application must be entered");
            }else {
                isAlive();

                StreamGobbler streamGobbler = new StreamGobbler(edcDesktopProcess.getInputStream(), System.out::println);
                Executors.newSingleThreadExecutor().submit(streamGobbler);

                setInterval(url);
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
