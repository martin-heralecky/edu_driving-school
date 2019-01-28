package cz.martinheralecky.edu.driving_school.integration_persistent.impl;

import cz.martinheralecky.edu.driving_school.integration.VehicleDAO;
import cz.martinheralecky.edu.driving_school.integration_persistent.ConnectionProvider;
import cz.martinheralecky.edu.driving_school.model.Vehicle;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link VehicleDAO} implementation which uses MySQL database to store entities.
 */
@Component
public class VehicleDAOMySQL implements VehicleDAO {
    @Reference
    ConnectionProvider connProvider;

    private PreparedStatement insertPs;
    private PreparedStatement selectPs;
    private PreparedStatement deletePs;

    @Override
    public void create(String licensePlate, String make, String model, int year, String color) throws SQLException {
        var stmt = getInsertPs();

        stmt.setString(1, licensePlate);
        stmt.setString(2, make);
        stmt.setString(3, model);
        stmt.setInt(4, year);
        stmt.setString(5, color);

        stmt.execute();
    }

    @Override
    public List<Vehicle> getAll() throws SQLException {
        var result = new ArrayList<Vehicle>();

        var queryResult = getSelectPs().executeQuery();
        while (queryResult.next()) {
            result.add(new Vehicle(
                new Vehicle.ID(queryResult.getInt(1)),
                queryResult.getString(2),
                queryResult.getString(3),
                queryResult.getString(4),
                queryResult.getInt(5),
                queryResult.getString(6)));
        }

        return result;
    }

    @Override
    public void delete(Vehicle.ID id) throws SQLException {
        var stmt = getDeletePs();
        stmt.setInt(1, id.getValue());
        stmt.execute();
    }

    private PreparedStatement getInsertPs() throws SQLException {
        if (insertPs == null) {
            insertPs = connProvider.getConn().prepareStatement(
                "INSERT INTO `vehicles` (`licensePlate`, `make`, `model`, `year`, `color`) VALUES (?, ?, ?, ?, ?)");
        }

        return insertPs;
    }

    private PreparedStatement getSelectPs() throws SQLException {
        if (selectPs == null) {
            selectPs = connProvider.getConn().prepareStatement(
                "SELECT `id`, `licensePlate`, `make`, `model`, `year`, `color` FROM `vehicles`");
        }

        return selectPs;
    }

    private PreparedStatement getDeletePs() throws SQLException {
        if (deletePs == null) {
            deletePs = connProvider.getConn().prepareStatement("DELETE FROM `vehicles` WHERE `id` = ?");
        }

        return deletePs;
    }
}
