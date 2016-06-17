function encryptCode(){
		var xhr = new XMLHttpRequest();
		var postdata = "encryptcode="+document.getElementById("hosnumber").value;
		 xhr.onreadystatechange=function(){
		 	if (xhr.readyState==4 && xhr.status==200) {
		 		// statement
		 		//alert(xhr.responseText);
		 		document.getElementById("code1").innerHTML=xhr.responseText;
		 	}
		 }
		 
		xhr.open("POST","/license/licenseController/createcode",true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

		//xhr.setRequestHeader("Content-Type","application/json");
		//var jsonsource = '{"username" : document.getElementById("name").value}';
		//xhr.send(obj);
		xhr.send(postdata);
	}