package es.home.example.sbowi.extractor;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

public class WSO2IsPrincipalExtractor implements PrincipalExtractor {

    @Override
    public Object extractPrincipal(final Map<String, Object> map) {
        return map.get("sub");
    }
}