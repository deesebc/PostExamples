package es.home.example.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.ClientProtocolException;
import org.wso2.msf4j.Request;

public interface OAuthValidatorService {

    String validate(Request request, String token) throws AuthenticationException, KeyManagementException,
            NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException;

}
