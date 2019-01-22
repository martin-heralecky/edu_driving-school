package cz.martinheralecky.edu.driving_school.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {
    Vehicle vehicle = new Vehicle(new Entity.ID(1), "1A2 3456", "Škoda", "Octavia RS", 2016, "green");

    @Test
    void gettersReturnCorrectly() {
        assertEquals(new Entity.ID(1), vehicle.getID());
        assertEquals(1, vehicle.getID().getValue());
        assertEquals("1A2 3456", vehicle.getLicensePlate());
        assertEquals("Škoda", vehicle.getMake());
        assertEquals("Octavia RS", vehicle.getModel());
        assertEquals(2016, vehicle.getYear());
        assertEquals("green", vehicle.getColor());
    }
}
