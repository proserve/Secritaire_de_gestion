package com.andima.secritaire.persistence.domain.integration;


import com.andima.secritaire.config.JpaConfiguration;
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

public class ClientMappingIntegrationTest {
    @Autowired
    EntityManager entityManager;

    @Test
    public void thatClientCustomMappingWork() throws Exception {
        assertThatTableExiste(entityManager, "client");
        assertThatTableHasColumn(entityManager, "client", "id");
    }
}
