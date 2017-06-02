package com.icloud.service.doubleChoice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.doubleChoice.CompanyDetailsMapper;
import com.icloud.model.doubleChoice.CompanyDetails;
import com.icloud.service.doubleChoice.CompanyDetailsService;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {
   
	@Autowired
	private CompanyDetailsMapper companyDetailsMapper;
	
	@Override
	public void save(CompanyDetails companyDetails) {
		companyDetailsMapper.save(companyDetails);		
	}

	@Override
	public List<CompanyDetails> selectNameList(String eventId) {
		return companyDetailsMapper.selectNameList(eventId);
	}

	@Override
	public CompanyDetails findByKey(String id) {
		return companyDetailsMapper.findByKey(id);
	}

	@Override
	public void batchInsert(List<CompanyDetails> list) {
		companyDetailsMapper.batchInsert(list);
	}

}
