package cz.martinheralecky.edu.driving_school.business_proxy.impl;

import cz.martinheralecky.edu.driving_school.business.Facade;
import cz.martinheralecky.edu.driving_school.business.Observer;
import cz.martinheralecky.edu.driving_school.business_proxy.FacadeProxy;
import cz.martinheralecky.edu.driving_school.dispatcher.Dispatcher;
import cz.martinheralecky.edu.driving_school.model.Student;
import cz.martinheralecky.edu.driving_school.model.Teacher;
import cz.martinheralecky.edu.driving_school.model.Vehicle;
import cz.martinheralecky.edu.driving_school.protocol.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link Facade} which uses {@link Dispatcher} to communicate with server by sending
 * {@link Command}s.
 */
@Component
public class FacadeProxyDefault implements FacadeProxy {
    @Reference
    private Dispatcher dispatcher;

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
        dispatcher.send(new AddVehicleCommand(new Vehicle(null, licensePlate, make, model, year, color)));
        observers.forEach(Observer::update);
    }

    @Override
    public List<Vehicle> getVehicles()
        throws Exception {
        return dispatcher.send(new GetVehiclesCommand());
    }

    @Override
    public void deleteVehicle(Vehicle.ID id)
        throws Exception {
        dispatcher.send(new DeleteVehicleCommand(id));
        observers.forEach(Observer::update);
    }

    @Override
    public void addTeacher(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate)
        throws Exception {
        dispatcher.send(new AddTeacherCommand(new Teacher(null, firstName, surname, email, phoneNumber, birthDate)));
        observers.forEach(Observer::update);
    }

    @Override
    public List<Teacher> getTeachers()
        throws Exception {
        return dispatcher.send(new GetTeachersCommand());
    }

    @Override
    public void deleteTeacher(Teacher.ID id)
        throws Exception {
        dispatcher.send(new DeleteTeacherCommand(id));
        observers.forEach(Observer::update);
    }

    @Override
    public void addStudent(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate)
        throws Exception {
        dispatcher.send(new AddStudentCommand(new Student(null, firstName, surname, email, phoneNumber, birthDate)));
        observers.forEach(Observer::update);
    }

    @Override
    public List<Student> getStudents()
        throws Exception {
        return dispatcher.send(new GetStudentsCommand());
    }

    @Override
    public void deleteStudent(Student.ID id)
        throws Exception {
        dispatcher.send(new DeleteStudentCommand(id));
        observers.forEach(Observer::update);
    }
}
