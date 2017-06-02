package com.icloud.service.signup.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.signup.SignUpConfigMapper;
import com.icloud.model.signup.SignUpConfig;
import com.icloud.service.signup.SignUpConfigService;

@Service
public class SignUpConfigServiceImpl implements SignUpConfigService {
     
	@Autowired
	private SignUpConfigMapper signUpConfigMapper;
	@Override
	public void save(SignUpConfig t) throws Exception {
		signUpConfigMapper.save(t);
	}

	@Override
	public void update(SignUpConfig t) throws Exception {
		signUpConfigMapper.update(t);
	}

	@Override
	public PageInfo<SignUpConfig> findByPage(int pageNo, int pageSize, SignUpConfig t) throws Exception {
		return null;
	}

	@Override
	public SignUpConfig findByKey(String id) throws Exception {
		return signUpConfigMapper.findForObject(id);
	}

	@Override
	public void deleteByKey(String id) throws Exception {
		signUpConfigMapper.deleteByKey(id);
	}

	@Override
	public int findCount(SignUpConfig t) throws Exception {
		return signUpConfigMapper.findCount(t);
	}

}
