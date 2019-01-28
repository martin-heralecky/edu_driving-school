package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.model.Vehicle;

/**
 * A {@link Command} that adds a vehicle, when executed.
 */
public class AddVehicleCommand extends FacadeCommand<Void> {
    /**
     * The entity that is to be added.
     */
    private final Vehicle vehicle;

    /**
     * @param vehicle The entity to add.
     */
    public AddVehicleCommand(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public Void execute() throws Exception {
        getFacade().addVehicle(
            vehicle.getLicensePlate(),
            vehicle.getMake(),
            vehicle.getModel(),
            vehicle.getYear(),
            vehicle.getColor());

        return null;
    }
}
