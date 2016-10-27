package com.rohirym.dto;

import com.rohirym.entity.Domain;

public class DomainAndStatusDTO {
	
	private Domain domain;
	
	private String status;

	public DomainAndStatusDTO(Domain domain) {
		this.domain = domain;
		this.status = "unknown";
	}
	
	public DomainAndStatusDTO(Domain domain, String status) {
		this.domain = domain;
		this.status = status;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
