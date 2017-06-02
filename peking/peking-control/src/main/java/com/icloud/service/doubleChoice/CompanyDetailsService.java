package com.icloud.service.doubleChoice;

import java.util.List;

import com.icloud.model.doubleChoice.CompanyDetails;

public interface CompanyDetailsService {
	void save(CompanyDetails companyDetails);
	List<CompanyDetails> selectNameList(String eventId);
	CompanyDetails findByKey(String id);
	void batchInsert(List<CompanyDetails> list);
}
