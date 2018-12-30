package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.integration.impl.TeacherDAOMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherDAOTest {
    @Test
    void getInstanceReturnsMapImpl() {
        assertEquals(TeacherDAOMap.class, TeacherDAO.getInstance().getClass());
    }
}
