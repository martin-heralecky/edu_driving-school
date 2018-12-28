package cz.martinheralecky.edu.driving_school.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AddressTest {
    Address addressA = new Address("Thákurova 9", "Praha", "160 00");
    Address addressA_ = new Address("Thákurova 9", "Praha", "160 00");
    Address addressB = new Address("Preslova 25", "Praha", "150 00");

    @Test
    void gettersReturnCorrectly() {
        assertEquals("Thákurova 9", addressA.getStreet());
        assertEquals("Praha", addressA.getCity());
        assertEquals("160 00", addressA.getZipCode());
    }

    @Test
    void equalsReturnsCorrectly() {
        assertEquals(addressA, addressA);
        assertEquals(addressA, addressA_);
        assertNotEquals(addressA, addressB);
    }
}
