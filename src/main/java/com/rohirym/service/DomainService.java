package com.rohirym.service;

import java.util.List;

import com.rohirym.entity.Domain;

public interface DomainService {
	
	public long createDomain(Domain domain);
    public Domain updateDomain(Domain domain);
    public void deleteDomain(long id);
    public List<Domain> getAllDomains();
    public Domain getDomain(long id);   
    public List<Domain> getAllDomains(String searchName);
}
