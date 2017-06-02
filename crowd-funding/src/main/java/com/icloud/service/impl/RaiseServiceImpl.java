package com.icloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.dao.PrizeRecordMapper;
import com.icloud.dao.RaiseMapper;
import com.icloud.dto.LuckyOnesDto;
import com.icloud.dto.RaiseDetailDto;
import com.icloud.dto.RaiseEntityDto;
import com.icloud.dto.RaiseOrderDetailDto;
import com.icloud.dto.RaiseQueryDto;
import com.icloud.form.RaiseForm;
import com.icloud.model.PrizeRecord;
import com.icloud.model.Raise;
import com.icloud.service.PrizeRecordService;
import com.icloud.service.RaiseOrderItemsService;
import com.icloud.service.RaiseService;
import com.icloud.util.DateUtils;
import com.icloud.util.LuckyDrawUtil;
/**
 * 
 * 类名称: RaiseServiceImpl
 * 类描述: 众筹service
 * 创建人: zdh
 * 创建时间:2017年4月12日 上午9:10:50
 */
@Service
public class RaiseServiceImpl implements RaiseService{
	
	public final static Logger log = LoggerFactory.getLogger(RaiseServiceImpl.class);
	   /**
     * 缓存的key
     */
    public static final String THING_ALL_KEY  = "\"thing_all\"";
    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    public static final String DEMO_CACHE_NAME = "raise";
	
	@Autowired
	private RaiseMapper raiseMapper;
	@Autowired
	private PrizeRecordMapper prizeRecordMapper;
	@Autowired
	private RaiseOrderItemsService orderItemsService;
	@Autowired
	private PrizeRecordService prizeRecordService;
	
	
	/**
	 * 添加
	 */
	@CacheEvict(value = DEMO_CACHE_NAME,key = THING_ALL_KEY)
	@Override
	public void add(Raise recoud) {
		raiseMapper.insert(recoud);
	}

	@CacheEvict(value = DEMO_CACHE_NAME,key = THING_ALL_KEY)
	@Override
	public void insertSelective(Raise recoud) {
		raiseMapper.insertSelective(recoud);
	}
	
	/**
	 *  根据id查询 
	 */
	@Override
	public Raise selectByPrimaryKey(Long id){
		return raiseMapper.selectByPrimaryKey(id);
	}
	   
	
	/**
	 * 分页查询(活动中心专用) ,分页 暂停使用
	 */
//	@Cacheable(value = DEMO_CACHE_NAME,key = THING_ALL_KEY)  
//	@Override
//	public RaiseQueryDto listCenter(RaiseForm raiseForm) {
//		QueryBuilder example=new QueryBuilder();
//		//排序设置 期数倒叙，号数顺序
//		example.setOrderByClause("current_period desc,current_num asc");
//		Criteria criteria = example.createCriteria();
//		com.github.pagehelper.Page  pages = PageHelper.startPage(raiseForm.getPageNum(),raiseForm.getPageSize());
//		List<Raise> list = raiseMapper.selectByExample(example);
//				
//	    RaiseQueryDto dto = new RaiseQueryDto("success","10000","",list);
//		dto.setPageNum(pages.getPageNum());
//	    dto.setStartIndex(pages.getStartRow());
//	    dto.setPageSize(pages.getPageSize());
//	    dto.setTotalCount(Long.valueOf(pages.getTotal()).intValue());
//	    dto.setTotalPage(Long.valueOf(pages.getPages()));
//		dto.setRaiseList(list);
//		return dto;
//	}
	
