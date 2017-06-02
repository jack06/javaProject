<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>菜单</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">

		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
	</head>

	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>人员角色<#if userrole??>修改<#else>添加</#if></legend>
			</fieldset>

			<form class="layui-form" action="" id="form">
			   <#if userrole??><input type="hidden" name="id" value="${userrole.id}"></#if>
				<div class="layui-form-item">
					<label class="layui-form-label">角色名称</label>
					<div class="layui-input-block">
						<input type="text" name="roleName" <#if userrole??>value=${userrole.roleName} readonly="true"</#if>  lay-verify="required" autocomplete="off" placeholder="请输入角色名称" class="layui-input" style="width:190px;">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">是否需要审批事件</label>
					<div class="layui-input-inline">
						<select name="isNeedVerify" lay-filter="aihao">
							<option value=""></option>
							<option value="0" selected="">是</option>
							<option value="1">否</option>
						</select>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="refer">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="../plugins/layui/layui.js"></script>
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script>
			layui.use(['form','jquery'], function() {
				var form = layui.form(),
					layer = layui.layer;
					
				//监听提交
				form.on('submit(refer)', function(data) {
					   $.ajax({
				           url:'/staffmanage/staffrole_input',
				           type:'post',
				           data:$('#form').serialize(),
				           success:function(data) { 
				             if("0000"==data){
				                layer.msg('添加成功',{time:2000})
				               setTimeout(function(){window.location="/staffmanage/staffrole_list"},1000);
				              }else if("0002"==data){
				                 layer.msg('修改成功',{time:2000})
				                 setTimeout(function(){window.location="/staffmanage/staffrole_list"},1000);     
				              }else if("0001"==data){
				                  layer.msg("角色名已被使用！", {time:2000 })
				               }else if("0003"==data){
				                  layer.msg("事件类型已被使用", {time:2000 })
				               }
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
					return false;
				});
				
				form.on('checkbox(_check_)', function(data){
				debugger
                     var dom = data.elem //得到checkbox原始DOM对象
                     var isOpen = data.elem.checked; //开关是否开启，true或者false
                     if(isOpen){
                        $('#p_'+data.elem.id).attr('checked','checked');
                     
                      }
                     
                     
                     console.log(data.value); //开关value值，也可以通过data.elem.value得到
                     
                     
                     console.log(data.othis); //得到美化后的DOM对象
              });  
			});
		</script>
	</body>

</html>