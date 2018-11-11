package com.jpmorgan.salesprocessing.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class for Sale
 * @author anshu.singh
 *
 */
@Entity
public class Sale {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	private String productType;
	private BigDecimal value;
	
	public Sale() {
	}
	
	public Sale(String productType, BigDecimal value) {
		this.productType = productType;
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
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
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
