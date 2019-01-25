package cz.martinheralecky.edu.driving_school.integration_nonpersistent.impl;

import cz.martinheralecky.edu.driving_school.integration.TeacherDAO;
import cz.martinheralecky.edu.driving_school.model.Teacher;
import org.osgi.service.component.annotations.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * {@link TeacherDAO} implementation which uses a Map to store entities and is non-persistent.
 */
@Component
public class TeacherDAOMap implements TeacherDAO {
    /**
     * Value of an ID of a new entity. Increments every time a new entity is stored.
     */
    private static int autoIncrement = 1;

    /**
     * The store containing all entities. Key always matches {@code value.getID()}.
     */
    private Map<Teacher.ID, Teacher> store = new TreeMap<>();

    @Override
    public void create(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate) {
        Teacher teacher =
            new Teacher(new Teacher.ID(autoIncrement), firstName, surname, email, phoneNumber, birthDate);

        store.put(teacher.getID(), teacher);
        ++autoIncrement;
    }

    @Override
    public List<Teacher> getAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Teacher.ID id) {
        store.remove(id);
    }
}
