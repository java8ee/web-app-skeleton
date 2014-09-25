package ss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ss.dao.PersonDataSource;
import ss.domain.Person;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonService {
    @Autowired
    private PersonDataSource dao;

    public Person get(int id) {
        return dao.getById(id);
    }

    public List<Person> list() {
        return dao.getAll();
    }
}