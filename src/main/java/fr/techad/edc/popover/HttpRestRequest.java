package fr.techad.edc.popover;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface HttpRestRequest {
    void postData(String host, String data) throws IOException;
}
