package ss.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@ManagedBean(name = "userSettings")
@SessionScoped
public class UserSettingsBean implements Serializable {
    private String locale = "en";

    private static Map<String, Locale> locales;

    static {
        locales = new LinkedHashMap<String, Locale>();
        locales.put("English", Locale.ENGLISH);
        locales.put("Russian", new Locale("ru"));
        locales.put("German", Locale.GERMAN);
        locales.put("French", Locale.FRENCH);
    }

    public Map<String, Locale> getLocales() {
        return locales;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void changeLocale(ValueChangeEvent event) {
        String newLocaleName = event.getNewValue().toString();
        Locale newLocale = findLocale(newLocaleName);
        if (newLocale != null) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
        }
    }
    
    private Locale findLocale(String name) {
        for (Map.Entry<String, Locale> entry : locales.entrySet()) {
            if (entry.getValue().toString().equals(name)) {
                return entry.getValue();
            }
        }

        return null;
    }
}