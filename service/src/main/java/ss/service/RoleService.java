package ss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ss.dao.RoleDataSource;
import ss.domain.Role;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RoleService {
    @Autowired
    private RoleDataSource dao;

    public Role get(int id) {
        return dao.getById(id);
    }

    public List<Role> list() {
        return dao.getAll();
    }
}