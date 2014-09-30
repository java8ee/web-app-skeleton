package ss.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    @ManyToOne
    private Person person;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return id + ". " + username + "@" + password + " -> " + person;
    }
}