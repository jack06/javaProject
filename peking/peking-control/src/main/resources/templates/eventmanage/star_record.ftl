     <div class="layui-field-box"> 
      <table class="site-table table-hover">
						<thead>
							<tr>
								<th>头像</th>
								<th>昵称</th>
								<th>点赞时间</th>
								
							</tr>
						</thead>
						 
						<tbody>
						</tbody>
	</table>					
	 </div>
    <div class="admin-table-page">
	<div id="star_page" class="page">
</div>
</div>

<script>
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer;
  
               
 $(document).ready(function(){
     $.ajax({
		 url:'/admin/event_star',
		 type:'post',
		 data:{"eventId":$('#event_id').val()},
		 success:function(data) { 
               if(data.errCode=="0000"){
               if(data.pages>1){
                   pages = data.pages;
                     laypage({
					    cont: 'star_page',
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
                      str += '<td>'+val.nick+'</td>'
                      str += '<td>'+val.createDate+'</td>'
                      str +='</tr>'
                   })
                    if(''==str){
                     str = "暂无记录"
                   }
                   $('#star').find('tbody').html(str)
                }else if(data.errCode=="0001"){
                   layer.msg(data.resultMsg);
                }else{
                   layer.msg("当前事件不存在！");
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
		 url:'/admin/event_star',
		 type:'post',
		 data:{"eventId":$('#event_id').val(),"pageNo":pageNo},
		 success:function(data) { 
               if(data.errCode=="0000"){
                  str = ''; 
                  $.each(data.list,function(i,val){
                      str += '<tr>'
                      str += '<td>'
                      str += '<img src="'+val.headerImg+'" />' 
                      str += '</td>'
                      str += '<td>'+val.nick+'</td>'
                      str += '<td>'+val.createDate+'</td>'
                      str +='</tr>'
                   })
                    if(''==str){
                     str = "暂无记录"
                   }
                    $('#star').find('tbody').html(str)
                }else if(data.errCode=="0001"){
                   layer.msg(data.resultMsg);
                }else{
                   layer.msg("当前事件不存在！");
                 }            
          },    
           error : function() {    
             layer.msg("异常！");    
           } 

    
  
})

}

</script>