	/**
	 * 分页查询(活动中心专用)，不分页
	 */
	@Cacheable(value = DEMO_CACHE_NAME,key = THING_ALL_KEY)  
	@Override
	public RaiseQueryDto listCenter(RaiseForm raiseForm) {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		criteria.andFieldLessThanOrEqualTo("rownum", 100);
		//排序设置 期数倒叙，号数顺序
		example.setOrderByClause("current_period desc,current_num asc");
		List<Raise> list = raiseMapper.selectByExample(example);
				
	    RaiseQueryDto dto = new RaiseQueryDto("success","10000","",list);
	    dto.setTotalCount(list!=null?list.size():0);
	    dto.setTotalPage(Long.valueOf(dto.getTotalCount()/raiseForm.getPageSize()+1));
		dto.setRaiseList(list);
		return dto;
	}
	
	
	/**
	 * 分页查询
	 */
	@Override
	public RaiseQueryDto listBypPage(RaiseForm raiseForm) {
		QueryBuilder example=new QueryBuilder();
		//排序设置
		example.setOrderByClause("Create_Time  desc");
		Criteria criteria = example.createCriteria();
		//添加查询条件
		Raise raise = raiseForm.getRaise();
		if(null!=raise){
			//query01.众筹名字
			if( null !=raise.getRaiseName() && !"".equals(raise.getRaiseName()) ){
				criteria.andFieldLike("raise_Name","%"+raise.getRaiseName()+"%");
			}
			//query02.期数
			if( null !=raise.getCurrentPeriod() && !"".equals(raise.getCurrentPeriod()) ){
				criteria.andFieldEqualTo("current_Period", raise.getCurrentPeriod());
			}
			//query03.号数
			if( null !=raise.getCurrentNum() && !"".equals(raise.getCurrentNum()) ){
				criteria.andFieldEqualTo("current_Num", raise.getCurrentNum());
			}
			//query04.商品名字
			if( null !=raise.getProductName() && !"".equals(raise.getProductName()) ){
				criteria.andFieldLike("product_Name","%"+raise.getProductName()+"%");
			}
			//query05.众筹状态
			if( null !=raise.getCurrentStatus() && !"".equals(raise.getCurrentStatus()) ){
				criteria.andFieldEqualTo("current_Status", raise.getCurrentStatus());
			}
		}		
		
		com.github.pagehelper.Page  pages = PageHelper.startPage(raiseForm.getPageNum(),raiseForm.getPageSize());
		List<Raise> list = raiseMapper.selectByExample(example);
				
	    RaiseQueryDto dto = new RaiseQueryDto("success","10000","",list);
		dto.setPageNum(pages.getPageNum());
	    dto.setStartIndex(pages.getStartRow());
	    dto.setPageSize(pages.getPageSize());
	    dto.setTotalCount(Long.valueOf(pages.getTotal()).intValue());
	    dto.setTotalPage(Long.valueOf(pages.getPages()));
		dto.setRaiseList(list);
		return dto;
	}

