package com.icloud.service.signup.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.signup.SignUpRecordMapper;
import com.icloud.model.signup.SignUpRecord;
import com.icloud.service.signup.SignUpRecordService;

@Service
public class SignUpRecordServiceImpl implements SignUpRecordService {

	@Autowired
	private SignUpRecordMapper mapper;
	@Override
	public PageInfo<SignUpRecord> findForList(int pageNo,int pageSize,SignUpRecord signUpRecord) {
		PageHelper.startPage(pageNo, pageSize);
		return new PageInfo<SignUpRecord>(mapper.findForList(signUpRecord));
	}

}
