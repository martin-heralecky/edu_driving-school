package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.model.Teacher;

import java.time.LocalDate;
import java.util.List;

/**
 * DAO interface for teacher entity.
 */
public interface TeacherDAO {
    void create(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate)
        throws Exception;

    /**
     * Retrieves all stored teacher entities.
     */
    List<Teacher> getAll()
        throws Exception;

    /**
     * Deletes a teacher entity identified by its ID from the storage.
     */
    void delete(Teacher.ID id)
        throws Exception;
}