	/**
	 * 修改
	 */
	@CacheEvict(value = DEMO_CACHE_NAME,key = THING_ALL_KEY)
	@Override
	public int updateByPrimaryKeySelective(Raise record) {
		// TODO Auto-generated method stub
		return raiseMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public RaiseQueryDto raisePast(Map<String,Object> map) {
		//一.得到当前页和每页记录数->直接传递过来的
		int pageNum =  Integer.parseInt(map.get("currPage").toString());
		int pageSize = Integer.parseInt(map.get("pageSize").toString());
		//二.查询获取总记录数
		int totalCount = raiseMapper.selectRaseJoinPrizeRecordCount(map);
		//三计算分页开始和结束
		int totalPage = (totalCount/pageSize) + (totalCount%pageSize==0?0:1);
		//四.得到开始和结束记录数
		map.put("begNum", (pageNum-1)*pageSize + 1);
		map.put("endNum", (pageNum*pageSize));
		//创建分页查询对象
		List<Raise> list = raiseMapper.selectRaseJoinPrizeRecord(map);
		//得到返回的结果
	    RaiseQueryDto dto = new RaiseQueryDto("success","10000",list);
	    //设置分页
		dto.setPageNum(pageNum);
	    dto.setStartIndex((pageNum-1)*pageSize + 1);
	    dto.setPageSize(pageSize);
	    //设置总记录数
	    dto.setTotalCount(totalCount);
	    //设置总页数
	    dto.setTotalPage(Long.parseLong(totalPage+""));
		dto.setRaiseList(list);
		return dto;
	}
	
	/**
	 * 众筹详细信息
	 */
	@Override
	public RaiseDetailDto getRaiseById(Long id) {
		Raise raise = raiseMapper.selectByPrimaryKey(id);
		RaiseDetailDto dto = new RaiseDetailDto("success", "10000");
		dto.setId(raise.getId());
		dto.setPrizeAmount(raise.getPrizeAmount());
		dto.setRaiseName(raise.getRaiseName());
		dto.setRaiseIcon(raise.getRaiseIcon());
		dto.setStartDate(DateUtils.dateToString(raise.getStartDate(), DateUtils.yyyy_MM_dd_HH_mm_ss));
		dto.setEndDate(DateUtils.dateToString(raise.getEndDate(), DateUtils.yyyy_MM_dd_HH_mm_ss));
		dto.setOutTimeStr(DateUtils.dateToString(raise.getOutTime(), DateUtils.yyyy_MM_dd_HH_mm_ss));
		dto.setCurrentPeriod(raise.getCurrentPeriod());
		dto.setCurrentNum(raise.getCurrentNum());
		dto.setTotalLedou(raise.getTotalLedou());
		dto.setEachLedou(raise.getEachTotal());
		dto.setTotalNum(raise.getTotalShare());
		dto.setEachMaxNum(raise.getMaxSare());
		dto.setDeliveryType(raise.getDeliveryType());
		
		dto.setCurrentStatus(raise.getCurrentStatus());
		dto.setProductDetails(raise.getProductDetails());
		dto.setProductSpecifications(raise.getProductSpecifications());
		
		List<PrizeRecord> prizeRecordList = null;
		if("3".equals(raise.getCurrentStatus())){	//众筹完成,查询中奖用户
			prizeRecordList = prizeRecordMapper.findByRecordId(raise.getId());
			if(null != prizeRecordList){
				List<LuckyOnesDto> luckyOnes = new ArrayList<LuckyOnesDto>();
				for(PrizeRecord pr : prizeRecordList){
					LuckyOnesDto loDto = new LuckyOnesDto();
					loDto.setUserNick(pr.getUserNick());
					loDto.setLuckyNo(pr.getLuckyNo());
					loDto.setOutTime(DateUtils.dateToString(pr.getOutTime(), DateUtils.yyyyMdHHmm_zh));
					luckyOnes.add(loDto);
				}
				dto.setLuckyOnes(luckyOnes);
			}
		}
		if(prizeRecordList!=null){
			dto.setProgress((prizeRecordList.size()*100/raise.getTotalShare())+"");
			dto.setRemainingNum(raise.getTotalShare()-prizeRecordList.size());
		}else{
			dto.setProgress("0");
			dto.setRemainingNum(raise.getTotalShare());
		}
		return dto;
	}

	@Override
	public RaiseEntityDto raiseGetById(long id) {
		RaiseEntityDto dto =new RaiseEntityDto();
		Raise raise = raiseMapper.selectByPrimaryKey(id);
		if(null != raise){
			dto.setRaise(raise);
			dto.setResultCode("10000");
			dto.setResultType("success");
			dto.setErrorMsg("查询成功");
		}else{
			dto.setResultCode("10001");
			dto.setResultType("fail");
			dto.setErrorMsg("查询失败");
		}
		return dto;
	}
	
	/**
	 * 抽奖
	 */
	@CacheEvict(value = DEMO_CACHE_NAME,key = THING_ALL_KEY)
	@Override
	@Transactional
	public void LuckDraw() {
		List<Raise> raiseList = raiseMapper.selectRaiseForLuckDraw();
		log.info("raiseList size="+raiseList.size());
		Long firstLuckyNo = LuckyDrawUtil.getFirstLuckyNo();
		if(null == firstLuckyNo){
			log.warn("获取不到第一个幸运号，退出抽奖");
			return;
		}
		Date luckDrawDate = new Date();
		for(Raise raise : raiseList){
			List<RaiseOrderDetailDto> rodList = orderItemsService.selectOrderDetailByRaiseId(raise.getId());
			Long prizeAmount = raise.getPrizeAmount();	//奖品数量
			log.info("奖品数量prizeAmount="+prizeAmount);
			int orderCount = rodList.size();	//参与众筹的份额
			log.info("参与众筹的份额orderCount="+orderCount);
			if(orderCount > 0){
				long totalTime = 0L;	//众筹订单的创建时间总和
				Map<String, RaiseOrderDetailDto> dtoMap = new HashMap<String, RaiseOrderDetailDto>();
				if(orderCount > prizeAmount){	//参与众筹份额大于奖品数量
					log.info("参与众筹份额大于奖品数量");
					for(RaiseOrderDetailDto dto : rodList){
						totalTime = LuckyDrawUtil.sumRaiseJoinTime(totalTime, dto.getCreateDate());	//众筹订单创建时间求和
						dtoMap.put(dto.getLuckyNo()+"", dto);
					}
					log.info("totalTime="+totalTime);
					long num1 = totalTime % orderCount;
					long winnerNumberNext = firstLuckyNo + num1;	//得到第一个中奖号码
					log.info("第一个中奖号码："+winnerNumberNext);
					if(prizeAmount > 1){	//奖品数量大于1
						log.info("奖品数量大于1");
						Set<Long> winnerNumberSet = new TreeSet<Long>();
						winnerNumberSet.add(winnerNumberNext);
						long maxWinnerNumber = firstLuckyNo + orderCount - 1;	//此次众筹最大的中奖号码
						long minWinnerNumber = firstLuckyNo;					//此次众筹最小的中奖号码
						boolean isAdd = true;
						if(winnerNumberNext % 2 == 0)
							isAdd = false;
						
						long num2 = raise.getTotalShare() / prizeAmount;
						while(true){
							winnerNumberNext = LuckyDrawUtil.nextWinnerNumber(winnerNumberNext, num2, isAdd);	//获取下一个中奖号码
							if(winnerNumberNext > maxWinnerNumber)
								isAdd = false;
							else if(winnerNumberNext < minWinnerNumber)
								isAdd = true;
							else
								winnerNumberSet.add(winnerNumberNext);
							
							if(winnerNumberSet.size() == prizeAmount)		//中奖号码set的size等于奖品数量时退出循环
								break;
						}
						for(Long winnerNumber : winnerNumberSet){	//保存中奖号码
							RaiseOrderDetailDto dto = dtoMap.get(winnerNumber+"");
							if(null != dto){
								PrizeRecord pr = new PrizeRecord();
								pr.setOrderId(dto.getOrderId());
								pr.setRaiseId(raise.getId());
								pr.setOutTime(luckDrawDate);
								pr.setUserId(dto.getUserId());
								pr.setUserNick(dto.getNickName());
								pr.setIsNotify("0");
								pr.setLuckyNo(winnerNumber);
								prizeRecordMapper.insert(pr);

							}
							log.info("中奖号winnerNumber="+winnerNumber);
						}
						
					}else{		//只有一个奖品，保存一个中奖号码
						log.info("只有一个奖品，中奖号码="+winnerNumberNext);
						RaiseOrderDetailDto dto = dtoMap.get(winnerNumberNext+"");
						if(null != dto){
							PrizeRecord pr = new PrizeRecord();
							pr.setOrderId(dto.getOrderId());
							pr.setRaiseId(raise.getId());
							pr.setOutTime(luckDrawDate);
							pr.setUserId(dto.getUserId());
							pr.setUserNick(dto.getNickName());
							pr.setIsNotify("0");
							pr.setLuckyNo(winnerNumberNext);
							prizeRecordMapper.insert(pr);

						}
					}
					
				}else{	//奖品数量大于或等于众筹份额，所有众筹份额都中奖
					log.info("奖品数量大于或等于众筹份额，所有参与众筹的都中奖");
					for(RaiseOrderDetailDto dto : rodList){
						PrizeRecord pr = new PrizeRecord();
						pr.setOrderId(dto.getOrderId());
						pr.setRaiseId(raise.getId());
						pr.setOutTime(luckDrawDate);
						pr.setUserId(dto.getUserId());
						pr.setUserNick(dto.getNickName());
						pr.setIsNotify("0");
						pr.setLuckyNo(dto.getLuckyNo());
						prizeRecordMapper.insert(pr);
						
					}
				}
			}
			raise.setCurrentStatus("3");	//将众筹状态设置为已经完成
			raiseMapper.updateByPrimaryKey(raise);
		}
		
	}
	
	
	/**
	 * 活动参与开始和参与结束状态设置
	 */
	@CacheEvict(value = DEMO_CACHE_NAME,key = THING_ALL_KEY)
	@Override
	@Transactional
	public void raiseStatusJo() {
		QueryBuilder example=new QueryBuilder();
		List<Raise> raiseList = raiseMapper.selectByExample(example);
		Criteria criteria = example.createCriteria();
		criteria.andFieldEqualTo("currentStatus", "0");
		Criteria or = example.or();
		or.andFieldEqualTo("currentStatus", "1");

		Date nowDate = new Date();
		Long nowTime = nowDate.getTime();
		for(Raise raise : raiseList){
			//日期为空
			if(raise.getStartDate()==null || raise.getEndDate()==null){
				continue;
			}
			Long endTime = raise.getEndDate().getTime();
			Long startTime = raise.getStartDate().getTime();
			//结束时间<开始时间
			if(startTime>endTime){
				continue;
			}
			if("0".equals(raise.getCurrentStatus())){
				if(startTime<=nowTime && nowTime<=endTime){
					//将众筹状态设置为开始
					raise.setCurrentStatus("1");
					raiseMapper.updateByPrimaryKeySelective(raise);
				}
			}
			if("1".equals(raise.getCurrentStatus())){
				if(endTime<=nowTime){
					//将众筹状态设置为 参与结束，等待开奖
					raise.setCurrentStatus("2");
					raiseMapper.updateByPrimaryKeySelective(raise);
				}
			}
		}
	}
	@Override
	public void deleteByPrimaryKey(long id) {
		raiseMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public List<Long> findAllPeriod() {
		return raiseMapper.findAllPeriod();
	}
	
	/**
	 * 分页查询已完成(已开奖)的众筹
	 */
	@Override
	public RaiseQueryDto findRaiseCompletedList(RaiseForm raiseForm) {
		QueryBuilder example=new QueryBuilder();
		//排序设置
		example.setOrderByClause("Create_Time desc");
		Criteria criteria = example.createCriteria();
		//添加查询条件
		Raise raise = raiseForm.getRaise();
		if(null!=raise){
			//query01.众筹名字
			if( null !=raise.getRaiseName() && !"".equals(raise.getRaiseName()) ){
				criteria.andFieldLike("raise_Name","%"+raise.getRaiseName()+"%");
			}
			//query02.期数
			if( null !=raise.getCurrentPeriod() && !"".equals(raise.getCurrentPeriod()) ){
				criteria.andFieldEqualTo("current_Period", raise.getCurrentPeriod());
			}
			//query03.号数
			if( null !=raise.getCurrentNum() && !"".equals(raise.getCurrentNum()) ){
				criteria.andFieldEqualTo("current_Num", raise.getCurrentNum());
			}
			//query04.商品名字
			if( null !=raise.getProductName() && !"".equals(raise.getProductName()) ){
				criteria.andFieldLike("product_Name","%"+raise.getProductName()+"%");
			}
		}		
		criteria.andFieldEqualTo("current_Status", "3");
		
		com.github.pagehelper.Page  pages = PageHelper.startPage(raiseForm.getPageNum(),raiseForm.getPageSize());
		List<Raise> list = raiseMapper.findCompletedList(example);
				
	    RaiseQueryDto dto = new RaiseQueryDto("success","10000","",list);
		dto.setPageNum(pages.getPageNum());
	    dto.setStartIndex(pages.getStartRow());
	    dto.setPageSize(pages.getPageSize());
	    dto.setTotalCount(Long.valueOf(pages.getTotal()).intValue());
	    dto.setTotalPage(Long.valueOf(pages.getPages()));
		dto.setRaiseList(list);
		return dto;
	}

	@Override
	public RaiseEntityDto selectRaiseByOrderId(Long orderId) {
		RaiseEntityDto dto =new RaiseEntityDto();
		Raise raise = raiseMapper.selectRaiseByOrderId(orderId);
		if(null != raise){
			dto.setRaise(raise);
			dto.setResultCode("10000");
			dto.setResultType("success");
			dto.setErrorMsg("查询成功");
		}else{
			dto.setResultCode("10001");
			dto.setResultType("fail");
			dto.setErrorMsg("查询失败");
		}
		return dto;
	}
}