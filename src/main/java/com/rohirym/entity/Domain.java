package com.rohirym.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "domains")
public class Domain implements Serializable{

	private static final long serialVersionUID = 2307195906328230908L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column
    @NotEmpty
    @Pattern(message = "Not valid domain", regexp = "^(https?://)?([A-Za-z0-9]{1,63}\\.)+[A-Za-z]{2,6}$")
    private String domainName;

	public Domain() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
}
