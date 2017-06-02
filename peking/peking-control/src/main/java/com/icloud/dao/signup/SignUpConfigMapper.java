package com.icloud.dao.signup;

import java.util.List;

import com.icloud.model.signup.SignUpConfig;

public interface SignUpConfigMapper{
	List<SignUpConfig> findForList(SignUpConfig config);

}
