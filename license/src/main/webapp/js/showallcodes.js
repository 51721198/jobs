$(document).ready(function(){                                         //初始化jQuery
					$.ajax({                               
					type:"GET",
					url:"/license/licenseController/showallcodes",
				    dataType:"json",
				    success:function(data){
				    	var i = 0;
				    	for (i;i<(data.length);i++) {
				    		var row = "rownum_"+i;
				    		var but = "button_"+i;
					    	//alert(row);
				    	    	$("#allcodes").append("<tr id="+row+"><td>"
				    	    			+ data[i].serialNumberId+ "</td><td>"
				    	    			+ data[i].sourceNumber + "</td><td>"
				    	    			+ data[i].createDay + "</td><td>" 
				    	    			+ data[i].expiredDate + "</td><td>" 
				    	    			+ data[i].encryptedNumber +"</td><td>" 
				    	    			+ data[i].expiredFlag +"</td><td>" 
				    	    			+ data[i].validDays + "</td><td>" 
				    	    			+ data[i].hospitalNumber+"</td><td>"
				    	    			+ "<button id="+but+" class='del' type='button' value="+data[i].serialNumberId+">删除</button>"+"</td></tr>");
				    	    	
				    	    	if(data[i].expiredFlag == 1){
				    	    	$("#rownum_"+i).css("background-color","red");
				    	    	$("#rownum_"+i).css("color","white");
				    	    	}
				    	    	
				    	    	$("#"+but).on("click",function(){
						    		//do something;
						    		var test = $(this).val();
						    	    if(confirm("确定删除第: "+test+" 条记录？")){
						    	    	$.ajax({
											type:"GET",
											url:"/license/licenseController/deletecode?codeid=" +test,
										});  
							    	    location.href="/license/licenseController/showall";
						    	    }
						  });
			    	 }
				},
				    error:function(jqXHR){
					alert("发生错误：" +jqXHR.status);			    	
		   },
	});
});	