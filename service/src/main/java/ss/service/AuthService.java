package ss.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import ss.dao.UserDataSource;
import ss.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthService {
    @Autowired
    private UserDataSource dao;

    public boolean hasPermission(String username, String password) {
        User user = dao.getByUsername(username);
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
        User user = dao.getByUsername(username);
        return user == null ? null : user.getPerson().toString();
    }
}