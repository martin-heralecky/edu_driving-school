package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.model.Vehicle;

import java.util.List;

/**
 * DAO interface for vehicle entity.
 */
public interface VehicleDAO {
    /**
     * Stores a new vehicle entity.
     */
    void create(String licensePlate, String make, String model, int year, String color);

    /**
     * Retrieves all stored vehicle entities.
     */
    List<Vehicle> getAll();

    /**
     * Deletes a vehicle entity identified by its ID from the storage.
     */
    void delete(Vehicle.ID id);
}
