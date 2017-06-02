
<fieldset class="layui-elem-field">
  <legend>评论配置</legend>
  <div class="layui-field-box" id="comment_configs">
  
<div class="layui-form-item">
    <label class="layui-form-label">评论开始时间</label>
    <div class="layui-input-block">
      <input type="hidden" id="comment_moduleId" />
      <input type="text" style="width:350px;" id="comment_startTime" readonly  autocomplete="off"  class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">评论截止时间</label>
    <div class="layui-input-block">
      <input type="text"style="width:350px;" id="comment_endTime" readonly   autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">当前状态</label>
    <div class="layui-input-block">
       <div class="layui-form-mid layui-word-aux" id="comment_status"> </div>
    </div> 
    <div class="layui-form-item">
    <label class="layui-form-label">允许角色</label>
          <div class="layui-input-block">
          <div class="layui-form-mid layui-word-aux" id="comment_roles"> </div>
    </div> 
  </div>

    </div>
</fieldset>
 <fieldset class="layui-elem-field">
  <legend>评论列表</legend>
  <div class="layui-field-box">
      <div class="layui-field-box"> 
      <table class="site-table table-hover">
						<thead>
							<tr>
								<th>头像</th>
								<th>昵称</th>
								<th>评论时间</th>
							</tr>
						</thead>
						<tbody id="comment_table">
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
               $('#comment_moduleId').val(data.config.id);
		       $('#comment_startTime').val(data.config.startTime)
		       $('#comment_endTime').val(data.config.endTime)
		       $('#comment_status').html(data.config.status)
		        roles = '';
		       if(data.roles=='0'){
		          roles = '不限角色';
		        }else{
		         $.each(data.roles,function(i,val){
		           roles += val.roleName +','
		         })
		       }
		        $('#comment_roles').html(roles);
               if(data.data.pages>1){
                   pages = data.data.pages;
                     laypage({
					    cont: 'comment_page',
					    pages: pages,
					    groups: 10, //连续显示分页数
					    jump: function(obj, first){
					      if(!first){
					          getNext_comment(obj.curr);
					      }
					    }
					  });
                   }
                  str = ''; 
                  $.each(data.data.commentList,function(i,val){
                      str += '<tr>'
                      str += '<td>'
                      str += '<img src="'+val.avatar+'" />' 
                      str += '</td>'
                      str += '<td>'+val.nick+'</td>'
                      str += '<td>'+val.createAt+'</td>'
                      str +='</tr>'
                      
                      str +='<tr>'
                      
                      str += '<td rowspan="'+(val.content.length+1)+'"></td>'
                      str += '</tr>'
                      
                       $.each(val.content,function(i,vals){
                          str +='<tr><td colspan="2">'
                          if(vals.type=='1'){
                            str += vals.value
                           }else{
                           str += '<img src="'+vals.value+'" />' 
                          }
                          str += '</td></tr>'
                       
                       })
                      
                   })
                   if(''==str){
                     str = "暂无记录"
                   }
                   $('#comment_table').html(str)
                } else{
		           $('#comment_configs').html('暂无配置')
		       
		        }         
          },    
           error : function() {    
             layer.msg("异常！");    
           } 
		 })
    })
     
})

function getNext_comment(pageNo){
$.ajax({
		 url:'/admin/comments_list',
		 type:'post',
		 data:{"moduleId":$('#comment_moduleId').val(),"pageNo":pageNo},
		 success:function(data) { 
               if(data.errCode=="0000"){
                    str = ''; 
                    $.each(data.data.commentList,function(i,val){
                      str += '<tr>'
                      str += '<td>'
                      str += '<img src="'+val.avatar+'" />' 
                      str += '</td>'
                      str += '<td>'+val.nick+'</td>'
                      str += '<td>'+val.createAt+'</td>'
                      str +='</tr>'
                      
                      str +='<tr>'
                      
                      str += '<td rowspan="'+(val.content.length+1)+'"></td>'
                      str += '</tr>'
                      
                       $.each(val.content,function(i,vals){
                          str +='<tr><td colspan="2">'
                          if(vals.type=='1'){
                            str += vals.value
                           }else{
                           str += '<img src="'+vals.value+'" />' 
                          }
                          str += '</td></tr>'
                       
                       })
                      
                   })
                    if(''==str){
                       str = "暂无记录"
                   }
                  $('#comment_table').html(str)
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