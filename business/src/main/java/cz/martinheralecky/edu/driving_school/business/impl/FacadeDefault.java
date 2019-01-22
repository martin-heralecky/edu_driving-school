package cz.martinheralecky.edu.driving_school.business.impl;

import cz.martinheralecky.edu.driving_school.business.Facade;
import cz.martinheralecky.edu.driving_school.integration.StudentDAO;
import cz.martinheralecky.edu.driving_school.integration.TeacherDAO;
import cz.martinheralecky.edu.driving_school.integration.VehicleDAO;
import cz.martinheralecky.edu.driving_school.model.Student;
import cz.martinheralecky.edu.driving_school.model.Teacher;
import cz.martinheralecky.edu.driving_school.model.Vehicle;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDate;
import java.util.List;

/**
 * Default implementation of the business {@link Facade}.
 */
@Component
public class FacadeDefault implements Facade {
    @Reference
    private VehicleDAO vehicleDAO;

    @Reference
    private TeacherDAO teacherDAO;

    @Reference
    private StudentDAO studentDAO;

    @Override
    public void addVehicle(String licensePlate, String make, String model, int year, String color) {
        vehicleDAO.create(licensePlate, make, model, year, color);
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleDAO.getAll();
    }

    @Override
    public void deleteVehicle(Vehicle.ID id) {
        vehicleDAO.delete(id);
    }

    @Override
    public void addTeacher(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate) {
        teacherDAO.create(firstName, surname, email, phoneNumber, birthDate);
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherDAO.getAll();
    }

    @Override
    public void deleteTeacher(Teacher.ID id) {
        teacherDAO.delete(id);
    }

    @Override
    public void addStudent(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate) {
        studentDAO.create(firstName, surname, email, phoneNumber, birthDate);
    }

    @Override
    public List<Student> getStudents() {
        return studentDAO.getAll();
    }

    @Override
    public void deleteStudent(Student.ID id) {
        studentDAO.delete(id);
    }
}
