package cz.martinheralecky.edu.driving_school.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherTest {
    Teacher teacher = new Teacher(
        new Entity.ID(1),
        "Josef",
        "Novák",
        "josef.novak@gmail.com",
        "+420 123 456 789",
        LocalDate.of(1980, 3, 20));

    @Test
    void gettersReturnCorrectly() {
        assertEquals(new Entity.ID(1), teacher.getID());
        assertEquals(1, teacher.getID().getValue());
        assertEquals("Josef", teacher.getFirstName());
        assertEquals("Novák", teacher.getSurname());
        assertEquals("josef.novak@gmail.com", teacher.getEmail());
        assertEquals("+420 123 456 789", teacher.getPhoneNumber());
        assertEquals("1980-03-20", teacher.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
