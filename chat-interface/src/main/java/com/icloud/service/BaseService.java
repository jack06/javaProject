package com.icloud.service;

import com.github.pagehelper.PageInfo;

public interface BaseService<T> {
	
	
	public void save(T t) throws Exception;
	
	public void update(T t)throws Exception;
	
	public  PageInfo<T> findByPage(int pageNo,int pageSize,T t)throws Exception;
	
	public T findByKey(String id)throws Exception;
	
	public void deleteByKey(String id)throws Exception;
	
	public int findCount(T t) throws  Exception;

}
