package cz.martinheralecky.edu.driving_school.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractIDTest {
    static class TestID extends AbstractID<TestID> {
        public TestID(int value) {
            super(value);
        }
    }

    TestID id_1 = new TestID(1);
    TestID id_1_ = new TestID(1);
    TestID id_2 = new TestID(2);
    TestID id_3 = new TestID(3);

    @Test
    void getValueReturnsCorrectly() {
        assertEquals(1, id_1.getValue());
    }

    @Test
    void compareToReturnsCorrectly() {
        assertEquals(0, id_1.compareTo(id_1));
        assertEquals(0, id_1.compareTo(id_1_));
        assertTrue(0 > id_1.compareTo(id_2));
        assertTrue(0 > id_2.compareTo(id_3));
        assertTrue(0 < id_2.compareTo(id_1));
        assertTrue(0 < id_3.compareTo(id_2));
    }

    @Test
    void equalsReturnsCorrectly() {
        assertEquals(id_1, id_1);
        assertEquals(id_1, id_1_);
        assertNotEquals(id_1, id_2);
    }

    @Test
    void hashCodeReturnsDifferentValuesForDifferentIDValues() {
        assertNotEquals(id_1.hashCode(), id_2.hashCode());
    }

    @Test
    void hashCodeReturnsEqualValuesForEqualIDs() {
        assertEquals(id_1.hashCode(), id_1_.hashCode());
    }

    @Test
    void toStringReturnsCorrectly() {
        assertEquals("1", id_1.toString());
    }
}
