package es.home.sample.wordCounter.util;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.home.sample.wordCounter.pojo.ReadmeResponse;

@Component
public final class RestTemplateCaller {
    private static final Log LOGGER = LogFactory.getLog(RestTemplateCaller.class);

    private static String README_GIBBERISH_URL;

    private final static RestTemplate restTemplate;
    private final static HttpEntity<String> entity;

    static {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0");
        entity = new HttpEntity<>("parameters", headers);
    }

    private RestTemplateCaller() {
        super();
    }

    public static ResponseEntity<ReadmeResponse> readmeGibberish(final Integer nParagraphs, final Integer minWords,
            final Integer maxWords) {
        String url = String.format(README_GIBBERISH_URL, nParagraphs, minWords, maxWords);
        LOGGER.info("Readme Gibberish call: " + url);
        return restTemplate.exchange(url, HttpMethod.GET, entity, ReadmeResponse.class);
    }

    @Value("${spring.readme.api.url}")
    public void setReadmeGibberishUrl(final String rgUrl) {
        README_GIBBERISH_URL = rgUrl;
    }
}
