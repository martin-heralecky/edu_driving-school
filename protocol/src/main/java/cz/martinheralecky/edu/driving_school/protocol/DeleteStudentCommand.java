package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.model.Student;

/**
 * A {@link Command} that deletes a student, when executed.
 */
public class DeleteStudentCommand extends FacadeCommand<Void> {
    /**
     * ID of the entity that is to be deleted.
     */
    private final Student.ID studentID;

    /**
     * @param id ID of the entity to delete.
     */
    public DeleteStudentCommand(Student.ID id) {
        this.studentID = id;
    }

    @Override
    public Void execute() throws Exception {
        getFacade().deleteStudent(studentID);

        return null;
    }
}
