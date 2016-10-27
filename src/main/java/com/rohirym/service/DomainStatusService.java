package com.rohirym.service;

import java.util.List;

import com.rohirym.dto.DomainAndStatusDTO;
import com.rohirym.entity.Domain;

public interface DomainStatusService {

	public List<DomainAndStatusDTO> lookup(List<Domain> domainList);
}
