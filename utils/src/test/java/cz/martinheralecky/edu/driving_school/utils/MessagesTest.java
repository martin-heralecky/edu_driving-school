package cz.martinheralecky.edu.driving_school.utils;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessagesTest {
    @Test
    void getReturnsCorrectEnglish() {
        Messages.setLocale(new Locale("cs_CZ"));
        assertEquals("vehicle", Messages.vehicle.get());
    }

    @Test
    void getCapitalizedReturnsCorrectEnglish() {
        Messages.setLocale(new Locale("cs_CZ"));
        assertEquals("Vehicle", Messages.vehicle.getCapitalized());
    }

    @Test
    void getReturnsCorrectCzech() {
        Messages.setLocale(new Locale("en"));
        assertEquals("vehicle", Messages.vehicle.get());
    }

    @Test
    void getCapitalizedReturnsCorrectCzech() {
        Messages.setLocale(new Locale("en"));
        assertEquals("Vehicle", Messages.vehicle.getCapitalized());
    }
}
