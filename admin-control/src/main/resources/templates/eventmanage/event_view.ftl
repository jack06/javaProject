<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>事件列表</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
		<style>
		 img {
		    display: inline-block;
		    border: none;
		    width: 26px;
		    height: 25px;
        }
		</style>
	</head>

	<body>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
      <legend>事件详情</legend>
    </fieldset>
     <div class="layui-tab" lay-filter="event">	
		<ul class="layui-tab-title">
	    <li class="layui-this">事件基本信息</li>
	    <li>报名模块配置</li>
	    <li>评论模块配置</li>
	  <!--  <li>评价模块配置</li>-->
	    <li>投票模块配置</li>
	    <li>问卷模块配置</li>
	    <li>点赞记录</li>
	    <li>关注记录</li>
	  </ul>

    
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
      <div class="layui-form-item">
      <fieldset class="layui-elem-field">
  <legend>事件详情</legend>
  <div class="layui-field-box">
   <div class="layui-form-item">
        <label class="layui-form-label">事件名称</label>
        <div class="layui-input-block">
          <input type="hidden" value="${event.id}" id="event_id"/>
          <input type="text" style="width:350px;" readonly value="${event.eventName}" autocomplete="off" class="layui-input">
        </div>
     </div>   
    <div class="layui-form-item">
    <label class="layui-form-label">地址</label>
     <div class="layui-input-block">
      <input type="text" style="width:350px;" readonly value="${event.address}" autocomplete="off" class="layui-input">
    </div>
    </div>
     <div class="layui-form-item">
    <label class="layui-form-label">发布时间</label>
     <div class="layui-input-block">
      <input type="text" style="width:350px;" readonly value="${event.createDate?string("yyyy-MM-dd HH:mm:ss")}" autocomplete="off" class="layui-input">
    </div>
    </div>
     <div class="layui-form-item">
    <label class="layui-form-label">开始时间</label>
     <div class="layui-input-block">
      <input type="text" style="width:350px;" readonly value="${event.startDate?string("yyyy-MM-dd HH:mm:ss")}" autocomplete="off" class="layui-input">
    </div>
    </div>
     <div class="layui-form-item">
    <label class="layui-form-label">结束时间</label>
     <div class="layui-input-block">
      <input type="text" style="width:350px;" readonly value="${event.endDate?string("yyyy-MM-dd HH:mm:ss")}" autocomplete="off" class="layui-input">
    </div>
    </div>
 <div class="layui-form-item">
    <label class="layui-form-label">状态</label>
     <div class="layui-input-block">
      <div class="layui-form-mid layui-word-aux">
      <#if event.eventStatus=="0">
                                                                                                  待发布
                                    </#if>
                                    <#if event.eventStatus=="1">
                                                                                                  发布待审批  
                                                                                                  
                               <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn"  lay-filter="formDemo" onclick="javascript:audit('${event.id}','2')" >审核通过</button>
      <button class="layui-btn layui-btn-danger" lay-filter="formDemo" onclick="javascript:audit('${event.id}','3')">不予通过</button>
    </div>
         </div>                                                                   
                                    </#if>
                                    <#if event.eventStatus=="2">
                                                                                                  已发布
                                    </#if>
                                     <#if event.eventStatus=="3">
                                        未通过审核
                                    </#if>
                                     <#if event.eventStatus=="4">
                                                                                                  已完结
                                    </#if>
                                    </div>
        </div>
        
    <div class="layui-form-item">
    <label class="layui-form-label">查看角色</label>
     <div class="layui-input-block">
      <div class="layui-form-mid layui-word-aux">
          ${role}                
       </div>
        </div>
        
        
 </div>
</fieldset>
    </div>
</div>

    <div class="layui-tab-item" id="signup"></div>
    <div class="layui-tab-item" id="comments"></div>
   <!-- <div class="layui-tab-item">暂无内容</div>-->
    <div class="layui-tab-item" id="vote">暂无内容</div>
    <div class="layui-tab-item" id="test">暂无内容</div>
    <div class="layui-tab-item" id="star"></div>
    <div class="layui-tab-item" id="follow"></div>
  </div>
 </div> 
  
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script>
var layer;
layui.use(['element','layer'], function(){
  var $ = layui.jquery,element = layui.element(); 
  //Tab的切换功能，切换事件监听等，需要依赖element模块
  layer = parent.layer === undefined ? layui.layer : parent.layer;
  
  element.on('tab(event)', function(elem){
     index = elem.index;
  });
  
});
 function audit(id,type){
  $.ajax({
		 url:'../admin/event_audit',
		 type:'post',
		 data:{"id":id,"type":type},
		 success:function(data) { 
               if(data=="0000"){
                   layer.msg("审核成功！");  
                   window.location='../admin/event_to_input?id='+id;
                }else if(data=="0001"){
                   layer.msg("事件状态错误！");
                }else{
                   layer.msg("当前事件不存在！");
                 }            
          },    
           error : function() {    
             layer.msg("异常！");    
                } 
		 })
   }
$(document).ready(function(){
   $('#star').load('../admin/star_record');
   $('#follow').load('../admin/event_follow');
   $('#signup').load('../admin/signup');
   $('#comments').load('../admin/toComment');
   $('#vote').load('../admin/to_vote');
   $('#test').load('../admin/to_test')
   
   })
   
</script>
 </body>
 </html>	