package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.integration.impl.TeacherDAOMap;
import cz.martinheralecky.edu.driving_school.model.Teacher;

import java.time.LocalDate;
import java.util.List;

/**
 * DAO abstract class for teacher entity. Follows the singleton pattern.
 */
public abstract class TeacherDAO implements DAO {
    private static DAOManager daoManager = new DAOManager(TeacherDAO.class, TeacherDAOMap.class);

    /**
     * Provides an instance of this DAO.
     */
    public static TeacherDAO getInstance() {
        return (TeacherDAO) daoManager.getInstance();
    }

    /**
     * Stores a new teacher entity.
     */
    public abstract void create(
        String firstName,
        String surname,
        String email,
        String phoneNumber,
        LocalDate birthDate);

    /**
     * Retrieves all stored teacher entities.
     */
    public abstract List<Teacher> getAll();

    /**
     * Deletes a teacher entity identified by its ID from the storage.
     */
    public abstract void delete(Teacher.ID id);
}
