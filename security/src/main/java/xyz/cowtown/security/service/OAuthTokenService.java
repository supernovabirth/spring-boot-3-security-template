package xyz.cowtown.security.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Service
public class OAuthTokenService {

    private final RestTemplate restTemplate;

    @Value("${oauth.token.url}")
    private String tokenUrl;

    @Value("${oauth.username}")
    private String username;

    @Value("${oauth.password}")
    private String password;

    @Value("${oauth.grant.type}")
    private String grantType;

    public OAuthTokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getOAuthToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("username", username);
        params.add("password", password);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, Map.class);
        return (String) response.getBody().get("access_token");
    }
}

