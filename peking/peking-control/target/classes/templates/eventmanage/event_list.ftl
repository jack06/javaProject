<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>事件列表</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
	</head>

	<body>
		<div class="admin-main">

			<blockquote class="layui-elem-quote">
			  事件名称：
			<div class="layui-input-inline">
				<input type="text"  value="${(eventName)!}" id="eventName" class="layui-input">
				</div>
		    状态 ：
		   <div class="layui-input-inline">     
		        <select  name="eventStatus" id="eventStatus" class="layui-input">
		         <option value="">请选择</option>
		          <option value="0">待发布</option>
		          <option value="1">已发布待审核</option>
		          <option value="2">已发布</option>
		          <option value="3">审核未通过</option>
		          <option value="4">已完结</option>
		          
		   </select>
		        </div>
		         展示 ：
		   <div class="layui-input-inline">     
		        <select  name="showStatus" id="showStatus" class="layui-input">
		         <option value="">请选择</option>
		          <option value="0">隐藏</option>
		          <option value="1">展示</option>
		         
		          
		   </select>
		        </div>
		        开始时间：
		        <div class="layui-input-inline">
      <input class="layui-input" placeholder="开始区间" id="LAY_demorange_s">
    </div>
    <div class="layui-input-inline">
      <input class="layui-input" placeholder="结束区间" id="LAY_demorange_e">
    </div>
		        
		        <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 搜索
				</a>
			</blockquote>
				
			<fieldset class="layui-elem-field">
				<legend>列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>事件名称</th>
									<th>发布时间</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>事件状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<#list page.list as event>
						    <tr>
								<td>${event.eventName}</td>
								<td>${event.createDate?string("yyyy-MM-dd HH:mm:ss")}</td>
								<td>${event.startDate?string("yyyy-MM-dd HH:mm:ss")}</td>
								<td>${event.endDate?string("yyyy-MM-dd HH:mm:ss")}</td>
								<td>
                                    <#if event.eventStatus=="0">
                                                                                                  待发布
                                    </#if>
                                    <#if event.eventStatus=="1">
                                                                                                  发布待审批
                                    </#if>
                                    <#if event.eventStatus=="2">
                                                                                                  已发布
                                    </#if>
                                     <#if event.eventStatus=="3">
                                                                                                审核未通过
                                    </#if>
                                     <#if event.eventStatus=="4">
                                                                                                  已完结
                                    </#if>
                                </td>
								<td>
									<a href="/admin/event_to_input?id=${event.id}"  class="layui-btn layui-btn-mini">查看</a>
									<#if event.eventStatus=='1'>
									  <a href="/admin/event_to_input?id=${event.id}"  class="layui-btn layui-btn-mini">审核</a>
									</#if>
								</td>
							</tr>
						</#list>
							
							
						</tbody>
					</table>

				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="page" class="page">
				</div>
			</div>
		</div>
		<script type="text/javascript" src="../plugins/layui/layui.js"></script>
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script>
			layui.config({
				base: '../plugins/layui/modules/'
			});

			layui.use(['icheck', 'laypage','layer','laydate'], function() {
			    var laydate = layui.laydate;
  
				  var start = {
				    min: '1900-01-01 00:00:00'
				    ,max: '2099-06-16 23:59:59'
				    ,istoday: true
				    ,istime: true
				    , format: 'YYYY-MM-DD hh:mm:ss'
				    ,choose: function(datas){
				      end.min = datas; //开始日选好后，重置结束日的最小日期
				      end.start = datas //将结束日的初始值设定为开始日
				    }
				  };
				  
				  var end = {
				    min: '1900-01-01 00:00:00'
				    ,max: '2099-06-16 23:59:59'
				    ,istoday: true
				    ,istime: true
				    , format: 'YYYY-MM-DD hh:mm:ss'
				    ,choose: function(datas){
				      start.max = datas; //结束日选好后，重置开始日的最大日期
				    }
				  };
				  
				  document.getElementById('LAY_demorange_s').onclick = function(){
				    start.elem = this;
				    laydate(start);
				  }
				  document.getElementById('LAY_demorange_e').onclick = function(){
				    end.elem = this
				    laydate(end);
				  }
							
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
					
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});

				//page
				laypage({
					cont: 'page',
					pages: ${page.pages} //总页数
						,
					groups: 10 //连续显示分页数
						,
					first:true,
					last:true,
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first) {
						 $.ajax({
				           url:'/admin/event_getlist',
				           type:'post',
				           data:{"pageNo":obj.curr,"eventName":$('#eventName').val(),"eventStatus":$('#eventStatus').val(),"startTime":$('#LAY_demorange_s').val(),"endTime":$('#LAY_demorange_e').val(),"showStatus":$('#showStatus').val()},
				           success:function(data) { 
				            str = ''; 
                            $.each(data,function(i,val){
                                str += '<tr>'      
								str += '<td>'+val.eventName+'</td>'
								str += '<td>'+val.createDate+'</td>'
								str += '<td>'+val.startDate+'</td>'
								str += '<td>'+val.endDate+'</td>'
								
								str += '<td>'+val.status+'</td>'
								
								str += '<td>'
								str += '<a href="/admin/event_to_input?id='+val.id+'"  class="layui-btn layui-btn-mini">查看</a>'
								if(val.eventStatus=='1'){
								str += '<a href="/admin/event_to_input?id='+val.id+'" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" id='+ val.id+'>审核</a>'
                                 }
                                str += '</tr>'
                            
                            })
                            $('.layui-elem-field').find('tbody').html(str)
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
				 	}
					}
				});

				$('#search').on('click', function() {
						 $.ajax({
				            url:'/admin/event_getlist',
				           type:'post',
				           data:{"eventName":$('#eventName').val(),"eventStatus":$('#eventStatus').val(),"startTime":$('#LAY_demorange_s').val(),"endTime":$('#LAY_demorange_e').val(),"showStatus":$('#showStatus').val()},
				           success:function(data) { 
                               str = ''; 
                            $.each(data,function(i,val){
                                str += '<tr>'      
								str += '<td>'+val.eventName+'</td>'
								str += '<td>'+val.createDate+'</td>'
								str += '<td>'+val.startDate+'</td>'
								str += '<td>'+val.endDate+'</td>'
								
								str += '<td>'+val.status+'</td>'
								
								str += '<td>'
								str += '<a href="/admin/event_to_input?id='+val.id+'"  class="layui-btn layui-btn-mini">查看</a>'
								if(val.eventStatus=='1'){
								str += '<a href="javascript:del('+val.id+');" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" id='+ val.id+'>审核</a>'
                                 }
                                str += '</tr>'
                            })
                            $('.layui-elem-field').find('tbody').html(str)
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
				});
				
				
				$('.site-table tbody tr').on('click', function(event) {
					var $this = $(this);
					var $input = $this.children('td').eq(0).find('input');
					$input.on('ifChecked', function(e) {
						$this.css('background-color', '#EEEEEE');
					});
					$input.on('ifUnchecked', function(e) {
						$this.removeAttr('style');
					});
					$input.iCheck('toggle');
				}).find('input').each(function() {
					var $this = $(this);
					$this.on('ifChecked', function(e) {
						$this.parents('tr').css('background-color', '#EEEEEE');
					});
					$this.on('ifUnchecked', function(e) {
						$this.parents('tr').removeAttr('style');
					});
				});
						
			});
		
			
		</script>
	</body>

</html>