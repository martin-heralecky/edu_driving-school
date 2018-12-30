package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.integration.impl.VehicleDAOMap;
import cz.martinheralecky.edu.driving_school.model.Vehicle;

import java.util.List;

/**
 * DAO abstract class for vehicle entity. Follows the singleton pattern.
 */
public abstract class VehicleDAO implements DAO {
    private static DAOManager daoManager = new DAOManager(VehicleDAO.class, VehicleDAOMap.class);

    /**
     * Provides an instance of this DAO.
     */
    public static VehicleDAO getInstance() {
        return (VehicleDAO) daoManager.getInstance();
    }

    /**
     * Stores a new vehicle entity.
     */
    public abstract void create(String licensePlate, String make, String model, int year, String color);

    /**
     * Retrieves all stored vehicle entities.
     */
    public abstract List<Vehicle> getAll();

    /**
     * Deletes a vehicle entity identified by its ID from the storage.
     */
    public abstract void delete(Vehicle.ID id);
}
