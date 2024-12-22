package dev.mds.interview.mdstechnicaltask.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.mds.interview.mdstechnicaltask.model.Company;
import dev.mds.interview.mdstechnicaltask.model.StockHistory;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

	@Query("select e from StockHistory e where e.date >= :dateFrom and e.date <= :dateTo and e.company = :company order by e.date asc")
	public List<StockHistory> search(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo,
			@Param("company") Company company);
}
