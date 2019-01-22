package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.model.Student;

import java.time.LocalDate;
import java.util.List;

/**
 * DAO interface for student entity.
 */
public interface StudentDAO {
    /**
     * Stores a new student entity.
     */
    void create(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate);

    /**
     * Retrieves all stored student entities.
     */
    List<Student> getAll();

    /**
     * Deletes a student entity identified by its ID from the storage.
     */
    void delete(Student.ID id);
}
