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

/**
 * TECH ADVANTAGE
 * All right reserved
 * <p>
 * Utility class to open an url in a browser.
 */
public class OpenUrlAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenUrlAction.class);

    private final Browser browser;
    private final HelpConfiguration helpConfiguration;

    @Inject
    public OpenUrlAction(Browser browser, HelpConfiguration helpConfiguration) {
        super();
        this.browser = browser;
        this.helpConfiguration = helpConfiguration;
    }

    public static void setTimeout(Runnable runnable, int delay, String url){
        new Thread(() -> {
            try {
                Thread.sleep(delay);

                runnable.run();

                String viewerUrl = "http://localhost:3000/viewerurl";
                URL obj = new URL(viewerUrl);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // Setting basic post request
                con.setRequestMethod("POST");

                con.setRequestProperty("Accept", "application/json");
                con.setRequestProperty("Content-Type","application/json");

                String input = "{\"url\": \""+url+"\"}";

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
                System.out.println(response.toString());
                con.disconnect();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    /**
     * Open the url in a browser.
     *
     * @param url the url to open
     * @throws IOException        if the connexion failed
     * @throws URISyntaxException the url is malformed
     */
    public void openUrl(String url) throws IOException, URISyntaxException {
        LOGGER.debug("Open the url: {}", url);

        if(helpConfiguration.getHelpViewer() == HelpViewer.EDC_DESKTOP_VIEWER){
            if(helpConfiguration.getViewerDesktopPath().isEmpty()){
                LOGGER.error("The path of the application must be entered");
            }else {
                setTimeout(() -> System.out.println("test"), 12000, url);

                    Runtime.getRuntime().exec(helpConfiguration.getViewerDesktopPath(), null, null);

                    LOGGER.info("Desktop viewer is running");
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
