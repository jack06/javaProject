package com.icloud.dao.signup;

import java.util.List;

import com.icloud.model.signup.SignUpRecord;

public interface SignUpRecordMapper{
	List<SignUpRecord> findForList(SignUpRecord signUpRecord);
}
