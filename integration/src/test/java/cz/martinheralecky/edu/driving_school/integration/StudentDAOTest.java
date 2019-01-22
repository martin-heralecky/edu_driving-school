package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.integration.impl.StudentDAOMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDAOTest {
    @Test
    void getInstanceReturnsMapImpl() {
        assertEquals(StudentDAOMap.class, StudentDAO.getInstance().getClass());
    }
}
