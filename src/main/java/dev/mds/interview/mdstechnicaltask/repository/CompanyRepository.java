package dev.mds.interview.mdstechnicaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.mds.interview.mdstechnicaltask.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
