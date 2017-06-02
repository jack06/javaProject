package com.icloud.service.signup.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.signup.SignUpConfigMapper;
import com.icloud.model.signup.SignUpConfig;
import com.icloud.service.signup.SignUpConfigService;

@Service
public class SignUpConfigServiceImpl implements SignUpConfigService {
	@Autowired
	private SignUpConfigMapper mapper;

	@Override
	public List<SignUpConfig> findForList(SignUpConfig config) {
		return mapper.findForList(config);
	}

}
