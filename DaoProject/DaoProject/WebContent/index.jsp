<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script src="http://cdnjs.gtimg.com/cdnjs/libs/jquery/2.1.1/jquery.min.js"></script>
	<body>
		<script>
			$(document).ready(function(){
			  $.ajax({
		             type: "POST",
	               	 contentType:"application/json",
		             url: "http://192.168.2.1:8080/MyServerDemo/StudentInq",
		             success: function(data){
		            	 var res=$.parseJSON(data);
		             }
		         });
			});
		</script>
		
	</body>
</html>