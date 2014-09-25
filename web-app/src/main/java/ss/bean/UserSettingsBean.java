package ss.bean;

import ss.util.LocaleHelper;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "userSettings")
@SessionScoped
public class UserSettingsBean implements Serializable {
    public static final String RESOURCE_BUNDLE_VAR = "msg";
    private Map<String, String> locales = new LinkedHashMap<String, String>();

    private String locale;

    public Map<String, String> getLocales() {
        if (locales.isEmpty()) {
            loadLocales();
        }
        return locales;
    }

    public String getLocale() {
        if (locale == null) {
            locale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale().getLanguage();
        }
        return locale;
    }

    public void setLocale(String locale) {
        if (!locale.equals(this.locale)) {
            this.locale = locale;
            loadLocales();
        }
    }

    public void changeLocale(ValueChangeEvent event) {
        String newLocale = event.getNewValue().toString();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(newLocale));
    }

    private void loadLocales() {
        ResourceBundle bundle = getBundle();

        locales.clear();
        for (Locale loc : LocaleHelper.getLocales()) {
            String lang = loc.getLanguage();
            locales.put(getMessage(bundle, "locale." + lang), lang);
        }
    }

    private String getMessage(ResourceBundle bundle, String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException cause) {
            return "???" + key + "???";
        }
    }

    private ResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, RESOURCE_BUNDLE_VAR);
    }
}