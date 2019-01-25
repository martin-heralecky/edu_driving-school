package cz.martinheralecky.edu.driving_school.business.impl;

import cz.martinheralecky.edu.driving_school.business.Facade;
import cz.martinheralecky.edu.driving_school.business.Observer;
import cz.martinheralecky.edu.driving_school.integration.StudentDAO;
import cz.martinheralecky.edu.driving_school.integration.TeacherDAO;
import cz.martinheralecky.edu.driving_school.integration.VehicleDAO;
import cz.martinheralecky.edu.driving_school.model.Student;
import cz.martinheralecky.edu.driving_school.model.Teacher;
import cz.martinheralecky.edu.driving_school.model.Vehicle;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * Set of {@link Observer} objects that should be notified about state changes of entities.
     */
    private Set<Observer> observers = new HashSet<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void addVehicle(String licensePlate, String make, String model, int year, String color)
        throws Exception {
        vehicleDAO.create(licensePlate, make, model, year, color);
        observers.forEach(Observer::update);
    }

    @Override
    public List<Vehicle> getVehicles()
        throws Exception {
        return vehicleDAO.getAll();
    }

    @Override
    public void deleteVehicle(Vehicle.ID id)
        throws Exception {
        vehicleDAO.delete(id);
        observers.forEach(Observer::update);
    }

    @Override
    public void addTeacher(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate)
        throws Exception {
        teacherDAO.create(firstName, surname, email, phoneNumber, birthDate);
        observers.forEach(Observer::update);
    }

    @Override
    public List<Teacher> getTeachers()
        throws Exception {
        return teacherDAO.getAll();
    }

    @Override
    public void deleteTeacher(Teacher.ID id)
        throws Exception {
        teacherDAO.delete(id);
        observers.forEach(Observer::update);
    }

    @Override
    public void addStudent(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate)
        throws Exception {
        studentDAO.create(firstName, surname, email, phoneNumber, birthDate);
        observers.forEach(Observer::update);
    }

    @Override
    public List<Student> getStudents()
        throws Exception {
        return studentDAO.getAll();
    }

    @Override
    public void deleteStudent(Student.ID id)
        throws Exception {
        studentDAO.delete(id);
        observers.forEach(Observer::update);
    }
}
