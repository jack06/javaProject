package com.icloud.service.signup;

import com.github.pagehelper.PageInfo;
import com.icloud.model.signup.SignUpRecord;

public interface SignUpRecordService {
	PageInfo<SignUpRecord> findForList(int pageNo,int pageSize,SignUpRecord signUpRecord);
}
