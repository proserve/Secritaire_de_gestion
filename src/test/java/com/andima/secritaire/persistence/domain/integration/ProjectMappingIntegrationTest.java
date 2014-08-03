package com.andima.secritaire.persistence.domain.integration;


import com.andima.secritaire.config.jpa.JpaConfiguration;
import com.andima.secritaire.config.jpa.drivers.HSQLDBDriverJpaConfiguration;
import com.andima.secritaire.config.jpa.drivers.MySQLDriverJpaConfiguration;
import com.andima.secritaire.config.jpa.drivers.PostgresDriverJpaConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static com.andima.secritaire.persistence.domain.fixture.JpaAssertion.assertThatTableExiste;
import static com.andima.secritaire.persistence.domain.fixture.JpaAssertion.assertThatTableHasColumn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
@Transactional

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
