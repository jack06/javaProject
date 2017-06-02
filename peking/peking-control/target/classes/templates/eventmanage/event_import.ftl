<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>数据导入</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />

	</head>

	<body>
<fieldset class="layui-elem-field">
  <legend>导入数据</legend>
  <div class="layui-field-box">
  <form class="layui-form" action="/admin/import_data"  method="post" enctype="multipart/form-data">
  <input type="file" name="excelfile" > 
    <div class="layui-form-item">
    <label class="layui-form-label">数据类型</label>
    <div class="layui-input-block">
      <input type="radio" name="type" value="1" title="双选/招聘会数据"  checked>
      <input type="radio" name="type" value="2" title="招聘公告数据">
    </div>
  </div>
  <button class="layui-btn layui-btn-primary" lay-submit lay-filter="import">提交</button>     
  </form>
  </div>
</fieldset>
<script type="text/javascript" src="../plugins/layui/layui.js"></script>
<script>
layui.use(['form','layer'], function(){
  var form = layui.form();
  layer = parent.layer === undefined ? layui.layer : parent.layer;
  form.on('submit(import)', function(data){
          //layer.msg("账号或密码错误"); 
          //return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
});
  
});
</script>

</body>

</html>