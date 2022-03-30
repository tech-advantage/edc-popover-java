package fr.techad.edc.popover.utils;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;
import fr.techad.edc.popover.browser.Browser;
import fr.techad.edc.popover.model.HelpViewer;
import fr.techad.edc.popover.model.HelpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

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
    private Process edcDesktopProcess = null;
    private long edcProcessPID = -1;
    private int inpStreamReaderValue = -1;

    @Inject
    public OpenUrlAction(Browser browser, HelpConfiguration helpConfiguration) {
        super();
        this.browser = browser;
        this.helpConfiguration = helpConfiguration;

    }

    public static long getProcessID(Process p)
    {
        long result = -1;
        try
        {
            //for windows
            if (p.getClass().getName().equals("java.lang.Win32Process") ||
                    p.getClass().getName().equals("java.lang.ProcessImpl"))
            {
                Field f = p.getClass().getDeclaredField("handle");
                f.setAccessible(true);
                long handl = f.getLong(p);
                Kernel32 kernel = Kernel32.INSTANCE;
                WinNT.HANDLE hand = new WinNT.HANDLE();
                hand.setPointer(Pointer.createConstant(handl));
                result = kernel.GetProcessId(hand);
                f.setAccessible(false);
            }
            //for unix based operating systems
            else if (p.getClass().getName().equals("java.lang.UNIXProcess"))
            {
                Field f = p.getClass().getDeclaredField("pid");
                f.setAccessible(true);
                result = f.getLong(p);
                f.setAccessible(false);
            }
        }
        catch(Exception ex)
        {
            result = -1;
        }

        return result;
    }

    /**
     * Run the Electron viewer desktop process
     *
     * @throws InterruptedException
     * @throws IOException
     */
    private void runProcess() throws InterruptedException, IOException {
            edcDesktopProcess = new ProcessBuilder(helpConfiguration.getViewerDesktopPath()).start();
            InputStream is = edcDesktopProcess.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            InputStream inpStream = edcDesktopProcess.getErrorStream();
            BufferedReader br = new BufferedReader(isr);
            String line;
            edcDesktopProcess.waitFor(10, TimeUnit.SECONDS);
            System.out.println("Résultat process : " + edcProcessPID);
    }

    /**
     * Run command to check if the PID exist
     *
     * @throws IOException
     */
    private void runProcessTaskFindPID() throws IOException {
        //  Check if the PID is running
        String args = "TASKLIST /v /fi \"PID eq " + edcProcessPID + "\"";
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(args);
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        InputStream inpStream = process.getErrorStream();
        String line;
        inpStreamReaderValue = isr.read();
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

        runProcessTaskFindPID();

        if(helpConfiguration.getHelpViewer() == HelpViewer.EDC_DESKTOP_VIEWER){
            if(helpConfiguration.getViewerDesktopPath().isEmpty()){
                LOGGER.error("The path of the application must be entered");
            }else {

                if(inpStreamReaderValue == -1 || inpStreamReaderValue == 73){
                    runProcess();
                    edcProcessPID = getProcessID(edcDesktopProcess);
                }

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
                System.out.println(response.toString());
                con.disconnect();

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
