<form class="layui-form" action="">
<div class="layui-form-item">
    <label class="layui-form-label">报名开始时间</label>
    <div class="layui-input-block">
      <input type="hidden" id="moduleId" />
      <input type="text" style="width:350px;" id="startTime" readonly  autocomplete="off"  class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">报名截止时间</label>
    <div class="layui-input-block">
      <input type="text"style="width:350px;" id="endTime" readonly   autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">当前状态</label>
    <div class="layui-input-block">
             <div class="layui-form-mid layui-word-aux" id="status"> </div>
    </div> 
    <div class="layui-form-item">
    <label class="layui-form-label">允许报名角色</label>
             <div class="layui-input-block">
             <div class="layui-form-mid layui-word-aux" id="roles"> </div>
    </div> 
  </div>
  </form>
 <fieldset class="layui-elem-field">
  <legend>报名记录</legend>
  <div class="layui-field-box">
      <div class="layui-field-box"> 
      <table class="site-table table-hover">
						<thead>
							<tr>
								<th>头像</th>
								<th>昵称</th>
								<th>评论内容</th>
								<th>评论时间</th>
								
							</tr>
						</thead>
						 
						<tbody>
						</tbody>
	</table>					
	 </div>
    <div class="admin-table-page">
	<div id="comment_page" class="page">
</div>
</div>
  </div>
</fieldset>
<script>  
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage,layer = layui.layer;
 $(document).ready(function(){
     $.ajax({
		 url:'/admin/comment_config',
		 type:'post',
		 data:{"eventId":$('#event_id').val()},
		 success:function(data) { 
               if(data.errCode=="0000"){
               $('#moduleId').val(data.config.id);
		       $('#startTime').val(data.config.startTime)
		       $('#endTime').val(data.config.endTime)
		       $('#status').html(data.config.status)
		        roles = '';
		       if(data.roles=='0'){
		          roles = '不限角色';
		        }else{
		         $.each(data.roles,function(i,val){
		           roles += val.roleName +','
		         })
		       }
		        $('#roles').html(roles);
               if(data.pages>1){
                   pages = data.pages;
                     laypage({
					    cont: 'comment_page',
					    pages: pages,
					    groups: 10, //连续显示分页数
					    jump: function(obj, first){
					      if(!first){
					          getNext(obj.curr);
					      }
					    }
					  });
                   }
                  str = ''; 
                  $.each(data.list,function(i,val){
                      str += '<tr>'
                      str += '<td>'
                      str += '<img src="'+val.headerImg+'" />' 
                      str += '</td>'
                      str += '<td>'
                      str +=   val.name
                      str += '</td>'
                      str += '<td>'+val.nick+'</td>'
                      str += '<td>'+val.createDate+'</td>'
                      str +='</tr>'
                   })
                   if(''==str){
                     str = "暂无记录"
                   }
                   $('.layui-field-box').find('tbody').html(str)
                }          
          },    
           error : function() {    
             layer.msg("异常！");    
           } 
		 })
    })
     
})

function getNext(pageNo){
$.ajax({
		 url:'/admin/sign_up_record',
		 type:'post',
		 data:{"eventId":$('#moduleId').val(),"pageNo":pageNo},
		 success:function(data) { 
               if(data.errCode=="0000"){
                  str = ''; 
                  $.each(data.list,function(i,val){
                      str += '<tr>'
                      str += '<td>'
                      str += '<img src="'+val.headerImg+'" />' 
                      str += '</td>'
                       str += '<td>'
                      str +=   val.name
                      str += '</td>'
                      str += '<td>'+val.nick+'</td>'
                      str += '<td>'+val.createDate+'</td>'
                      str +='</tr>'
                   })
                    if(''==str){
                       str = "暂无记录"
                   }
                  $('.layui-field-box').find('tbody').html(str)
                }else if(data.errCode=="0001"){
                }else{
                   
                 }            
          },    
           error : function() {    
             layer.msg("异常！");    
           } 

    
  
})

}
     
     
     
  </script>