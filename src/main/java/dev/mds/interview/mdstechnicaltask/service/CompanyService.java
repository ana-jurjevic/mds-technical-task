package dev.mds.interview.mdstechnicaltask.service;

import java.util.List;

import dev.mds.interview.mdstechnicaltask.model.Company;

public interface CompanyService {

	public List<Company> getData();
	
	public Company getById(Integer id);
	
	public Company insert(Company entity);
	
	public Company update(Company entity);
	
	public void delete(Company entity);
	
	public void delete(Integer id);
}