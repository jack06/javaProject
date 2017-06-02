package com.icloud.service.signup;
import com.icloud.model.signup.SignUpRecord;
import com.icloud.service.BaseService;

public interface SignUpRecordService extends BaseService<SignUpRecord> {
	SignUpRecord findByUser(SignUpRecord signUpRecord);
}
