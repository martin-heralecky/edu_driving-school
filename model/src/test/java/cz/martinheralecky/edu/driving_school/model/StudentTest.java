package cz.martinheralecky.edu.driving_school.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {
    Student student = new Student(
        new Entity.ID(1),
        "Pavel",
        "Fiala",
        "pavel.fiala@gmail.com",
        "+420 987 654 321",
        LocalDate.of(1995, 1, 18));

    @Test
    void gettersReturnCorrectly() {
        assertEquals(new Entity.ID(1), student.getID());
        assertEquals(1, student.getID().getValue());
        assertEquals("Pavel", student.getFirstName());
        assertEquals("Fiala", student.getSurname());
        assertEquals("pavel.fiala@gmail.com", student.getEmail());
        assertEquals("+420 987 654 321", student.getPhoneNumber());
        assertEquals("1995-01-18", student.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
