package fr.techad.edc.popover.internal;

import fr.techad.edc.popover.HttpRestRequest;
import fr.techad.edc.popover.utils.OpenUrlAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRestRequestImpl implements HttpRestRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenUrlAction.class);
    private HttpURLConnection createHttpConnection(String apiPath) throws IOException {
        URL url = new URL(apiPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        return connection;
    }

    public void postData(String apiPath, String data) throws IOException {
        HttpURLConnection httpConnection = createHttpConnection(apiPath);
        String input = data;

        // Send post request
        httpConnection.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream())) {
            wr.writeBytes(input);
            wr.flush();
        }

        int responseCode = httpConnection.getResponseCode();
        LOGGER.debug("Sending 'POST' request to URL: {}", apiPath);
        LOGGER.debug("Post Data: {}", input);
        LOGGER.debug("Response Code: {}", responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()))) {
            String output;
            StringBuilder response = new StringBuilder();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            LOGGER.debug(response.toString());
        }
    }
}
