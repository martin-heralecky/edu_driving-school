package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.model.Teacher;

/**
 * A {@link Command} that adds a teacher, when executed.
 */
public class AddTeacherCommand extends FacadeCommand<Void> {
    /**
     * The entity that is to be added.
     */
    private final Teacher teacher;

    /**
     * @param teacher The entity to add.
     */
    public AddTeacherCommand(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Void execute() {
        facade.addTeacher(
            teacher.getFirstName(),
            teacher.getSurname(),
            teacher.getEmail(),
            teacher.getPhoneNumber(),
            teacher.getBirthDate());

        return null;
    }
}
