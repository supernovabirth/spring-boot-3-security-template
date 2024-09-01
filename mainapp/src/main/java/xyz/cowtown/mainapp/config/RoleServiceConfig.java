package xyz.cowtown.mainapp.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import xyz.cowtown.security.service.JsonRoleRetrievalStrategy;
import xyz.cowtown.security.service.OAuthTokenService;
import xyz.cowtown.security.service.RoleService;

@Configuration
public class RoleServiceConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OAuthTokenService oAuthTokenService(RestTemplate restTemplate) {
        return new OAuthTokenService(restTemplate);
    }

    @Bean
    public JsonRoleRetrievalStrategy jsonRoleRetrievalStrategy(RestTemplate restTemplate, OAuthTokenService oAuthTokenService) {
        return new JsonRoleRetrievalStrategy(restTemplate, oAuthTokenService);
    }

    @Bean
    public RoleService roleService(JsonRoleRetrievalStrategy jsonRoleRetrievalStrategy) {
        return new RoleService(jsonRoleRetrievalStrategy);
    }
}

