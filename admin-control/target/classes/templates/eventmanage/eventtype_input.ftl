<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>事件类型</title>
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
				<legend>事件类型<#if eventType??>修改<#else>添加</#if></legend>
			</fieldset>

			<form class="layui-form" action="" id="form">
			   <#if eventType??><input type="hidden" name="id" value="${eventType.id}"></#if>
				<div class="layui-form-item">
					<label class="layui-form-label">事件类型</label>
					<div class="layui-input-block">
						<input type="text" name="typeName" <#if eventType??>value=${eventType.typeName}  readonly="true"</#if>  lay-verify="required" autocomplete="off" placeholder="请输入事件类型" class="layui-input" style="width:190px;">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">类型标识</label>
					<div class="layui-input-block">
						<input type="text" name="typeMark" <#if eventType??>value=${eventType.typeMark} readonly="true"</#if>  lay-verify="required" autocomplete="off" placeholder="请输入类型标识" class="layui-input" style="width:190px;">
					</div>
				</div>
					
			<!--	<div class="layui-form-item">
					<label class="layui-form-label">上级类别</label>
					<div class="layui-input-inline">
						<select name="parentId">
							<option value="0" selected="">一级</option>
							<option value="1">二级</option>
						</select>
					</div>
				</div>
			-->	
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-block">
						<input type="number" name="sortNum" <#if eventType??>value=${eventType.sortNum}</#if>  lay-verify="required" autocomplete="off" placeholder="请输入排序" class="layui-input" style="width:190px;">
					</div>
			   </div>
			   
			  <!-- 	<div class="layui-form-item">
					<label class="layui-form-label">类型图标</label>
					<div class="layui-input-block">
						<input type="text" name="typeIcon" <#if eventType??>value=${eventType.typeIcon}</#if>  lay-verify="required" autocomplete="off" placeholder="请输入类型图标" class="layui-input" style="width:190px;">
					</div>
				</div>	
			 -->							
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
				           url:'../eventmanage/eventtype_input',
				           type:'post',
				           data:$('#form').serialize(),
				           success:function(data) { 
				             if("0000"==data){
				                layer.msg('添加成功',{time:2000})
				               setTimeout(function(){window.location="../eventmanage/eventtype_list"},1000);
				              }else if("0002"==data){
				                 layer.msg('修改成功',{time:2000})
				                 setTimeout(function(){window.location="../eventmanage/eventtype_list"},1000);     
				              }else if("0001"==data){
				                  layer.msg("事件类型已被使用", {time:2000 })
				               }else if("0003"==data){
				                  layer.msg("类型标识已被使用", {time:2000 })
				               }
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
					return false;
				});
				
				form.on('checkbox(_check_)', function(data){
                     var dom = data.elem //得到checkbox原始DOM对象
                     var isOpen = data.elem.checked; //开关是否开启，true或者false
                     if(isOpen){
                        $('#p_'+data.elem.id).attr('checked','checked');
                     
                      }
                     
              });  
			});
		</script>
	</body>

</html>