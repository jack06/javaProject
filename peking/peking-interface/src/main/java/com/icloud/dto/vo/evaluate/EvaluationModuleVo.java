package com.icloud.dto.vo.evaluate;

public class EvaluationModuleVo {

	private ConfigVo config;
	private EvaluationVo evaluation;
	private boolean hasEvaluation = false;
	
	public ConfigVo getConfig() {
		return config;
	}
	public void setConfig(ConfigVo config) {
		this.config = config;
	}
	
	public EvaluationVo getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(EvaluationVo evaluation) {
		this.evaluation = evaluation;
	}
	public boolean isHasEvaluation() {
		return hasEvaluation;
	}
	public void setHasEvaluation(boolean hasEvaluation) {
		this.hasEvaluation = hasEvaluation;
	}
	
}
