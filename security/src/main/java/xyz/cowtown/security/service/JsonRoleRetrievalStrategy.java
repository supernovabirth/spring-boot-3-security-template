package xyz.cowtown.security.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JsonRoleRetrievalStrategy implements RoleRetrievalStrategy {

    private final RestTemplate restTemplate;
    private final OAuthTokenService oAuthTokenService;
    
    @Value("${roles.service.string.app}")
    private String allUsersString;

    public JsonRoleRetrievalStrategy(RestTemplate restTemplate, OAuthTokenService oAuthTokenService) {
        this.restTemplate = restTemplate;
        this.oAuthTokenService = oAuthTokenService;
    }

    @Override
    public Map<Integer, List<String>> getRolesForAllUsers(String appName) {
        String url = String.format("http://127.0.0.1:8000/users/apps/%s/users", appName);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(oAuthTokenService.getOAuthToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        // Assuming the response is in the format {"users": {"1": ["VIEW"], "2": ["VIEW", "CONTRIBUTOR"]}}
        return (Map<Integer, List<String>>) response.getBody().get("users");
    }

    @Override
    public List<String> getRolesForUser(String userId) {
        String url = String.format("http://127.0.0.1:8000/users/apps/springbootapp/users/%s/roles", userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(oAuthTokenService.getOAuthToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        // Assuming the response is in the format {"roles": ["VIEW", "CONTRIBUTOR"]}
        return (List<String>) response.getBody().get("roles");
    }
}
