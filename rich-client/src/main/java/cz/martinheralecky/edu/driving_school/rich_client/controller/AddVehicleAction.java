package cz.martinheralecky.edu.driving_school.rich_client.controller;

import cz.martinheralecky.edu.driving_school.rich_client.FacadeProvider;
import cz.martinheralecky.edu.driving_school.rich_client.FormDialog;
import cz.martinheralecky.edu.driving_school.utils.Messages;
import javafx.scene.control.ButtonType;

/**
 * Class representing an action that asks the user for data (via {@link FormDialog}) and then adds a new vehicle.
 */
public class AddVehicleAction implements Action {
    private final FacadeProvider facadeProvider;

    public AddVehicleAction(FacadeProvider facadeProvider) {
        this.facadeProvider = facadeProvider;
    }

    @Override
    public void execute() throws Exception {
        var dialog = new FormDialog.Builder()
            .addField(VehicleProperty.LICENSE_PLATE, Messages.vehicle_licensePlate.getCapitalized())
            .addField(VehicleProperty.MAKE, Messages.vehicle_make.getCapitalized())
            .addField(VehicleProperty.MODEL, Messages.vehicle_model.getCapitalized())
            .addField(VehicleProperty.YEAR, Messages.vehicle_year.getCapitalized())
            .addField(VehicleProperty.COLOR, Messages.vehicle_color.getCapitalized())
            .build();

        var dialogRes = dialog.showAndWait();

        if (dialogRes.isPresent() && dialogRes.get().equals(ButtonType.OK)) {
            facadeProvider.getFacade().addVehicle(
                dialog.getFieldValue(VehicleProperty.LICENSE_PLATE).trim(),
                dialog.getFieldValue(VehicleProperty.MAKE).trim(),
                dialog.getFieldValue(VehicleProperty.MODEL).trim(),
                Integer.parseInt(dialog.getFieldValue(VehicleProperty.YEAR)), // todo: handle non-integer value
                dialog.getFieldValue(VehicleProperty.COLOR).trim());
        }
    }

    enum VehicleProperty {
        LICENSE_PLATE,
        MAKE,
        MODEL,
        YEAR,
        COLOR
    }
}
