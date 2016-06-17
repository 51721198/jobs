
		$(document).ready(function(){                                         //初始化jQuery
			$("#createcode").click(function(){                                //生成原始序列号
				if($("#hosnumber").val()=='' || $("#duedate").val()==''){
					alert("请填写完整信息");
					return false;
				}
				$.ajax({
				type:"GET",
				url:"/license/licenseController/createcode?hosnumber=" +$("#hosnumber").val()+"&duedate=" +$("#duedate").val(),
			    dataType:"json",
			    success:function(data){
			    	if(data.success){
			    		$("#code1").val(data.msg);
			    	}else{
			    		$("#code1").val("data.msg");
			    	}
			    },
			    error:function(jqXHR){
				alert("发生错误：" +jqXHR.status);			    	
			}
		});
	});
//}); 函数总关闭括号，容易遗漏！

			$("#encryptcode").click(function(){                              //加密序列号
				if($("#code1").val()==''){
					alert("请先生成序列号！");
					return false;
				}
				$.ajax({
				type:"POST",
				url:"/license/licenseController/encryptcode",
			    dataType:"json",
			    data:{
			    	encryptcode:$("#code1").val(),
			    },
			    success:function(data){
			    	if(data.success){
			    		$("#code2").val(data.msg);
			    	}else{
			    		$("#code2").val(data.msg);
			    	}
			    },
			    error:function(jqXHR){
				alert("发生错误：" +jqXHR.status);			    	
			}
		});
	});
	
			//$("#hosnumber").click(function(){                                //生成原始序列号
				$.ajax({
				type:"GET",
				url:"/license/hospitalController/showhospital",
			    dataType:"json",
			    success:function(data){
			    		
			    		for(var i=0;i<data.length;i++){
			    		$("#select_id").append("<option value='"+data[i].hospitalNumber+"'>"+data[i].hospitalName+"</option>");
			    		//$("option").val(data[i].hospitalName);
			    		}
			    	},
			    error:function(jqXHR){
				alert("发生错误：" +jqXHR.status);			    	
			}
		});
			
				$("#select_id").change(function(){
					$("#hosnumber").val($("#select_id").val());
				});
				
				$("#go").click(function(){
					if($("#code2").val()==''){
						alert("请先获取加密序列号！");
						return false;									
					}					
				});
});	        //总函数关闭括号
		
