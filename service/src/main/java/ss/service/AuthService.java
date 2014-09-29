package ss.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ss.dao.UserDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ss.domain.Role;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthService implements UserDetailsService {
    @Autowired
    private UserDataSource dao;

    public boolean hasPermission(String username, String password) {
        ss.domain.User user = dao.getByUsername(username);
        return user != null && equals(user.getPassword(), password);
    }

    private boolean equals(String first, String second) {
        if (first == null && second == null) {
            return true;
        } else {
            return first != null && first.equals(second);
        }
    }

    public String getPerson(String username) {
        ss.domain.User user = dao.getByUsername(username);
        return user == null ? null : user.getPerson().toString();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ss.domain.User entity = dao.getByUsername(username);
        if (entity == null) {
            throw new UsernameNotFoundException("Unnoun user : [" + username + "]");
        } else if (entity.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("User without any roles : [" + username + "]");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : entity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(entity.getUsername(), entity.getPassword(), authorities);
    }
}