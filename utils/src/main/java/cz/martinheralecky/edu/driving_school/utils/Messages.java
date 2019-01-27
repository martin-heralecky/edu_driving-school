package cz.martinheralecky.edu.driving_school.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Enumeration that provides localized strings.
 */
public enum Messages {
    app_name,
    menu_file,
    menu_file_quit,
    menu_data,
    menu_data_connect,
    menu_data_new_vehicle,
    menu_data_new_student,
    menu_data_new_teacher,
    menu_data_refresh,

    vehicle,
    vehicles,
    vehicle_id,
    vehicle_licensePlate,
    vehicle_make,
    vehicle_model,
    vehicle_year,
    vehicle_color,

    teacher,
    teachers,
    teacher_id,
    teacher_firstName,
    teacher_surname,
    teacher_email,
    teacher_phoneNumber,
    teacher_birthDate,

    student,
    students,
    student_id,
    student_firstName,
    student_surname,
    student_email,
    student_phoneNumber,
    student_birthDate,

    connectDialog_host,
    connectDialog_port;

    private static final Logger LOG = Logger.getLogger(Messages.class.getName());

    private static ResourceBundle resourceBundle;

    static {
        setLocale(Locale.getDefault());
    }

    /**
     * Sets locale.
     *
     * @param locale The new locale.
     */
    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("cz.martinheralecky.edu.driving_school.utils.messages", locale);
    }

    /**
     * Returns the localized and formatted string. If not found in resources, returns the name of the enum field.
     *
     * @param args Arguments of the string.
     */
    public String get(Object... args) {
        try {
            return getUnchecked(args);
        } catch (MissingResourceException ex) {
            LOG.warning(ex.toString());

            return name();
        }
    }

    /**
     * Returns the localized, formatted and capitalized string. If not found in resources, returns the name of the enum field, unchanged.
     *
     * @param args Arguments of the string.
     */
    public String getCapitalized(Object... args) {
        try {
            var msg = getUnchecked(args);

            if (msg.length() > 0) {
                msg = Character.toUpperCase(msg.charAt(0)) + msg.substring(1);
            }

            return msg;
        } catch (MissingResourceException ex) {
            LOG.warning(ex.toString());

            return name();
        }
    }

    /**
     * Returns the localized and formatted string.
     *
     * @param args Arguments of the string.
     * @throws MissingResourceException If the resource was not found.
     */
    private String getUnchecked(Object... args) throws MissingResourceException {
        return MessageFormat.format(resourceBundle.getString(name()), args);
    }
}
