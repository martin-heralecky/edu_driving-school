package cz.martinheralecky.edu.driving_school.rich_client.controller;

import cz.martinheralecky.edu.driving_school.business.Facade;
import cz.martinheralecky.edu.driving_school.rich_client.FormDialog;
import cz.martinheralecky.edu.driving_school.utils.Messages;
import javafx.scene.control.ButtonType;
import org.osgi.framework.FrameworkUtil;

/**
 * Class representing an action that asks the user for data (via {@link FormDialog}) and then adds a new vehicle.
 */
public class AddVehicleAction implements Action {
    /**
     * The facade service.
     */
    private Facade facade;

    public AddVehicleAction() {
        var bundleContext = FrameworkUtil.getBundle(AddVehicleAction.class).getBundleContext();
        var facadeServiceRef = bundleContext.getServiceReference(Facade.class);
        facade = bundleContext.getService(facadeServiceRef);
    }

    @Override
    public void execute() {
        var dialog = new FormDialog.Builder()
            .addField(VehicleProperty.LICENSE_PLATE, Messages.vehicle_licensePlate.getCapitalized())
            .addField(VehicleProperty.MAKE, Messages.vehicle_make.getCapitalized())
            .addField(VehicleProperty.MODEL, Messages.vehicle_model.getCapitalized())
            .addField(VehicleProperty.YEAR, Messages.vehicle_year.getCapitalized())
            .addField(VehicleProperty.COLOR, Messages.vehicle_color.getCapitalized())
            .build();

        var dialogRes = dialog.showAndWait();

        dialogRes.ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.OK)) {
                facade.addVehicle(
                    dialog.getFieldValue(VehicleProperty.LICENSE_PLATE).trim(),
                    dialog.getFieldValue(VehicleProperty.MAKE).trim(),
                    dialog.getFieldValue(VehicleProperty.MODEL).trim(),
                    Integer.parseInt(dialog.getFieldValue(VehicleProperty.YEAR)), // todo: handle non-integer value
                    dialog.getFieldValue(VehicleProperty.COLOR).trim());
            }
        });
    }

    enum VehicleProperty {
        LICENSE_PLATE,
        MAKE,
        MODEL,
        YEAR,
        COLOR
    }
}