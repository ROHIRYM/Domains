package com.rohirym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rohirym.dao.DomainDAO;
import com.rohirym.entity.Domain;
import com.rohirym.service.DomainService;

@Service
@Transactional
public class DomainServiceImpl implements DomainService{

	public DomainServiceImpl() {
        System.out.println("DomainServiceImpl()");
    }
    
    @Autowired
    private DomainDAO domainDAO;

    @Override
    public long createDomain(Domain domain) {
        return domainDAO.createDomain(domain);
    }
    @Override
    public Domain updateDomain(Domain domain) {
        return domainDAO.updateDomain(domain);
    }
    @Override
    public void deleteDomain(long id) {
        domainDAO.deleteDomain(id);
    }
    @Override
    public List<Domain> getAllDomains() {
        return domainDAO.getAllDomains();
    }
    @Override
    public Domain getDomain(long id) {
        return domainDAO.getDomain(id);
    }    
    @Override
    public List<Domain> getAllDomains(String searchName) {
        return domainDAO.getAllDomains(searchName);
    }
}
