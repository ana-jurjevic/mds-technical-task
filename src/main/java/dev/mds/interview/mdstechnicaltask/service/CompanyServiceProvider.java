package dev.mds.interview.mdstechnicaltask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mds.interview.mdstechnicaltask.model.Company;
import dev.mds.interview.mdstechnicaltask.repository.CompanyRepository;
import dev.mds.interview.mdstechnicaltask.system.AppLogger;

@Service
public class CompanyServiceProvider implements CompanyService {
	private static final AppLogger logger = AppLogger.of(CompanyServiceProvider.class);
	
	@Autowired
	CompanyRepository repository;

	@Override
	public List<Company> getData() {
		logger.debug("CompanyServiceProvider getData");
		return repository.findAll();
	}

	@Override
	public Company insert(Company entity) {
		logger.info("CompanyServiceProvider insert " + entity.toString());
		return repository.save(entity);
	}

	@Override
	public Company update(Company entity) {
		logger.info("CompanyServiceProvider update " + entity.toString());
		return repository.save(entity);
	}

	@Override
	public void delete(Company entity) {
		logger.info("CompanyServiceProvider delete " + entity.toString());
		repository.delete(entity);
	}

	@Override
	public Company getById(Long id) {
		logger.info("CompanyServiceProvider getById " + id.toString());
		return repository.getReferenceById(id);
	}

	@Override
	public void delete(Long id) {
		logger.info("CompanyServiceProvider delete " + id);
		repository.deleteById(id);
	}

	@Override
	public Company getByCode(String code) {
		logger.info("CompanyServiceProvider getByCode " + code);
		return repository.getByCode(code);
	}
}
