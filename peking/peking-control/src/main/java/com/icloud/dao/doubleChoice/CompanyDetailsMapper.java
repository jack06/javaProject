package com.icloud.dao.doubleChoice;

import java.util.List;

import com.icloud.model.doubleChoice.CompanyDetails;

public interface CompanyDetailsMapper {
	void save(CompanyDetails companyDetails);
	List<CompanyDetails> selectNameList(String eventId);
	CompanyDetails findByKey(String id);
	void batchInsert(List<CompanyDetails> list);
}
