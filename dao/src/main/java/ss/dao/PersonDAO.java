package ss.dao;

import org.springframework.stereotype.Repository;
import ss.domain.Person;

@Repository
public class PersonDAO extends AbstractDAO<Person> implements PersonDataSource {
    public PersonDAO() {
        super(Person.class);
    }
}