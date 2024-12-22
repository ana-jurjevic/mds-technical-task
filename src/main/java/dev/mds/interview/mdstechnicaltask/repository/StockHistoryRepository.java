package dev.mds.interview.mdstechnicaltask.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.mds.interview.mdstechnicaltask.model.Stock;
import dev.mds.interview.mdstechnicaltask.model.StockHistory;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

	@Query("select e from StockHistory e where e.date >= :dateFrom and e.date <= :dateTo and e.stock = :stock order by e.date asc")
	public List<StockHistory> search(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo,
			@Param("stock") Stock stock);
}
