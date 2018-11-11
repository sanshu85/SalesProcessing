package com.jpmorgan.salesprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpmorgan.salesprocessing.model.SalesAdjustment;

/**
 * This is an repository interface for Sale Adjustment
 * @author anshu.singh
 *
 */
@Repository
public interface AdjustmentRepository extends JpaRepository<SalesAdjustment, Long>{

}
