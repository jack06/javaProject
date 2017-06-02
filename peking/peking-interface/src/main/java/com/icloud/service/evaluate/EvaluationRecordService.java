package com.icloud.service.evaluate;

import java.util.List;

import com.icloud.model.evaluate.EvaluationRecord;
import com.icloud.service.BaseService;

/**
 * @filename      : EvaluationRecordService.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年4月27日 上午10:15:40   
 * @copyright     : zhumeng.com@chat-interface
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public interface EvaluationRecordService extends BaseService<EvaluationRecord>{

	public List<EvaluationRecord> getList(EvaluationRecord evaluationRecord);
}
