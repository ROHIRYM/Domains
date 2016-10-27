package com.rohirym.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rohirym.dto.DomainAndStatusDTO;
import com.rohirym.entity.Domain;
import com.rohirym.service.DomainService;
import com.rohirym.service.DomainStatusService;

@Controller
public class DomainController {

	private static final Logger logger = Logger.getLogger(DomainController.class);

	public DomainController() {
		System.out.println("DomainController()");
	}

	@Autowired
	private DomainService domainService;
	
	@Autowired
	private DomainStatusService domainStatusService;

	@RequestMapping("createDomain")
	public ModelAndView createDomain(@ModelAttribute Domain domain) {
		logger.info("Creating Domain. Data: " + domain);
		return new ModelAndView("domainForm");
	}

	@RequestMapping("editDomain")
	public ModelAndView editDomain(@RequestParam long id, @ModelAttribute Domain domain) {
		logger.info("Updating the Domain for the Id " + id);
		domain = domainService.getDomain(id);
		return new ModelAndView("domainForm", "domainObject", domain);
	}

	@RequestMapping("saveDomain")
	public ModelAndView saveDomain(@ModelAttribute Domain domain) {
		logger.info("Saving the Domain. Data : " + domain);
		if (domain.getId() == 0) { // if domain id is 0 then creating the domain other
									// updating the domain
			domainService.createDomain(domain);
		} else {
			domainService.updateDomain(domain);
		}
		return new ModelAndView("redirect:getAllDomains");
	}

	@RequestMapping("deleteDomain")
	public ModelAndView deleteDomain(@RequestParam long id) {
		logger.info("Deleting the Domain. Id : " + id);
		domainService.deleteDomain(id);
		return new ModelAndView("redirect:getAllDomains");
	}

	@RequestMapping("getAllDomains")
	public ModelAndView getAllDomains() {
		logger.info("Getting the all Domains.");
		return domainListModelAndView(domainService.getAllDomains());
	}

	@RequestMapping("searchDomain")
	public ModelAndView searchDomain(@RequestParam("searchName") String searchName) {
		logger.info("Searching the Domain. Domain Names: " + searchName);
		return domainListModelAndView(domainService.getAllDomains(searchName));
	}
	
	private ModelAndView domainListModelAndView(List<Domain> domainList) {
		List<DomainAndStatusDTO> domainAndStatusList = domainStatusService.lookup(domainList);
		return new ModelAndView("domainList", "domainAndStatusList", domainAndStatusList);
	}
}
