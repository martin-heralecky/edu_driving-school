package cz.martinheralecky.edu.driving_school.business.impl;

import cz.martinheralecky.edu.driving_school.business.Facade;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FacadeDefaultTest {
    Facade facade = new FacadeDefault();

    @Test
    void vehicle_addGetDeleteWorkCorrectly() {
        facade.addVehicle("1A2 345", "Opel", "Astra", 2000, "black");
        assertEquals(1, facade.getVehicles().size());
        assertEquals("1A2 345", facade.getVehicles().get(0).getLicensePlate());
        assertEquals("Opel", facade.getVehicles().get(0).getMake());
        assertEquals("Astra", facade.getVehicles().get(0).getModel());
        assertEquals(2000, facade.getVehicles().get(0).getYear());
        assertEquals("black", facade.getVehicles().get(0).getColor());

        facade.addVehicle("9Z8 7655", "", "", 0, "");
        assertEquals(2, facade.getVehicles().size());

        var vehicles = facade.getVehicles();
        assertNotEquals(vehicles.get(0).getLicensePlate(), vehicles.get(1).getLicensePlate());

        facade.deleteVehicle(vehicles.get(0).getID());
        assertEquals(1, facade.getVehicles().size());

        facade.deleteVehicle(vehicles.get(1).getID());
        assertEquals(0, facade.getVehicles().size());
    }

    @Test
    void teacher_addGetDeleteWorkCorrectly() {
        facade.addTeacher("Josef", "Novák", "josef.novak@gmail.com", "+420 123 456 789", LocalDate.of(1985, 4, 25));
        assertEquals(1, facade.getTeachers().size());
        assertEquals("Josef", facade.getTeachers().get(0).getFirstName());
        assertEquals("Novák", facade.getTeachers().get(0).getSurname());
        assertEquals("josef.novak@gmail.com", facade.getTeachers().get(0).getEmail());
        assertEquals("+420 123 456 789", facade.getTeachers().get(0).getPhoneNumber());
        assertEquals("1985-04-25", facade.getTeachers().get(0).getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));

        facade.addTeacher("Petr", "", "", "", LocalDate.now());
        assertEquals(2, facade.getTeachers().size());

        var teachers = facade.getTeachers();
        assertNotEquals(teachers.get(0).getFirstName(), teachers.get(1).getFirstName());

        facade.deleteTeacher(teachers.get(0).getID());
        assertEquals(1, facade.getTeachers().size());

        facade.deleteTeacher(teachers.get(1).getID());
        assertEquals(0, facade.getTeachers().size());
    }

    @Test
    void student_addGetDeleteWorkCorrectly() {
        facade.addStudent("Josef", "Novák", "josef.novak@gmail.com", "+420 123 456 789", LocalDate.of(1985, 4, 25));
        assertEquals(1, facade.getStudents().size());
        assertEquals("Josef", facade.getStudents().get(0).getFirstName());
        assertEquals("Novák", facade.getStudents().get(0).getSurname());
        assertEquals("josef.novak@gmail.com", facade.getStudents().get(0).getEmail());
        assertEquals("+420 123 456 789", facade.getStudents().get(0).getPhoneNumber());
        assertEquals("1985-04-25", facade.getStudents().get(0).getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));

        facade.addStudent("Petr", "", "", "", LocalDate.now());
        assertEquals(2, facade.getStudents().size());

        var students = facade.getStudents();
        assertNotEquals(students.get(0).getFirstName(), students.get(1).getFirstName());

        facade.deleteStudent(students.get(0).getID());
        assertEquals(1, facade.getStudents().size());

        facade.deleteStudent(students.get(1).getID());
        assertEquals(0, facade.getStudents().size());
    }
}
