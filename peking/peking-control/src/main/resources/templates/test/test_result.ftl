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
	<br/>
	<#list list as question>
  <fieldset class="layui-elem-field">
  <legend>${question.questionNo}&nbsp;${question.questionContent}</legend>
   <div class="layui-field-box">
  

    <#if question.questionType!='3'>
      <#list question.optionList as option>
          <#list question.answers as answer>
            <#if answer.optionsId==option.id>
             <i class="layui-icon" style="color:#ff1e5b;">&#xe618;</i> 
            <#else>
          </#if>
          </#list>
           ${option_index+1}.${option.optionsContent}
            <br/> 
       </#list>
    
    
    </#if>
    <#if question.questionType=='3'>
        <#list question.answers as answer>
           ${answer.optionsId}
      </#list>
    </#if>  
  </div>
 </fieldset>
</#list>
	
</body>
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script>
//注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
 var element = layui.element();
  var layer = layui.layer;
  
  //监听折叠
  element.on('collapse(test)', function(data){
    layer.msg('展开状态：'+ data.show);
  });
});
</script>	
</html>