package cz.martinheralecky.edu.driving_school.business;

import cz.martinheralecky.edu.driving_school.model.Student;
import cz.martinheralecky.edu.driving_school.model.Teacher;
import cz.martinheralecky.edu.driving_school.model.Vehicle;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface defining basic operations with vehicles, teachers and students. Operations that change state notify the 
 * registered observers.
 */
public interface Facade extends Observable {
    /**
     * Adds a new vehicle.
     */
    void addVehicle(String licensePlate, String make, String model, int year, String color);

    /**
     * Retrieves all vehicles.
     */
    List<Vehicle> getVehicles();

    /**
     * Deletes a vehicle identified by its ID.
     */
    void deleteVehicle(Vehicle.ID id);

    /**
     * Adds a new teacher.
     */
    void addTeacher(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate);

    /**
     * Retrieves all teachers.
     */
    List<Teacher> getTeachers();

    /**
     * Deletes a teacher identified by ID.
     */
    void deleteTeacher(Teacher.ID id);

    /**
     * Adds a new student.
     */
    void addStudent(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate);

    /**
     * Retrieves all students.
     */
    List<Student> getStudents();

    /**
     * Deletes a student identified by ID.
     */
    void deleteStudent(Student.ID id);
}
