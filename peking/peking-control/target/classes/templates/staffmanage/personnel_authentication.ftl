<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<title>人员审核列表</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
	</head>

	<body>
	
			<blockquote class="layui-elem-quote">
			         
			         姓名：<div class="layui-input-inline">
				       <input type="text" name="realName" value="${(realName)!}" id="realName" class="layui-input">
				     </div>
				
			       工号(学号)：<div class="layui-input-inline">
				           <input type="text" name="wordNo" value="${(wordNo)!}" id="wordNo" class="layui-input">
				        </div>
				        
                                 手机号：<div class="layui-input-inline">
				          <input type="text" name="phone" value="${(phone)!}" id="phone" class="layui-input">
				       </div>
		               
		             认证状态 ：<div class="layui-input-inline">     
		              <select  name="isCertification" id="isCertification" class="layui-input">
		                <option value="">请选择</option>
		                <option value="0">认证中</option>
		                <option value="1">认证成功</option>
		                <option value="2">认证失败</option> 
		               </select>
		             </div>
		         
		       <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 查找
				</a>
			</blockquote>
				
			<fieldset class="layui-elem-field">
				<legend>人员审核列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>姓名</th>
								<th>工号(学号)</th>
								<th>所在院系</th>	
								<!--<th>籍贯</th>-->
								<th>性别</th>
								<th>专业(部门)</th>
								<th>级别(职位)</th>
								<th>手机号</th>
								<th>认证状态</th>
								<!--<th>交友宣言</th>							
								<th>兴趣爱好</th>	-->						
								<th>角色名称</th>
								<th>是否需要审批事件</th>														
								<th>昵称</th>
								<th>微信头像</th>	
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<#list list as t>
						    <tr>
								<td>${t.realName!""}</td>
								<td>${t.wordNo!""}</td>
								<td>${t.departments!""}</td>
								<!--<td>${t.nativePlace!""}</td>-->
								<#if t.gender??>
								<td>
						            <#if t.gender=="0">男</#if>
						            <#if t.gender=="1">女</#if>
								</td>
								<#else>
								<td>
						            
								</td>
								</#if>
								<td>${t.professional!""}</td>
								<td>${t.grade!""}</td>
								<td>${t.phone!""}</td>
						        <td>
						            <#if t.isCertification=="0">认证中</#if>
						            <#if t.isCertification=="1">认证成功</#if>
						            <#if t.isCertification=="2">认证失败</#if>
								</td>	
								<!--<td>${t.declaration!""}</td>
								<td>${t.hobby!""}</td>		-->						
								<td>${t.roleName!""}</td>
								<#if t.isNeedVerify??>
								<td>
						            <#if t.isNeedVerify=="0">是</#if>
						            <#if t.isNeedVerify=="1">否</#if>
								</td>
								<#else>
								<td>
								</td>
								</#if>
								<td>${t.nick!""}</td>
								<td><img src="${t.wxHeadImg!""}" height="50" width="50"/></td>																							
								<td>
									<a href="javascript:succeed('${t.id}');" data-id="1" data-opt="succeed" class="layui-btn layui-btn-mini" >审核认证成功</a>
									<a href="javascript:fail('${t.id}');" data-id="1" data-opt="fail" class="layui-btn layui-btn-danger layui-btn-mini" >审核认证失败</a>
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

			layui.use(['icheck', 'laypage','layer'], function() {
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});

				//page
				laypage({
					cont: 'page',
					pages: ${userext.pages} //总页数
						,
					groups: 5 //连续显示分页数
						,
					first:true,
					last:true,
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first) {
						 $.ajax({
				           url:'/staffmanage/authentication_getlist',
				           type:'post',
				           data:{"pageNo":obj.curr,"account":$('#account').val()},
				           success:function(data) { 
				           debugger
				            str = ''; 
                            $.each(data.list,function(i,val){                     
                                
                                str += '<tr>'
                                
								if (val.realName==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.realName+'</td>'
                                 }								
								if (val.wordNo==undefined) {
						        str += '<td>' 
                                 }else{
                               	str += '<td>'+val.wordNo+'</td>'
                                 }								
						        if (val.departments==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.departments+'</td>'  
                                 }
								//str += '<td>'+val.nativePlace+'</td>'
								if (val.gender==undefined) {
						        str += '<td>' 
                                 }else{
                               	str += '<td>'+val.gender+'</td>'
                                 }
								
								if (val.professional==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.professional+'</td>'
                                 }
								if (val.grade==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.grade+'</td>'
                                 }
								if (val.phone==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.phone+'</td>'
                                 }
                                 
                                if (val.isCertification==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.isCertification+'</td>'
                                 } 
								//str += '<td>'+val.declaration+'</td>'
							   //str += '<td>'+val.hobby+'</td>'
                                if (val.roleName==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.roleName+'</td>'
                                 }							   								
                                
                                if (val.isNeedVerify==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.isNeedVerify+'</td>'
                                 }									
                                if (val.nick==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.nick+'</td>'
                                 }								
								
                                if (val.wxHeadImg==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td><img src="'+val.wxHeadImg+'" height="50" width="50" /></td>'
                                 }								
								str += '<td>'
								str += '<a href="javascript:succeed(\''+val.id+'\');" data-id="1" data-opt="succeed" class="layui-btn layui-btn-mini" id='+ val.id+'>审核认证成功</a>'
								str += '<a href="javascript:fail(\''+val.id+'\');" data-id="1" data-opt="fail" class="layui-btn layui-btn-danger layui-btn-mini" id='+ val.id+'>审核认证失败</a>'
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
				           url:'/staffmanage/authentication_getlist',
				           type:'post',
				           data:{"realName":$('#realName').val(),"wordNo":$('#wordNo').val(),"phone":$('#phone').val(),"isCertification":$('#isCertification').val()},
				           success:function(data) { 
				           debugger

				            str = ''; 
                            $.each(data.list,function(i,val){
 
                                str += '<tr>'
                                
								if (val.realName==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.realName+'</td>'
                                 }								
								if (val.wordNo==undefined) {
						        str += '<td>' 
                                 }else{
                               	str += '<td>'+val.wordNo+'</td>'
                                 }								
						        if (val.departments==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.departments+'</td>'  
                                 }
								//str += '<td>'+val.nativePlace+'</td>'
								if (val.gender==undefined) {
						        str += '<td>' 
                                 }else{
                               	str += '<td>'+val.gender+'</td>'
                                 }
								
								if (val.professional==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.professional+'</td>'
                                 }
								if (val.grade==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.grade+'</td>'
                                 }
								if (val.phone==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.phone+'</td>'
                                 }
                                 
                                if (val.isCertification==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.isCertification+'</td>'
                                 } 
								//str += '<td>'+val.declaration+'</td>'
							   //str += '<td>'+val.hobby+'</td>'
                                if (val.roleName==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.roleName+'</td>'
                                 }							   								
                                
                                if (val.isNeedVerify==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.isNeedVerify+'</td>'
                                 }									
                                if (val.nick==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td>'+val.nick+'</td>'
                                 }								
								
                                if (val.wxHeadImg==undefined) {
						        str += '<td>' 
                                 }else{
                                str += '<td><img src="'+val.wxHeadImg+'" height="50" width="50" /></td>'
                                 }								
								str += '<td>'
								str += '<a href="javascript:succeed(\''+val.id+'\');" data-id="1" data-opt="succeed" class="layui-btn layui-btn-mini" id='+ val.id+'>审核认证成功</a>'
								str += '<a href="javascript:fail(\''+val.id+'\');" data-id="1" data-opt="fail" class="layui-btn layui-btn-danger layui-btn-mini" id='+ val.id+'>审核认证失败</a>'
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
			function succeed(id){
			         $.ajax({
				           url:'/authentication_auditing',
				           type:'post',
				           data:{"id":id},
				           success:function(data) { 
				              layer.msg("审核通过",{time:2000});
				              setTimeout(function(){window.location.href="/staffmanage/personnel_authentication";},1000);
				              
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				      })
			
			}
			function fail(id){
			         $.ajax({
				           url:'/authentication_fail',
				           type:'post',
				           data:{"id":id},
				           success:function(data) { 
				              layer.msg("审核不通过",{time:2000});
				              setTimeout(function(){window.location.href="/staffmanage/personnel_authentication";},1000);
				              
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				      })
			
			}			
		</script>
	</body>

</html>