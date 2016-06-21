$(document).ready(function(){                                         //初始化jQuery
					$.ajax({                               
					type:"GET",
					url:"/license/hospitalController/showhospital",
				    dataType:"json",
				    success:function(data){
				    	    for (var i = 0;i<(data.length);i++) {
				    	    	var but = "button_"+i;
				    	    	var but2 = "button2_"+i;
				    	    	$("#allhospital").append("<tr><td>" 
				    	    			+ data[i].hospitalNumber+ "</td><td>"
				    	    			+ data[i].hospitalName + "</td><td>"
				    	    			+ data[i].hospitalPhone + "</td><td>" 
				    	    			+ data[i].hospitalAddress + "</td><td>" 
				    	    			+ "<button id="+but+" class='del' type='button' value="+data[i].hospitalNumber+">删除</button>"
				    	    			+ "&nbsp&nbsp<button id="+but2+" class='del' type='button' value="+data[i].hospitalNumber+">修改</button>"
				    	    			+"</td></tr>");
				    	    	
				    	    	$("#"+but).on("click",function(){
						    		//do something;
						    		var test = $(this).val();
						    	    if(confirm("确定删除第: "+test+" 条医院信息？")){
						    	    	$.ajax({
											type:"GET",
											url:"/license/hospitalController/deletehospital?hospitalNumber=" +test,
										 });  
							    	    location.href="/license/hospitalController/tohospital";
						    	     }
					           });
				    	  }
				    },
				    error:function(jqXHR){
					alert("发生错误：" +jqXHR.status);			    	
			}
	  });
});	