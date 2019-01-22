package cz.martinheralecky.edu.driving_school.integration.impl;

import cz.martinheralecky.edu.driving_school.integration.StudentDAO;
import cz.martinheralecky.edu.driving_school.model.Student;
import org.osgi.service.component.annotations.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * {@link StudentDAO} implementation which uses a Map to store entities and is non-persistent.
 */
@Component
public class StudentDAOMap implements StudentDAO {
    /**
     * Value of an ID of a new entity. Increments every time a new entity is stored.
     */
    private static int autoIncrement = 1;

    /**
     * The store containing all entities. Key always matches {@code value.getID()}.
     */
    private Map<Student.ID, Student> store = new TreeMap<>();

    @Override
    public void create(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate) {
        Student student =
            new Student(new Student.ID(autoIncrement), firstName, surname, email, phoneNumber, birthDate);

        store.put(student.getID(), student);
        ++autoIncrement;
    }

    @Override
    public List<Student> getAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Student.ID id) {
        store.remove(id);
    }
}
