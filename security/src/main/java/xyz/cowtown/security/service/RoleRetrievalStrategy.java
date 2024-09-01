package xyz.cowtown.security.service;
import java.util.Map;
import java.util.List;

public interface RoleRetrievalStrategy {
    Map<Integer, List<String>> getRolesForAllUsers(String appName);
    List<String> getRolesForUser(String userId);
}
