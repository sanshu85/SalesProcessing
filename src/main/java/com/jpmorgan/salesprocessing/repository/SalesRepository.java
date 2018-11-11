package com.jpmorgan.salesprocessing.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jpmorgan.salesprocessing.model.Operation;
import com.jpmorgan.salesprocessing.model.Report;
import com.jpmorgan.salesprocessing.model.Sale;

/**
 * This an repository interface for Sale
 * 
 * @author anshu.singh
 *
 */
@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {

	/**
	 * This method saves multiple occurences of sale
	 * 
	 * @param occurences
	 * @param sale
	 */
	public default void saveMultipleSales(int occurences, Sale sale) {
		List<Sale> salesList = new ArrayList<>();
		for (int i = 0; i < occurences; i++) {
			Sale s = new Sale(sale.getProductType(), sale.getValue());
			salesList.add(s);

		}
		saveAll(salesList);
	}

	/**
	 * This method applies the adjustments to sales records depending on operation
	 * 
	 * @param operation
	 * @param sale
	 */
	public default void adjustSales(Operation operation, Sale sale) {

		List<Sale> salesList = findByProductType(sale.getProductType());
		if (operation.equals(Operation.ADD)) {
			for (Sale s : salesList) {
				s.setValue(s.getValue().add(sale.getValue()));
			}

		} else if (operation.equals(Operation.SUBTRACT)) {
			for (Sale s : salesList) {
				s.setValue(s.getValue().subtract(sale.getValue()));
			}
		} else if (operation.equals(Operation.MULTIPLY)) {
			for (Sale s : salesList) {
				s.setValue(s.getValue().multiply(sale.getValue()));
			}
		}
		saveAll(salesList);
	}

	/**
	 * This method fetches report data for sales
	 * 
	 * @return
	 */
	@Query(value = "select new com.jpmorgan.salesprocessing.model.Report(count(*),s.productType,sum(s.value)) from Sale s group by s.productType")
	List<Report> generateReport();

	/**
	 * This method gets the list of sale records by productType
	 * 
	 * @param productType
	 * @return
	 */
	public List<Sale> findByProductType(String productType);
}
