$(document).ready(function(){                                         //初始化jQuery
					$.ajax({                               
					type:"GET",
					url:"/license/hospitalController/showhospital",
				    dataType:"json",
				    success:function(data){
				    	var pageTiao = 14;
				    	function initUI(pageNo, pageSize) {
				    		//alert("here2");
				    		var rows = $("#allhospital").find("tr").length;
				    if (rows > 1) { // 判断table中是否有数据，若有则先进行清除
					for (var j = rows - 1; j > 0; j--) {
						//从表的最下端往最上端删除，防止删到表头！！！！！注意这种表删除的写法
						$("#allhospital").find("tr").eq(j).remove();
						console.info(j);
					              }
				     }   
				    	    for (var i = (pageNo-1)*pageSize; i < pageNo*pageSize; i++) {
				    	    	var but = "button_"+i;
				    	    	var but2 = "button2_"+i;
				    	    	$("#allhospital").append("<tr><td>" 
				    	    			+ data[i].hospitalNumber+ "</td><td>"
				    	    			+ data[i].hospitalName + "</td><td>"
				    	    			+ data[i].hospitalPhone + "</td><td>" 
				    	    			+ data[i].hospitalAddress + "</td><td>" 
				    	    			+ "<button id="+but+" type='button' class='del' value="+data[i].hospitalNumber+">删除</button>"
				    	    			+ "&nbsp&nbsp<button id="+but2+" type='button' value="+data[i].hospitalNumber+">修改</button>"
				    	    			// + "<a href='' id="+but+" class='del' value="+data[i].hospitalNumber+">删除</a>"
				    	    			// + "<a href='' id="+but2+" class='mod' value="+data[i].hospitalNumber+">修改</a>"
				    	    			+"</td></tr>");
				    	    	  //       $("#"+but).attr("value");

				    	    	$("#"+but).on("click",function(){
						    		//do something;
						    		var test = $(this).val();
						    	    if(confirm("确定删除第: "+test+" 条医院信息？")){
						    	    	$.ajax({
											type:"GET",
											url:"/license/hospitalController/deletehospital?hospitalNumber=" +test,
											dataType:"json",
										    success:function(data){
										    	if(data.success){
										    			alert(data.msg);
										    			location.href="/license/hospitalController/toshowallhospital";
										    	}else{
										    			alert(data.msg);
										    				}
										   			 },
										    error:function(jqXHR){
											alert("发生错误：" +jqXHR.status);			    	
												}
										 });  
						    	     }
					           });
				    	  }

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
			}
	  });
});	