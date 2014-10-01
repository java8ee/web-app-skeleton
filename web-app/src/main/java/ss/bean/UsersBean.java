package ss.bean;

import ss.domain.User;
import ss.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name = "users")
@ViewScoped
public class UsersBean implements Serializable {
    @ManagedProperty("#{userService}")
    private UserService userService;

    private Field sortField;
    private boolean ascending;

    public List<User> getList() {
        List<User> list = userService.list();

        if (this.sortField != null) {
            Collections.sort(list,  new Comparator<User>() {
                @Override
                public int compare(User first, User last) {
                    try {
                        Object valueFirst = sortField.get(first);
                        Object valueLast = sortField.get(last);
                        if (valueFirst instanceof Comparable) {
                            return ascending ?
                                    ((Comparable) valueFirst).compareTo((Comparable) valueLast) :
                                    ((Comparable) valueLast).compareTo((Comparable) valueFirst);
                        } else {
                            return 0;
                        }
                    } catch (IllegalAccessException cause) {
                        return 0;
                    }
                }
            });
        }

        return list;
    }

    /* SERVICES */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void remove(User user) {
        userService.delete(user);
    }

    public String sort(String field) throws NoSuchFieldException {
        Field sortField = getField(field);
        if (sortField.equals(this.sortField)) {
            this.ascending = !this.ascending;
        } else {
            this.sortField = sortField;
            this.ascending = true;
            this.sortField.setAccessible(true);
        }

        return null;
    }

    private Field getField(String field) throws NoSuchFieldException {
        try {
            return User.class.getDeclaredField(field);
        } catch (NoSuchFieldException cause) {
            return User.class.getField(field);
        }
    }
}