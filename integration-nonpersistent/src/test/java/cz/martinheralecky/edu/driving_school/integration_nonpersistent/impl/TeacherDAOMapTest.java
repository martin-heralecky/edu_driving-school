package cz.martinheralecky.edu.driving_school.integration.impl;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TeacherDAOMapTest {
    @Test
    void createGetAllDeleteWorkCorrectly() {
        var dao = new TeacherDAOMap();

        dao.create("Josef", "Novák", "josef.novak@gmail.com", "+420 123 456 789", LocalDate.of(1985, 4, 25));
        assertEquals(1, dao.getAll().size());
        assertEquals("Josef", dao.getAll().get(0).getFirstName());
        assertEquals("Novák", dao.getAll().get(0).getSurname());
        assertEquals("josef.novak@gmail.com", dao.getAll().get(0).getEmail());
        assertEquals("+420 123 456 789", dao.getAll().get(0).getPhoneNumber());
        assertEquals("1985-04-25", dao.getAll().get(0).getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));

        dao.create("Petr", "", "", "", LocalDate.now());
        assertEquals(2, dao.getAll().size());

        var teachers = dao.getAll();
        assertNotEquals(teachers.get(0).getFirstName(), teachers.get(1).getFirstName());

        dao.delete(teachers.get(0).getID());
        assertEquals(1, dao.getAll().size());

        dao.delete(teachers.get(1).getID());
        assertEquals(0, dao.getAll().size());
    }
}
