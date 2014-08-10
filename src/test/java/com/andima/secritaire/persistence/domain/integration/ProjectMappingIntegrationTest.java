package com.andima.secritaire.persistence.domain.integration;


import com.andima.secritaire.config.jpa.JpaConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import static com.andima.secritaire.persistence.domain.fixture.JpaAssertion.assertThatTableExiste;
import static com.andima.secritaire.persistence.domain.fixture.JpaAssertion.assertThatTableHasColumn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class ProjectMappingIntegrationTest {
    @Autowired
    EntityManager entityManager;

    @Test
    public void thatProjectCustomMappingWork() throws Exception {
        assertThatTableExiste(entityManager, "Project");
        assertThatTableHasColumn(entityManager, "Project", "id");
        assertThatTableHasColumn(entityManager, "Project", "name");
        assertThatTableHasColumn(entityManager, "Project", "parent_id");
        assertThatTableHasColumn(entityManager, "Project", "start_date");
        assertThatTableHasColumn(entityManager, "Project", "end_date");
    }
}