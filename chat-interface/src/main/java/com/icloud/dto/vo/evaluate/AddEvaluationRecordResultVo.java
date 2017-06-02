package com.icloud.dto.vo.evaluate;
/**
 * @filename      : AddEvaluationRecordVo.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年4月27日 下午2:01:10   
 * @copyright     : zhumeng.com@chat-interface
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class AddEvaluationRecordResultVo {

	private String evaluationId;//评价记录id
	private Result result;//
	
	public String getEvaluationId() {
		return evaluationId;
	}
	public void setEvaluationId(String evaluationId) {
		this.evaluationId = evaluationId;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	
}
