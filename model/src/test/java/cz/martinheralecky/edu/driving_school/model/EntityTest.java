package cz.martinheralecky.edu.driving_school.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EntityTest {
    static class TestEntity extends Entity {
        protected TestEntity(ID id) {
            super(id);
        }
    }

    TestEntity entityA = new TestEntity(new Entity.ID(1));
    TestEntity entityA_ = new TestEntity(new Entity.ID(1));
    TestEntity entityB = new TestEntity(new Entity.ID(2));

    @Test
    void getIDReturnsCorrectly() {
        assertEquals(new Entity.ID(1), entityA.getID());
    }

    @Test
    void equalsReturnsCorrectly() {
        assertEquals(entityA, entityA);
        assertEquals(entityA, entityA_);
        assertNotEquals(entityA, entityB);
    }

    @Test
    void hashCodeReturnsDifferentValuesForDifferentEntities() {
        assertNotEquals(entityA.hashCode(), entityB.hashCode());
    }

    @Test
    void hashCodeReturnsEqualValuesForEqualEntities() {
        assertEquals(entityA.hashCode(), entityA_.hashCode());
    }
}
