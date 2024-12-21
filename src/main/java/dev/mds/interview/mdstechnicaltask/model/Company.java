package dev.mds.interview.mdstechnicaltask.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UNIQUE_CODE", columnNames = { "code" }))
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Code is mandatory")
	private String code;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotNull(message = "Established on is mandatory")
	private Date establishedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEstablishedOn() {
		return establishedOn;
	}

	public void setEstablishedOn(Date establishedOn) {
		this.establishedOn = establishedOn;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", code=" + code + ", name=" + name + ", establishedOn=" + establishedOn + "]";
	}
}