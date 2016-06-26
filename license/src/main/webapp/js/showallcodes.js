$(document).ready(function(){                                    //初始化jQuery
					$.ajax({                               
					type:"GET",
					url:"/license/licenseController/showallcodes",
				    dataType:"json",

				    success:function(data){
				    	//每页显示多少条记录
				    	var pageTiao = 10;
				    	function initUI(pageNo, pageSize) {
				    		//alert("here2");
				    		var rows = $(".allcodes").find("tr").length;
				    if (rows > 1) { // 判断table中是否有数据，若有则先进行清除
					for (var j = rows - 1; j > 0; j--) {
						//从表的最下端往最上端删除，防止删到表头！！！！！注意这种表删除的写法
						$(".allcodes").find("tr").eq(j).remove();
						console.info(j);
					              }
				     }   

				    	for(var i = (pageNo-1)*pageSize; i < pageNo*pageSize; i++)  {

				    		var row = "rownum_"+i;
				    		var but = "button_"+i;
					    			//alert(row);
					    			//alert(i);
   										$(".allcodes").append("<tr id="+row+"><td>"
				    	    			+ data[i].serialNumberId+ "</td><td>"
				    	    			+ data[i].sourceNumber + "</td><td>"
				    	    			+ data[i].createDay + "</td><td>" 
				    	    			+ data[i].expiredDate + "</td><td>" 
				    	    			+ data[i].encryptedNumber +"</td><td>" 
				    	    			+ data[i].validDays + "</td><td>" 
				    	    			+ data[i].hospitalName+"</td><td>"
				    	    			+ "<button id="+but+" class='del' type='button' value="+data[i].serialNumberId+">删除</button>"+"</td></tr>");
						    	    		if(data[i].expiredFlag == 1){
						                          			//alert("#rownum_"+i);
										    	    	$("#"+row).css("background-color","#CCCCCC");
										    	    	//$("#"+row).css("color","red");
								    	    					}
                                                				
												    	  $("#"+but).on("click",function(){
										    		         var test = $(this).val();
										    	           if(confirm("确定删除第: "+test+" 条记录？")){
										    	    	         $.ajax({
															             type:"GET",
															             url:"/license/licenseController/deletecode?codeid=" +test,
															             dataType:"json",
															             success:function(text){
															             	if(text.success){
																			    		alert(text.msg);
																			    		location.href="/license/licenseController/toshowallcodes";
																			    	}else{
																			    		alert(text.msg);
																			    	}
															             },
															             error:function(jqXHR){
					                                                          alert("发生错误：" +jqXHR.status);
					                                                     },
														       });  
										    	    	}
										 		 });
												    	  //拼接过程动态读取表的行数
                                           console.info($(".allcodes").find("tr").length);
		                          			}
		                          						//拼接完成后读取整个表的行数
		                             console.info("第二次"+$(".allcodes").find("tr").length);  


                           		pagination({                           //定义四个参数
											cur: pageNo,              //当前页
											total: 6,                 //总的页面数
											len: 4,                   //显示多少个可点的数字
											targetId: 'pagination',   //分页条在页面中的位置？
											callback: function() {
												var me = this;
												var oPages = $(".page-index");
												for(var i = 0; i < oPages.length; i++) {
													oPages[i].onclick=function() {
														initUI(this.getAttribute('data-index'), pageTiao);
													}
												}
												var goPage = $("#go-search")
												goPage.onclick = function() {
													var index = $("#yeshu").val();
													if(!index || (+index > me.total) || (+index < 1)) {
														return;
													}
													initUI(index, pageTiao);
												}
											}
										});
                           			}
                           	initUI(1,pageTiao);
				    },
				    error:function(jqXHR){
					alert("发生错误：" +jqXHR.status);			    	
		      },
	  });
});