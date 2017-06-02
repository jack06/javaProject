<input type="hidden" id="vote_module">
<fieldset class="layui-elem-field">
  <legend>当前时间投票结果</legend>
  <div class="layui-field-box" id="vote_result">
    暂无配置
    <br/>
  </div>
</fieldset>
 <fieldset class="layui-elem-field">
  <legend>投票记录</legend>
  <div class="layui-field-box">
      <div class="layui-field-box"> 
      <table class="site-table table-hover">
						<thead>
							<tr>
								<th>头像</th>
								<th>昵称</th>
								<th>选项</th>
								<th>投票时间</th>
							</tr>
						</thead>
						<tbody id="vote_table">
						</tbody>
	</table>					
	 </div>
    <div class="admin-table-page">
	<div id="vote_page" class="page">
</div>
</div>
  </div>
</fieldset>
<script>  
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage,layer = layui.layer;
 $(document).ready(function(){
     $.ajax({
		 url:'/admin/vote_result',
		 type:'post',
		 data:{"eventId":$('#event_id').val()},
		 success:function(data) { 
		 if(data.errCode=='0000'){
		 $('#vote_module').val(data.moduleId);
		        str = '';
                $.each(data.result,function(i,val){
                  
                  str += val.optiond
                  str += '&nbsp;&nbsp;&nbsp;&nbsp;'
                  str += '投票人数：'+val.count
                  str += '&nbsp;&nbsp;&nbsp;&nbsp;'
                  str += '占比：'+val.percentage
                  str += '<br/>'
                
                })
               $('#vote_result').empty();
               $('#vote_result').append(str);
               //投票记录
                if(data.pages>1){
                   pages = data.pages;
                     laypage({
					    cont: 'vote_page',
					    pages: pages,
					    groups: 10, //连续显示分页数
					    jump: function(obj, first){
					      if(!first){
					          getNext_vote(obj.curr);
					      }
					    }
					  });
                   }
               strs = '';
                $.each(data.records,function(i,val){
                  strs += '<tr>'
                  strs += '<td>'
                  strs +='<img src="'+val.headerImg+'" />'
                   strs += '</td>'
                   strs += '<td>'
                  strs +=val.nick
                   strs += '</td>'
                   strs += '<td>'
                  strs +=val.option
                   strs += '</td>'
                  strs += '<td>'
                  strs +=val.createDate
                  strs += '</td>'
                  
                  strs +='</tr>'
                 
                })
                $('#vote_table').html(strs);
            }          
          },    
           error : function() {    
             layer.msg("异常！");    
           } 
		 })
    })
     
})

function getNext_vote(pageNo){
$.ajax({
		 url:'/admin/vote_record_list',
		 type:'post',
		 data:{"moduleId":$('#vote_module').val(),"pageNo":pageNo},
		 success:function(data) { 
               if(data.errCode=="0000"){
                   strs = '';
                $.each(data.records,function(i,val){
                  strs += '<tr>'
                  strs += '<td>'
                  strs +='<img src="'+val.headerImg+'" />'
                   strs += '</td>'
                   strs += '<td>'
                  strs +=val.nick
                   strs += '</td>'
                   strs += '<td>'
                  strs +=val.option
                   strs += '</td>'
                  strs += '<td>'
                  strs +=val.createDate
                  strs += '</td>'
                  
                  strs +='</tr>'
                 
                })
                $('#vote_table').html(strs);
                }           
          },    
           error : function() {    
             layer.msg("异常！");    
           } 

    
  
})

}
     
  </script>
