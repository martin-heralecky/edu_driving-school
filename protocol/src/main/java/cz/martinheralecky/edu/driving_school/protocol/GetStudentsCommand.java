package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.model.Student;

import java.util.List;

/**
 * A {@link Command} that provides a list of all students, when executed.
 */
public class GetStudentsCommand extends FacadeCommand<List<Student>> {
    @Override
    public List<Student> execute() throws Exception {
        return getFacade().getStudents();
    }
}
