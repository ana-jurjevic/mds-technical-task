package dev.mds.interview.mdstechnicaltask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mds.interview.mdstechnicaltask.model.Company;
import dev.mds.interview.mdstechnicaltask.service.CompanyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	@Autowired
	private CompanyService service;
	
	@GetMapping
	public List<Company> findAll(){
		return service.getData();
	}
	
	@PostMapping
	public Company create(@RequestBody @Valid Company company) {
		return service.insert(company);
	}
	
	@PutMapping("/{id}")
	public Company update(@RequestBody Company company, @PathVariable Integer id) {
		return service.update(company);
	}
	
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}