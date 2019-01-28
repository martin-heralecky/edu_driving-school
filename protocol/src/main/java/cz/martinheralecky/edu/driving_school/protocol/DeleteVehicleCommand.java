package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.model.Vehicle;

/**
 * A {@link Command} that deletes a vehicle, when executed.
 */
public class DeleteVehicleCommand extends FacadeCommand<Void> {
    /**
     * ID of the entity that is to be deleted.
     */
    private final Vehicle.ID vehicleID;

    /**
     * @param id ID of the entity to delete.
     */
    public DeleteVehicleCommand(Vehicle.ID id) {
        this.vehicleID = id;
    }

    @Override
    public Void execute() throws Exception {
        getFacade().deleteVehicle(vehicleID);

        return null;
    }
}
