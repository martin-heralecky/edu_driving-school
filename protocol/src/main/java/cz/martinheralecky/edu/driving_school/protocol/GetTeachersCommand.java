package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.model.Teacher;

import java.util.List;

/**
 * A {@link Command} that provides a list of all teachers, when executed.
 */
public class GetTeachersCommand extends FacadeCommand<List<Teacher>> {
    @Override
    public List<Teacher> execute() throws Exception {
        return getFacade().getTeachers();
    }
}
