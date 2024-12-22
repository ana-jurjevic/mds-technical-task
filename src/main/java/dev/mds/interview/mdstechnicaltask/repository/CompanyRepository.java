package dev.mds.interview.mdstechnicaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.mds.interview.mdstechnicaltask.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("select e from Company e where e.code = :code")
	Company getByCode(@Param("code") String code);
}
