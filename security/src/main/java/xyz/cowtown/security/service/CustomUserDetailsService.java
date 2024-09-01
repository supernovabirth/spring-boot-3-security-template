package xyz.cowtown.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RoleService roleService;

    public CustomUserDetailsService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch roles for the user
        List<String> roles = roleService.getRolesForUser(username);
        System.out.println("roles: " + roles.toString());
        // Convert roles to GrantedAuthority
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        // Log the username and authorities
        System.out.println("Username: " + username);
        System.out.println("Authorities: " + authorities);
        // Return UserDetails object
        return new User(username, "{noop}password", authorities); // Use {noop} for plain text password
    }
}