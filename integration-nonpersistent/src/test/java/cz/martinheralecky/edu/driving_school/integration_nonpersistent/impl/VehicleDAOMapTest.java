package cz.martinheralecky.edu.driving_school.integration_nonpersistent.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VehicleDAOMapTest {
    @Test
    void createGetAllDeleteWorkCorrectly() {
        var dao = new VehicleDAOMap();

        dao.create("1A2 345", "Opel", "Astra", 2000, "black");
        assertEquals(1, dao.getAll().size());
        assertEquals("1A2 345", dao.getAll().get(0).getLicensePlate());
        assertEquals("Opel", dao.getAll().get(0).getMake());
        assertEquals("Astra", dao.getAll().get(0).getModel());
        assertEquals(2000, dao.getAll().get(0).getYear());
        assertEquals("black", dao.getAll().get(0).getColor());

        dao.create("9Z8 7655", "", "", 0, "");
        assertEquals(2, dao.getAll().size());

        var vehicles = dao.getAll();
        assertNotEquals(vehicles.get(0).getLicensePlate(), vehicles.get(1).getLicensePlate());

        dao.delete(vehicles.get(0).getID());
        assertEquals(1, dao.getAll().size());

        dao.delete(vehicles.get(1).getID());
        assertEquals(0, dao.getAll().size());
    }
}
