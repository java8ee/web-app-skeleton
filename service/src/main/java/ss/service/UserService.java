package ss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ss.dao.UserDataSource;
import ss.domain.User;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {
    @Autowired
    private UserDataSource dao;

    public List<User> list() {
        return dao.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(User user) {
        dao.delete(user);
    }
}