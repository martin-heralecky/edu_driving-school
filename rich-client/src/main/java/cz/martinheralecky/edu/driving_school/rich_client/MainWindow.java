package cz.martinheralecky.edu.driving_school.rich_client;

import cz.martinheralecky.edu.driving_school.business.Facade;
import cz.martinheralecky.edu.driving_school.business.Observer;
import cz.martinheralecky.edu.driving_school.business_proxy.FacadeProxy;
import cz.martinheralecky.edu.driving_school.model.Student;
import cz.martinheralecky.edu.driving_school.model.Teacher;
import cz.martinheralecky.edu.driving_school.model.Vehicle;
import cz.martinheralecky.edu.driving_school.rich_client.controller.*;
import cz.martinheralecky.edu.driving_school.utils.Messages;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.osgi.framework.FrameworkUtil;

/**
 * The main window of the application.
 */
class MainWindow extends Stage implements Observer, FacadeProvider {
    /**
     * The facade service.
     */
    private Facade facade;

    private EntityPane<Vehicle> vehiclesPane;
    private EntityPane<Teacher> teachersPane;
    private EntityPane<Student> studentsPane;

    MainWindow() {
        // Initially, we use the local facade. Later, the server connection can be configured.
        setFacadeLocal();

        vehiclesPane = createVehiclesPane();
        teachersPane = createTeachersPane();
        studentsPane = createStudentsPane();

        setTitle(Messages.app_name.getCapitalized());

        var root = new VBox(createMenuBar(), vehiclesPane, teachersPane, studentsPane);

        var scene = new Scene(root, 800, 600);
        setScene(scene);

        show();
    }

    private void setFacadeLocal() {
        setFacade(Facade.class);
    }

    private void setFacadeProxy() {
        setFacade(FacadeProxy.class);
    }

    private void setFacade(Class<? extends Facade> facadeClass) {
        if (facade != null) {
            facade.removeObserver(this);
        }

        var bundleContext = FrameworkUtil.getBundle(MainWindow.class).getBundleContext();
        var facadeServiceRef = bundleContext.getServiceReference(facadeClass);
        facade = bundleContext.getService(facadeServiceRef);

        facade.addObserver(this);

        refresh();
    }

    /**
     * Creates menu bar.
     */
    private Node createMenuBar() {
        var fileMenu = new Menu(Messages.menu_file.getCapitalized());
        fileMenu.getItems().add(new ActionMenuItem(Messages.menu_file_quit.getCapitalized(), new QuitAction()));

        var dataMenu = new Menu(Messages.menu_data.getCapitalized());
        dataMenu.getItems().addAll(
            new ActionMenuItem(Messages.menu_data_connect.getCapitalized(), new ConnectAction(this::setFacadeProxy)),
            new SeparatorMenuItem(),
            new ActionMenuItem(Messages.menu_data_new_vehicle.getCapitalized(), new AddVehicleAction(this)),
            new ActionMenuItem(Messages.menu_data_new_teacher.getCapitalized(), new AddTeacherAction(this)),
            new ActionMenuItem(Messages.menu_data_new_student.getCapitalized(), new AddStudentAction(this)),
            new SeparatorMenuItem(),
            new ActionMenuItem(Messages.menu_data_refresh.getCapitalized(), this::refresh));

        return new MenuBar(fileMenu, dataMenu);
    }

    /**
     * Creates a pane for vehicles.
     */
    private EntityPane<Vehicle> createVehiclesPane() {
        return new EntityPane.Builder<Vehicle>()
            .setTitle(Messages.vehicles.getCapitalized())
            .addColumn(Messages.vehicle_id.getCapitalized(), "ID")
            .addColumn(Messages.vehicle_licensePlate.getCapitalized(), "licensePlate")
            .addColumn(Messages.vehicle_make.getCapitalized(), "make")
            .addColumn(Messages.vehicle_model.getCapitalized(), "model")
            .addColumn(Messages.vehicle_year.getCapitalized(), "year")
            .addColumn(Messages.vehicle_color.getCapitalized(), "color")
            .setRecordsSupplier(() -> getFacade().getVehicles())
            .setOnDeleteRequest(this::deleteSelectedVehicles)
            .build();
    }

    /**
     * Creates a pane for teachers.
     */
    private EntityPane<Teacher> createTeachersPane() {
        return new EntityPane.Builder<Teacher>()
            .setTitle(Messages.teachers.getCapitalized())
            .addColumn(Messages.teacher_id.getCapitalized(), "ID")
            .addColumn(Messages.teacher_firstName.getCapitalized(), "firstName")
            .addColumn(Messages.teacher_surname.getCapitalized(), "surname")
            .addColumn(Messages.teacher_email.getCapitalized(), "email")
            .addColumn(Messages.teacher_phoneNumber.getCapitalized(), "phoneNumber")
            .addColumn(Messages.teacher_birthDate.getCapitalized(), "birthDate")
            .setRecordsSupplier(() -> getFacade().getTeachers())
            .setOnDeleteRequest(this::deleteSelectedTeachers)
            .build();
    }

    /**
     * Creates a pane for students.
     */
    private EntityPane<Student> createStudentsPane() {
        return new EntityPane.Builder<Student>()
            .setTitle(Messages.students.getCapitalized())
            .addColumn(Messages.student_id.getCapitalized(), "ID")
            .addColumn(Messages.student_firstName.getCapitalized(), "firstName")
            .addColumn(Messages.student_surname.getCapitalized(), "surname")
            .addColumn(Messages.student_email.getCapitalized(), "email")
            .addColumn(Messages.student_phoneNumber.getCapitalized(), "phoneNumber")
            .addColumn(Messages.student_birthDate.getCapitalized(), "birthDate")
            .setRecordsSupplier(() -> getFacade().getStudents())
            .setOnDeleteRequest(this::deleteSelectedStudents)
            .build();
    }

    @Override
    public Facade getFacade() {
        return facade;
    }

    /**
     * Reacts to the change of state from {@link Facade}.
     */
    @Override
    public void update() {
        refresh();
    }

    /**
     * Refreshes the data in the entity panes.
     */
    private void refresh() {
        if (vehiclesPane != null && teachersPane != null && studentsPane != null) {
            vehiclesPane.refresh();
            teachersPane.refresh();
            studentsPane.refresh();
        }
    }

    /**
     * Deletes the currently selected vehicles.
     */
    private void deleteSelectedVehicles() {
        // prevent calling refresh() for each record
        facade.removeObserver(this);

        for (var vehicle : vehiclesPane.getSelected()) {
            try {
                facade.deleteVehicle(vehicle.getID());
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                break;
            }
        }

        facade.addObserver(this);

        refresh();
    }

    /**
     * Deletes the currently selected teachers.
     */
    private void deleteSelectedTeachers() {
        // prevent calling refresh() for each record
        facade.removeObserver(this);

        for (var teacher : teachersPane.getSelected()) {
            try {
                facade.deleteTeacher(teacher.getID());
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                break;
            }
        }

        facade.addObserver(this);

        refresh();
    }

    /**
     * Deletes the currently selected students.
     */
    private void deleteSelectedStudents() {
        // prevent calling refresh() for each record
        facade.removeObserver(this);

        for (var student : studentsPane.getSelected()) {
            try {
                facade.deleteStudent(student.getID());
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                break;
            }
        }

        facade.addObserver(this);

        refresh();
    }
}
