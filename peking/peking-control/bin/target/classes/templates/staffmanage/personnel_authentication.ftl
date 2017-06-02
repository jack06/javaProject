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
				角色名称：<input type="text" name="menuName" value="${(menuName)!}" id="menuName">
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
								<th>Id</th>
								<th>角色名称</th>								
								<th>上级菜单</th>	
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<#list list as m>
						    <tr>
								<td>${m.menuName}</td>
								<td>${m.menuUrl}</td>
								<td>
								
								<#if m.parentId=="0">一级菜单</#if>
								<#if m.parentId!="0">
								 ${m.parent.menuName}
								</#if>
								
								</td>
								<td>
									<a href="/staffmanage/staffrole_to_input?id=${m.id}"  class="layui-btn layui-btn-mini">编辑</a>
									<a href="javascript:del('${m.id}');" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" >删除</a>
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
					pages: ${menu.pages} //总页数
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
				           url:'/staffmanage/staffrole_getlist',
				           type:'post',
				           data:{"pageNo":obj.curr,"account":$('#account').val()},
				           success:function(data) { 
				           debugger
				            str = ''; 
                            $.each(data.list,function(i,val){
                            
                                str += '<tr>'
								str += '<td>'+val.menuName+'</td>'
								str += '<td>'
								str += val.menuUrl
								str += '</td>'
								
								
								str += '<td>'+val.parentName+'</td>'
								
								str += '<td>'
								str += '<a href="/staffmanage/staffrole_to_input?id='+val.id+'"  class="layui-btn layui-btn-mini">编辑</a>'
								str += '<a href="javascript:del('+val.id+');" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" id='+ val.id+'>删除</a>'
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
				debugger
						 $.ajax({
				           url:'/staffmanage/staffrole_getlist',
				           type:'post',
				           data:{"menuName":$('#menuName').val()},
				           success:function(data) { 
				           debugger
				            str = ''; 
                            $.each(data.list,function(i,val){
                                str += '<tr>'
                                  
								str += '<td>'+val.menuName+'</td>'
								str += '<td>'
								str += val.menuUrl
								str += '</td>'
								
								str += '<td>'+val.parentName+'</td>'
								
								str += '<td>'
								str += '<a href="/staffmanage/staffrole_to_input?id='+val.id+'"  class="layui-btn layui-btn-mini">编辑</a>'
								str += '<a href="javascript:del('+val.id+');" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" id='+ val.id+'>删除</a>'
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
			function del(id){
			         $.ajax({
				           url:'/staffmanage/staffrole_del',
				           type:'post',
				           data:{"id":id},
				           success:function(data) { 
				              layer.msg("删除成功",{time:2000});
				              setTimeout(function(){window.location.href="/staffmanage/staffrole_list";},1000);
				              
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				      })
			
			}
			
		</script>
	</body>

</html>