package xyz.cowtown.security.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

@Service
public class RoleService {
    private final RoleRetrievalStrategy roleRetrievalStrategy;

    public RoleService(RoleRetrievalStrategy roleRetrievalStrategy) {
        this.roleRetrievalStrategy = roleRetrievalStrategy;
    }

    public Map<Integer, List<String>> getRolesForAllUsers(String appName) {
        return roleRetrievalStrategy.getRolesForAllUsers(appName);
    }

    public List<String> getRolesForUser(String userId) {
        return roleRetrievalStrategy.getRolesForUser(userId);
    }
}
