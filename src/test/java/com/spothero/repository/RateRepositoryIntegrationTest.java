/**
 * 
 */
package com.spothero.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.spothero.model.Rate;

import config.Application;

/**
 * @author nayanankmahajan
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes=Application.class)
public class RateRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private RateRepository rateRepository;
    
    @Test
    public void test() {
    	Rate rate1 = new Rate("mon", 900, 2100, "America/Chicago", 1500.0);
    	Rate rate2 = new Rate("tues", 900, 2100, "America/Chicago", 1500.0);
    	Rate rate3 = new Rate("thurs", 900, 2100, "America/Chicago", 1500.0);
    	
    	entityManager.persist(rate1);
    	entityManager.persist(rate2);
    	entityManager.persist(rate3);
        entityManager.flush();
        
        List<Rate> found = rateRepository.findRatesForInputCriteria("mon", "America/Chicago", 930, 1730);
     
        assertEquals(1, found.size());
    }

}
