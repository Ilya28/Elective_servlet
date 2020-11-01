package org.elective.service;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizedTextSupplier {
    private static LocalizedTextSupplier instance;

    public static synchronized LocalizedTextSupplier getInstance() {
        if (instance == null)
            instance = new LocalizedTextSupplier();
        return instance;
    }

    public String getLocalizedText(String messageKey, String locale) {
        ResourceBundle rb = ResourceBundle.getBundle("locale", new Locale(locale));
        return rb.getString(messageKey);
    }

    private LocalizedTextSupplier() {
    }
}
