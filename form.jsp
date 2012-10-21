<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>upload</title>
<script src="jquery.js" type="text/javascript"></script>
<script src="jquery.filestyle.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
    	$("input.file_1").filestyle({ 
   		image: "select.jpg",
    	imageheight : 20,
    	imagewidth : 80,
    	width : 100
    	});     
    });    		
</script>

<style type="text/css">

.file_1 {
    background: #fff;
    color: #888;
}
</style>

<script type="text/javascript">
    var file= document.getElementById('formfile');
	file.onchange= function() 
	{
    	if (this.value!=='')
        this.form.submit();
    };
 </script>
</head> 

 <body>
<p>${message}</p>

<div class="entry">
  <form action="upload" enctype="multipart/form-data" method="POST">
  name <input type="text" name="name"/>
  <br>
  <input type="file" name="select" id="formfile" class="file_1"/>
  </form>
</div>

</body>
</html>