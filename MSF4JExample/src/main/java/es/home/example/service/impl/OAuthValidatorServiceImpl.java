package es.home.example.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.wso2.msf4j.Request;

import es.home.example.service.OAuthValidatorService;

@Path("/oauth2")
@Produces(MediaType.APPLICATION_JSON)
public class OAuthValidatorServiceImpl implements OAuthValidatorService {

    private static final String TOKEN = "token";
    private static final String ADMIN = "admin";
    private static final String OAUTH2_INTROSPECT = "https://localhost:9443/oauth2/introspect";

    @POST
    @Path("/validate")
    @Override
    public String validate(@Context final Request request, @FormParam(value = "token") final String token)
            throws AuthenticationException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
            ClientProtocolException, IOException {
        System.out.println("OAuthValidatorServiceImpl - validate");
        System.out.println("Token: " + token);
        return callIntrospectWithToken(token);
    }

    private String callIntrospectWithToken(final String token) throws NoSuchAlgorithmException, KeyStoreException,
            AuthenticationException, KeyManagementException, ClientProtocolException, IOException {
        System.out.println("OAuthValidatorServiceImpl - callIntrospectWithToken");
        System.out.println("OAuthValidatorServiceImpl - token: " + token);

        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());

        HttpClient client = HttpClientBuilder.create().setSSLSocketFactory(sslsf).build();
        HttpPost httppost = new HttpPost(OAUTH2_INTROSPECT);

        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(ADMIN, ADMIN);
        httppost.addHeader(new BasicScheme().authenticate(creds, httppost, null));

        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair(TOKEN, token));
        httppost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8.name()));
        HttpResponse response = client.execute(httppost);
        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8.name());
    }
}
