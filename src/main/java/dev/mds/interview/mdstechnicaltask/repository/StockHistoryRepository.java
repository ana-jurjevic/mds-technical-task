package dev.mds.interview.mdstechnicaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.mds.interview.mdstechnicaltask.model.StockHistory;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

}
