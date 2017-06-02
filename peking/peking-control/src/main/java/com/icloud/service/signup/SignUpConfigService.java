package com.icloud.service.signup;

import java.util.List;

import com.icloud.model.signup.SignUpConfig;

public interface SignUpConfigService {
	List<SignUpConfig> findForList(SignUpConfig config);
}
