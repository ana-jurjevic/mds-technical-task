package dev.mds.interview.mdstechnicaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.mds.interview.mdstechnicaltask.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query("select e from Stock e where e.code = :code")
	Stock getByCode(@Param("code") String code);
}
