package ss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ss.dao.UserDataSource;
import ss.domain.User;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RegisterService {
    @Autowired
    private UserDataSource dao;

    public void register(User user) {
        dao.create(user);
    }

    public boolean existUser(String username) {
        return dao.getByUsername(username) != null;
    }
}