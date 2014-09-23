package ss.util;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import java.util.*;

public class LocaleHelper {
    private static List<Locale> locales;

    public static List<Locale> getLocales() {
        if (locales == null) {
            Application app = FacesContext.getCurrentInstance().getApplication();
            Set<Locale> set = new HashSet<Locale>();
            set.add(app.getDefaultLocale());

            Iterator<Locale> iterator = app.getSupportedLocales();
            while (iterator.hasNext()) {
                set.add(iterator.next());
            }

            locales = new ArrayList<Locale>(set);
            Collections.sort(locales, new Comparator<Locale>() {
                @Override
                public int compare(Locale first, Locale second) {
                    return first.getLanguage().compareTo(second.getLanguage());
                }
            });
        }

        return locales;
    }

    public static Locale findLocale(String name) {
        for (Locale locale : getLocales()) {
            if (locale.getLanguage().equals(name)) {
                return locale;
            }
        }

        return null;
    }
}