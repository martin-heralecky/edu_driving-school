package cz.martinheralecky.edu.driving_school.rich_client.controller;

import cz.martinheralecky.edu.driving_school.business.Facade;
import cz.martinheralecky.edu.driving_school.rich_client.FormDialog;
import cz.martinheralecky.edu.driving_school.utils.Messages;
import javafx.scene.control.ButtonType;
import org.osgi.framework.FrameworkUtil;

import java.time.LocalDate;

/**
 * Class representing an action that asks the user for data (via {@link FormDialog}) and then adds a new teacher.
 */
public class AddTeacherAction implements Action {
    /**
     * The facade service.
     */
    private Facade facade;

    public AddTeacherAction() {
        var bundleContext = FrameworkUtil.getBundle(AddTeacherAction.class).getBundleContext();
        var facadeServiceRef = bundleContext.getServiceReference(Facade.class);
        facade = bundleContext.getService(facadeServiceRef);
    }

    @Override
    public void execute()
        throws Exception {
        var dialog = new FormDialog.Builder()
            .addField(TeacherProperty.FIRST_NAME, Messages.teacher_firstName.getCapitalized())
            .addField(TeacherProperty.SURNAME, Messages.teacher_surname.getCapitalized())
            .addField(TeacherProperty.EMAIL, Messages.teacher_email.getCapitalized())
            .addField(TeacherProperty.PHONE_NUMBER, Messages.teacher_phoneNumber.getCapitalized())
            .addField(TeacherProperty.BIRTH_DATE, Messages.teacher_birthDate.getCapitalized())
            .build();

        var dialogRes = dialog.showAndWait();

        if (dialogRes.isPresent() && dialogRes.get().equals(ButtonType.OK)) {
            facade.addTeacher(
                dialog.getFieldValue(TeacherProperty.FIRST_NAME).trim(),
                dialog.getFieldValue(TeacherProperty.SURNAME).trim(),
                dialog.getFieldValue(TeacherProperty.EMAIL).trim(),
                dialog.getFieldValue(TeacherProperty.PHONE_NUMBER).trim(),
                LocalDate.parse(dialog.getFieldValue(TeacherProperty.BIRTH_DATE))); // todo: handle invalid value
        }
    }

    enum TeacherProperty {
        FIRST_NAME,
        SURNAME,
        EMAIL,
        PHONE_NUMBER,
        BIRTH_DATE
    }
}
