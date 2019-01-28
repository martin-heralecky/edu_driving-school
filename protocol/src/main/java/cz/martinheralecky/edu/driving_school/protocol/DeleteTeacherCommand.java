package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.model.Teacher;

/**
 * A {@link Command} that deletes a teacher, when executed.
 */
public class DeleteTeacherCommand extends FacadeCommand<Void> {
    /**
     * ID of the entity that is to be deleted.
     */
    private final Teacher.ID teacherID;

    /**
     * @param id ID of the entity to delete.
     */
    public DeleteTeacherCommand(Teacher.ID id) {
        this.teacherID = id;
    }

    @Override
    public Void execute() throws Exception {
        getFacade().deleteTeacher(teacherID);

        return null;
    }
}
