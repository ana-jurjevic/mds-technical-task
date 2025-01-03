package dev.mds.interview.mdstechnicaltask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mds.interview.mdstechnicaltask.model.StockHistory;
import dev.mds.interview.mdstechnicaltask.service.StockHistoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stockhistory")
public class StockHistoryController {

	@Autowired
	private StockHistoryService service;

	@GetMapping
	public List<StockHistory> findAll() {
		return service.getData();
	}

	@PostMapping
	public StockHistory create(@RequestBody @Valid StockHistory entity) {
		return service.insert(entity);
	}

	@PutMapping("/{id}")
	public StockHistory update(@RequestBody StockHistory entity, @PathVariable Long id) {
		return service.update(entity);
	}

	@DeleteMapping
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}