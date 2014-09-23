package ss.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

@ManagedBean(name = "userSettings")
@SessionScoped
public class UserSettingsBean implements Serializable {
    private static final String LOCALES_PATH = "ss/locales.properties";
    private static Map<String, Locale> locales = new TreeMap<String, Locale>();

    private String locale = "en";

    static {
        loadLocales();
    }

    private static void loadLocales() {
        InputStream stream = UserSettingsBean.class.getClassLoader().getResourceAsStream(LOCALES_PATH);
        if (stream == null) {
            throw new RuntimeException("Can't find locales resource: " + LOCALES_PATH);
        }

        try {
            Properties properties = new Properties();
            properties.load(stream);
            for (String key : properties.stringPropertyNames()) {
                String lang = properties.getProperty(key);
                locales.put(lang, new Locale(key));
            }
        } catch (IOException cause) {
            throw new RuntimeException("Can't read locales list from file", cause);
        }
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