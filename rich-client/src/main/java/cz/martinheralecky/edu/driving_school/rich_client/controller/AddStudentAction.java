package cz.martinheralecky.edu.driving_school.rich_client.controller;

import cz.martinheralecky.edu.driving_school.rich_client.FacadeProvider;
import cz.martinheralecky.edu.driving_school.rich_client.FormDialog;
import cz.martinheralecky.edu.driving_school.utils.Messages;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;

/**
 * Class representing an action that asks the user for data (via {@link FormDialog}) and then adds a new student.
 */
public class AddStudentAction implements Action {
    private final FacadeProvider facadeProvider;

    public AddStudentAction(FacadeProvider facadeProvider) {
        this.facadeProvider = facadeProvider;
    }

    @Override
    public void execute()
        throws Exception {
        var dialog = new FormDialog.Builder()
            .addField(StudentProperty.FIRST_NAME, Messages.student_firstName.getCapitalized())
            .addField(StudentProperty.SURNAME, Messages.student_surname.getCapitalized())
            .addField(StudentProperty.EMAIL, Messages.student_email.getCapitalized())
            .addField(StudentProperty.PHONE_NUMBER, Messages.student_phoneNumber.getCapitalized())
            .addField(StudentProperty.BIRTH_DATE, Messages.student_birthDate.getCapitalized())
            .build();

        var dialogRes = dialog.showAndWait();

        if (dialogRes.isPresent() && dialogRes.get().equals(ButtonType.OK)) {
            facadeProvider.getFacade().addStudent(
                dialog.getFieldValue(StudentProperty.FIRST_NAME).trim(),
                dialog.getFieldValue(StudentProperty.SURNAME).trim(),
                dialog.getFieldValue(StudentProperty.EMAIL).trim(),
                dialog.getFieldValue(StudentProperty.PHONE_NUMBER).trim(),
                LocalDate.parse(dialog.getFieldValue(StudentProperty.BIRTH_DATE))); // todo: handle invalid value
        }
    }

    enum StudentProperty {
        FIRST_NAME,
        SURNAME,
        EMAIL,
        PHONE_NUMBER,
        BIRTH_DATE
    }
}
