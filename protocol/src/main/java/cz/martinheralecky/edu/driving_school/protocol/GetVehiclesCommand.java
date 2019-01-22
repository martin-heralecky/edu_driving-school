package cz.martinheralecky.edu.driving_school.protocol;

import cz.martinheralecky.edu.driving_school.model.Vehicle;

import java.util.List;

/**
 * A {@link Command} that provides a list of all vehicles, when executed.
 */
public class GetVehiclesCommand extends FacadeCommand<List<Vehicle>> {
    @Override
    public List<Vehicle> execute() {
        return facade.getVehicles();
    }
}
