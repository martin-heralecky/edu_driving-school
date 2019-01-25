package cz.martinheralecky.edu.driving_school.integration_nonpersistent.impl;

import cz.martinheralecky.edu.driving_school.integration.VehicleDAO;
import cz.martinheralecky.edu.driving_school.model.Vehicle;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * {@link VehicleDAO} implementation which uses a Map to store entities and is non-persistent.
 */
@Component
public class VehicleDAOMap implements VehicleDAO {
    /**
     * Value of an ID of a new entity. Increments every time a new entity is stored.
     */
    private static int autoIncrement = 1;

    /**
     * The store containing all entities. Key always matches {@code value.getID()}.
     */
    private Map<Vehicle.ID, Vehicle> store = new TreeMap<>();

    @Override
    public void create(String licensePlate, String make, String model, int year, String color) {
        Vehicle vehicle = new Vehicle(new Vehicle.ID(autoIncrement), licensePlate, make, model, year, color);

        store.put(vehicle.getID(), vehicle);
        ++autoIncrement;
    }

    @Override
    public List<Vehicle> getAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Vehicle.ID id) {
        store.remove(id);
    }
}
