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
    void addVehicle(String licensePlate, String make, String model, int year, String color)
        throws Exception;

    /**
     * Retrieves all vehicles.
     */
    List<Vehicle> getVehicles()
        throws Exception;

    /**
     * Deletes a vehicle identified by its ID.
     */
    void deleteVehicle(Vehicle.ID id)
        throws Exception;

    /**
     * Adds a new teacher.
     */
    void addTeacher(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate)
        throws Exception;

    /**
     * Retrieves all teachers.
     */
    List<Teacher> getTeachers()
        throws Exception;

    /**
     * Deletes a teacher identified by ID.
     */
    void deleteTeacher(Teacher.ID id)
        throws Exception;

    /**
     * Adds a new student.
     */
    void addStudent(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate)
        throws Exception;

    /**
     * Retrieves all students.
     */
    List<Student> getStudents()
        throws Exception;

    /**
     * Deletes a student identified by ID.
     */
    void deleteStudent(Student.ID id)
        throws Exception;
}
