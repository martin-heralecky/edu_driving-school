package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.integration.impl.VehicleDAOMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleDAOTest {
    @Test
    void getInstanceReturnsMapImpl() {
        assertEquals(VehicleDAOMap.class, VehicleDAO.getInstance().getClass());
    }
}
