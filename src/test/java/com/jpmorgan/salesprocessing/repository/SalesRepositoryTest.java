package com.jpmorgan.salesprocessing.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpmorgan.salesprocessing.model.Operation;
import com.jpmorgan.salesprocessing.model.Sale;

/**
 * Test class for SalesRepository
 * @author anshu.singh
 *
 */
@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackages = { "com.jpmorgan.salesprocessing.repository" })
@EntityScan(basePackages = { "com.jpmorgan.salesprocessing" })
@TestPropertySource("classpath:application.properties")
@DataJpaTest
@ContextConfiguration(classes = { SalesRepository.class })
public class SalesRepositoryTest {

	@Autowired
	SalesRepository salesRepository;

	@Test
	public void testSaveMultipleSales() {
		salesRepository.saveMultipleSales(2, new Sale("Apple", new BigDecimal(15.5)));
		assertEquals(2, salesRepository.findAll().size());
	}

	@Test
	public void testAdjustSalesAddOperation() {
		Sale s = new Sale("Apple", new BigDecimal(10));
		salesRepository.save(s);
		salesRepository.adjustSales(Operation.ADD, new Sale("Apple", new BigDecimal(20)));
		assertEquals(new BigDecimal(30), salesRepository.findById(s.getId()).get().getValue());
	}

	@Test
	public void testAdjustSalesSubtractOperation() {
		Sale s = new Sale("Apple", new BigDecimal(30));
		salesRepository.save(s);
		salesRepository.adjustSales(Operation.SUBTRACT, new Sale("Apple", new BigDecimal(20)));
		assertEquals(new BigDecimal(10), salesRepository.findById(s.getId()).get().getValue());
	}

	@Test
	public void testAdjustSalesMultiplyOperation() {
		Sale s = new Sale("Apple", new BigDecimal(10));
		salesRepository.save(s);
		salesRepository.adjustSales(Operation.MULTIPLY, new Sale("Apple", new BigDecimal(4)));
		assertEquals(new BigDecimal(40), salesRepository.findById(s.getId()).get().getValue());
	}

	@Test
	public void testFindByProduct() {
		salesRepository.save(new Sale("Orange", new BigDecimal(10)));
		List<Sale> salesList = salesRepository.findByProductType("Orange");
		assertNotNull(salesList);

	}
}
