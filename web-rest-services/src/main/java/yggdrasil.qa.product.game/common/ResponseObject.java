package yggdrasil.qa.product.game.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.log4testng.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static yggdrasil.qa.product.game.utils.StatusCode.STATUS_CODE_200;

public class ResponseObject {

    private static final Logger logger = Logger.getLogger(ResponseObject.class);

    private byte[] response;

    private HttpResponse httpResponse;

    public String getResponse() {
        return new String(response);
    }

    public JsonObject getResponseJson() {
        return new JsonParser().parse(getResponse()).getAsJsonObject();
    }

    public void setResponseContent(byte[] response) {
        this.response = response;
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    private ResponseObject executeQuery(HttpUriRequest request, ResponseObject responseObject) {
        try {
            final HttpResponse httpResponse = HttpClientBuilder.create()
                    .build()
                    .execute(request);

            responseObject.setHttpResponse(httpResponse);
        } catch (IOException e) {
            logger.error(String.format("Request with URI: %s cannot be executed", request.getURI()));
        }
        return responseObject;
    }

    public ResponseObject executeGetRequest(ResponseObject responseObject, List<NameValuePair> nvPairList, String url) {

        final HttpGet httpGet = new HttpGet(url);
        try {
            final URI uri = new URIBuilder(httpGet.getURI()).addParameters(nvPairList)
                    .build();
            httpGet.setURI(uri);
            responseObject = executeQuery(httpGet, responseObject);
            responseObject.setResponseContent(getContentFromHttpResponse(responseObject));

        } catch (URISyntaxException e) {
            logger.error(String.format("Cannot create URI for url: %s", url));
        }
        return responseObject;
    }

    private byte[] getContentFromHttpResponse(ResponseObject responseObject) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        InputStream inputStream = null;

        try {

            if (responseObject.getHttpResponse() != null) {
                inputStream = responseObject.getHttpResponse()
                        .getEntity()
                        .getContent();
            }

            if (inputStream != null) {
                byte[] buff = new byte[4096];
                int read = -1;
                while ((read = inputStream.read(buff, 0, buff.length)) != -1) {
                    result.write(buff, 0, read);
                }
            }
        } catch (IOException e) {
            logger.error("Problem with creating InputStreamReader");
        }

        return result.toByteArray();
    }

    public void isRequestCorrectlyExecuted() throws IllegalStateException {
        final int responseCode = getHttpResponse().getStatusLine().getStatusCode();
        if (responseCode != STATUS_CODE_200.getCode()) {
            throw new IllegalStateException(String.format("Invalid response status code: %s", responseCode));
        }
    }

}

