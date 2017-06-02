package com.icloud.model.event;

/**
 * 系统模块的统一管理
 * @author leiyi
 *
 */
public class Module {
	
	private String id;
	private String name;//模块名称
	private String moduleLabel;//模块标签 唯一，通过该标签判断模块的入口
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModuleLabel() {
		return moduleLabel;
	}
	public void setModuleLabel(String moduleLabel) {
		this.moduleLabel = moduleLabel;
	}
	
	

}
