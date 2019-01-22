package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.model.Student;

/**
 * A {@link Command} that adds a student, when executed.
 */
public class AddStudentCommand extends FacadeCommand<Void> {
    /**
     * The entity that is to be added.
     */
    private final Student student;

    /**
     * @param student The entity to add.
     */
    public AddStudentCommand(Student student) {
        this.student = student;
    }

    @Override
    public Void execute() {
        facade.addStudent(
            student.getFirstName(),
            student.getSurname(),
            student.getEmail(),
            student.getPhoneNumber(),
            student.getBirthDate());

        return null;
    }
}
