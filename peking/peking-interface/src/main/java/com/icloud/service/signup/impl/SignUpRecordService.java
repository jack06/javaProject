package com.icloud.service.signup.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.signup.SignUpRecordMapper;
import com.icloud.model.signup.SignUpRecord;

@Service
public class SignUpRecordService implements com.icloud.service.signup.SignUpRecordService {

	@Autowired
	private SignUpRecordMapper signUpRecordMapper;
	@Override
	public void save(SignUpRecord t) throws Exception {
		signUpRecordMapper.save(t);
	}

	@Override
	public void update(SignUpRecord t) throws Exception {
		
	}

	@Override
	public PageInfo<SignUpRecord> findByPage(int pageNo, int pageSize, SignUpRecord t) throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		return  new PageInfo<SignUpRecord>(signUpRecordMapper.findForList(t));
		
	}

	@Override
	public SignUpRecord findByKey(String id) throws Exception {
		return signUpRecordMapper.findForObject(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		signUpRecordMapper.deleteByKey(id);
	}

	@Override
	public int findCount(SignUpRecord t) throws Exception {
		return signUpRecordMapper.findCount(t);
	}

	@Override
	public SignUpRecord findByUser(SignUpRecord signUpRecord) {
		return signUpRecordMapper.findByUser(signUpRecord);
	}

}
