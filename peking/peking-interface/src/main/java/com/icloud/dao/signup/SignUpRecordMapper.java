package com.icloud.dao.signup;

import com.icloud.dao.DAO;
import com.icloud.model.signup.SignUpRecord;

public interface SignUpRecordMapper extends DAO<SignUpRecord> {
	SignUpRecord findByUser(SignUpRecord signUpRecord);
}
