<fieldset class="layui-elem-field">
  <legend>问卷配置</legend>
  <div class="layui-field-box">
    <form class="layui-form" action="">
     <input type="hidden" id="test_moduleId" />
    <div class="layui-form-item">
    <label class="layui-form-label">问卷名称</label>
    <div class="layui-input-block">
      <input type="text" style="width:350px;" id="test_title" readonly  autocomplete="off"  class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">问卷描述</label>
    <div class="layui-input-block">
     
      <input type="text" style="width:350px;" id="test_description" readonly  autocomplete="off"  class="layui-input">
    </div>
  </div>

  <div class="layui-form-item">
    <label class="layui-form-label">当前状态</label>
    <div class="layui-input-block">
             <div class="layui-form-mid layui-word-aux" id="test_status"> </div>
    </div> 
    <div class="layui-form-item">
    <label class="layui-form-label">允许角色</label>
             <div class="layui-input-block">
             <div class="layui-form-mid layui-word-aux" id="test_roles"> </div>
    </div> 
  </div>
  </form>
  </div>
</fieldset>
<fieldset class="layui-elem-field">
  <legend>问卷记录</legend>
  <div class="layui-field-box">
      <div class="layui-field-box"> 
      <table class="site-table table-hover">
						<thead>
							<tr>
								<th>头像</th>
								<th>昵称</th>
								<th>参与时间</th>
								<th>操作</th>
							</tr>
						</thead>
						 
						<tbody id="test_table">
						</tbody>
	</table>					
	 </div>
    <div class="admin-table-page">
	<div id="test_page" class="page">
</div>
</div>
  </div>
</fieldset>

<script>
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage,layer = layui.layer;
 $(document).ready(function(){
     $.ajax({
		 url:'/admin/test_config',
		 type:'post',
		 data:{"eventId":$('#event_id').val()},
		 success:function(data) { 
               if(data.errCode=="0000"){
               $('#test_moduleId').val(data.config.id)
               $('#test_title').val(data.config.title)
                $('#test_description').val(data.config.description)
		       $('#test_status').html(data.config.status)
		          roles = '';
		       if(data.config.roles=='0'){
		          roles = '不限角色';
		        }else{
		         $.each(data.config.roles,function(i,val){
		           roles += val.roleName +','
		         })
		       }
		        $('#test_roles').html(roles);
                if(data.pages>1){
                   pages = data.pages;
                     laypage({
					    cont: 'test_page',
					    pages: pages,
					    groups: 10, //连续显示分页数
					    jump: function(obj, first){
					      if(!first){
					          getNext_test(obj.curr);
					      }
					    }
					  });
                   }
                   
                  str = ''; 
                  $.each(data.records,function(i,val){
                      str += '<tr>'
                      str += '<td>'
                      str += '<img src="'+val.wxHeaderImg+'" />' 
                      str += '</td>'
                      str += '<td>'+val.nick+'</td>'
                      str += '<td>'+val.createTime+'</td>'
                      str += '<td><button class="layui-btn layui-btn-mini" onclick="javascript:open_test(\''+val.recordId+'\')">查看结果</button></td>'
                      str +='</tr>'
                   })
                   if(''==str){
                     str = "暂无记录"
                   }
                   $('#test_table').html(str)
                   
                }else if(data.errCode=='0002'){
                   $('#test').html('暂无配置问卷模块')
                }          
          },    
           error : function() {    
             layer.msg("异常！");    
           } 
		 })
    })
   
})  
  function open_test(id){
        layer.open({
         title: ['问卷结果', 'font-size:18px;'],
         type: 2, 
         area: ['800px', '600px'],
         content: '/admin/test_result?moduleId='+ $('#test_moduleId').val()+'&userId='+id
        }); 
    
     }
function getNext_test(pageNo){
$.ajax({
		 url:'/admin/test_records',
		 type:'post',
		 data:{"moduleId":$('#test_moduleId').val(),"pageNo":pageNo},
		 success:function(data) { 
               if(data.errCode=="0000"){
                  str = ''; 
                  $.each(data.records,function(i,val){
                      str += '<tr>'
                      str += '<td>'
                      str += '<img src="'+val.wxHeaderImg+'" />' 
                      str += '</td>'
                      str += '<td>'+val.nick+'</td>'
                      str += '<td>'+val.createTime+'</td>'
                      str += '<td><button class="layui-btn layui-btn-mini" onclick="javascript:open_test("'+val.recordId+'")">查看结果</button></td>'
                      str +='</tr>'
                   })
                   if(''==str){
                     str = "暂无记录"
                   }
                   $('#test_table').html(str)
                 }            
          }
})

}
     
     
     
</script>