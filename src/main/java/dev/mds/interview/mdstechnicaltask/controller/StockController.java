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

import dev.mds.interview.mdstechnicaltask.model.Stock;
import dev.mds.interview.mdstechnicaltask.service.StockService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
	
	@Autowired
	private StockService service;
	
	@GetMapping
	public List<Stock> findAll(){
		return service.getData();
	}
	
	@PostMapping
	public Stock create(@RequestBody @Valid Stock entity) {
		return service.insert(entity);
	}
	
	@PutMapping("/{id}")
	public Stock update(@RequestBody Stock entity, @PathVariable Integer id) {
		return service.update(entity);
	}
	
	@DeleteMapping
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}