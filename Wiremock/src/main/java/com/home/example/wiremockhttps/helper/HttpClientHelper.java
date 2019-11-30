package com.home.example.wiremockhttps.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.home.example.wiremockhttps.pojo.Token;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HttpClientHelper {
    private static final String UTF_8 = "UTF-8";
    private static final String HTTP_POST = "POST";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    private static final Gson gson = new GsonBuilder().create();

    private static CloseableHttpClient createAcceptSelfSignedCertificateClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(new TrustSelfSignedStrategy()).build();
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        return HttpClients.custom().setSSLSocketFactory(connectionFactory).build();
    }

    private static HttpPost getPost(final String sEndpoint, final Map<String, String> headers) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(sEndpoint);
        Header[] soapHeaders = headers.entrySet().stream().map((entry) -> new BasicHeader(entry.getKey(), entry.getValue())).toArray(Header[]::new);
        post.setHeaders(soapHeaders);
        return post;
    }

    public static <T> T doPost(final String sEndpoint, final Map<String, String> headers, final Class<T> clase) throws ClientProtocolException, IOException {
        T exit = null;
        HttpPost post = getPost(sEndpoint, headers);
        try (CloseableHttpClient httpClient = createAcceptSelfSignedCertificateClient()) {
            HttpResponse hResponse = httpClient.execute(post);
            String json = EntityUtils.toString(hResponse.getEntity(), "UTF-8");
            exit = new Gson().fromJson(json, clase);
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException except) {
            log.error(except.getMessage(), except);
        }
        return exit;
    }

    // public static <T> T doPostAndGetResponse(final String sEndpoint, final Map<String, String> headers, final String payload, final Class<T> clase)
    // throws ParseException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
    // log.debug("sEndpoint: " + sEndpoint);
    // HttpPost post = getPost(sEndpoint, payload, headers);
    // T retorno = null;
    // HttpResponse response = null;
    // try (CloseableHttpClient httpClient = createAcceptSelfSignedCertificateClient()) {
    // response = httpClient.execute(post);
    // int httpStatus = response.getStatusLine().getStatusCode();
    // log.debug("getStatusCode: " + httpStatus);
    // if (httpStatus < 200 || httpStatus > 300) {
    // log.debug("Response : " + EntityUtils.toString(response.getEntity(), "UTF-8"));
    // throw new AMPluginException("Error receiving file, httpStatus: " + httpStatus, new UnsupportedOperationException());
    // } else {
    // String json = EntityUtils.toString(response.getEntity(), "UTF-8");
    // retorno = new Gson().fromJson(json, clase);
    // }
    // }
    // return retorno;
    // }
    //
    // private static CloseableHttpClient createAcceptSelfSignedCertificateClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
    // TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
    // SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(acceptingTrustStrategy).build();
    // SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, org.apache.http.conn.ssl.NoopHostnameVerifier.INSTANCE);
    // Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("https", sslsf)
    // .register("http", new PlainConnectionSocketFactory()).build();
    // BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
    // return HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(connectionManager).build();
    // }
    //
    // private static HttpPost getPost(final String sEndpoint, final String fPayload, final Map<String, String> headers) throws UnsupportedEncodingException {
    // HttpPost post = new HttpPost(sEndpoint);
    // Header[] soapHeaders = headers.entrySet().stream().map((entry) -> new BasicHeader(entry.getKey(), entry.getValue())).toArray(Header[]::new);
    // post.setHeaders(soapHeaders);
    // if (StringUtils.isNotBlank(fPayload)) {
    // post.setEntity(new StringEntity(fPayload));
    // }
    // return post;
    // }

    /**
     * Generate OAuth token using password grant type.
     *
     * @param url
     *            token endpoint URL
     * @param apiKey
     *            api consumer key
     * @param apiSecret
     *            api consumer secret
     * @param username
     *            username
     * @param password
     *            password
     * @param grantType
     *            password or client_credentials
     * @return
     * @throws IOException
     */
    public static Token generateToken(String url, final String apiKey, final String apiSecret, final String username, final String password,
            final String grantType) throws IOException {

        if (log.isDebugEnabled()) {
            log.debug("Initializing token generation request: [token-endpoint] " + url);
        }

        HttpURLConnection connection = null;
        // Set query parameters
        url += "?grant_type=client_credentials";
        URL url_ = new URL(url);
        connection = (HttpURLConnection) url_.openConnection();
        connection.setDoOutput(true);

        // Set HTTP method
        connection.setRequestMethod(HTTP_POST);

        // Set authorization header
        String credentials = Base64.getEncoder().encodeToString((apiKey + ":" + apiSecret).getBytes());
        connection.setRequestProperty(AUTHORIZATION_HEADER, "Basic " + credentials);
        connection.setRequestProperty(CONTENT_TYPE_HEADER, APPLICATION_X_WWW_FORM_URLENCODED);

        // Make HTTP request
        log.debug("Requesting access token...");

        int responseCode = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        if (log.isDebugEnabled()) {
            log.debug("Response: [status-code] " + responseCode + " [message] " + response.toString());
        }
        return gson.fromJson(response.toString(), Token.class);
    }
}
