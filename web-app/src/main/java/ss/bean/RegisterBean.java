package ss.bean;

import ss.domain.Person;
import ss.domain.User;
import ss.service.PersonService;
import ss.service.RegisterService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@ManagedBean(name = "register")
@RequestScoped
public class RegisterBean implements Serializable {
    private String username;
    private String password;
    private String confirm;
    private int personId;

    @ManagedProperty("#{registerService}")
    private RegisterService registerService;

    @ManagedProperty("#{personService}")
    private PersonService personService;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    public String getConfirm() {
        return confirm;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public int getPersonId() {
        return personId;
    }

    public Map<String, Integer> getPersons() {
        Map<String, Integer> persons = new LinkedHashMap<>();
        persons.put("UNNOUN", 0);

        for (Person person : personService.list()) {
            persons.put(person.getName() + " " + person.getName(), person.getId());
        }

        return persons;
    }

    /* SERVICES */
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    /* ACTIONS */
    public String register() {
        if (userUnique() & checkPasswords()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPerson(personService.get(personId));
            registerService.register(user);

            FacesMessage message = new FacesMessage("User " + username + " was successfully created!");
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        return null;
    }

    private boolean userUnique() {
        if (registerService.existUser(username)) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username is not unique", null);
            context.addMessage("register:username", message);

            return false;
        } else {
            return true;
        }
    }

    private boolean checkPasswords() {
        if (password.equals(confirm)) {
            return true;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password and Confirm are not same", null);
            context.addMessage("register:confirm", message);

            return false;
        }
    }
}