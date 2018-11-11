package com.jpmorgan.salesprocessing.model;

import java.math.BigDecimal;

/**
 * Model class for Report
 * @author anshu.singh
 *
 */
public class Report {

	private long salesCount;
	private String productType;
	private BigDecimal totalValue;


	public Report() {
	}
	
	public Report(long salesCount, String productType, BigDecimal totalValue) {
		this.salesCount = salesCount;
		this.productType = productType;
		this.totalValue = totalValue;
	}
	
	/**
	 * @return the salesCount
	 */
	public long getSalesCount() {
		return salesCount;
	}
	/**
	 * @param salesCount the salesCount to set
	 */
	public void setSalesCount(long salesCount) {
		this.salesCount = salesCount;
	}
	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return the totalValue
	 */
	public BigDecimal getTotalValue() {
		return totalValue;
	}
	/**
	 * @param totalValue the totalValue to set
	 */
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
	
	
}
