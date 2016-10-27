package com.rohirym.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rohirym.dao.DomainDAO;
import com.rohirym.entity.Domain;
import com.rohirym.util.HibernateUtil;

@Repository
public class DomainDAOImpl implements DomainDAO{
	
	public DomainDAOImpl() {
        System.out.println("DomainDAOImpl");
    }

	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public long createDomain(Domain domain) {
		return (Long) hibernateUtil.create(domain);
	}

	@Override
	public Domain updateDomain(Domain domain) {
		return hibernateUtil.update(domain);
	}

	@Override
	public void deleteDomain(long id) {
		Domain domain = new Domain();
		domain.setId(id);
		hibernateUtil.delete(domain);
	}

	@Override
	public List<Domain> getAllDomains() {
		return hibernateUtil.fetchAll(Domain.class);
	}

	@Override
	public Domain getDomain(long id) {
		return hibernateUtil.fetchById(id, Domain.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Domain> getAllDomains(String searchName) {
		String query = "SELECT d.* FROM Domains d WHERE d.domainName like '%" + searchName + "%'";
		List<Object[]> domainObjects = hibernateUtil.fetchAll(query);
		List<Domain> domains = new ArrayList<Domain>();
		for (Object[] domainObject : domainObjects) {
			Domain domain = new Domain();
			long id = ((BigInteger) domainObject[0]).longValue();
			String domainName = (String) domainObject[1];
			domain.setId(id);
			domain.setDomainName(domainName);
			domains.add(domain);
		}
		System.out.println(domains);
		return domains;
	}
}
