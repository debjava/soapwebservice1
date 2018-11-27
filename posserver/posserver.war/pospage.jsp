<html>
  <head>
    <title>Web Application for POS Device</title>
  </head>
<!-- Beginning of Relevent javascript functions -->  
  <script>
  
	function login()
	{
		var timestamp = new Date();
		var name = document.getElementById("nameId").value;
		var pwd = document.getElementById("pwdId").value;
		var ajaxReq = getXmlHttpRequest();
		var serverUrl = "http://localhost:8080/posserver/pos?name="+name+"&pass="+pwd+"&timestamp="+timestamp.getTime();
		var pingStatus = processSynchronousPing( ajaxReq , serverUrl );
		document.getElementById("resultId").value = pingStatus;
	}
	
	function makePayment()
	{
		var timestamp = new Date();
		var serverUrl = document.getElementById("payUrlId").value+"&timestamp="+timestamp.getTime();
		var ajaxReq = getXmlHttpRequest();
		var pingStatus = processSynchronousPing( ajaxReq , serverUrl );
		document.getElementById("resultId").value = pingStatus;
	}
	
	function makeTransaction()
	{
		var timestamp = new Date();
		var serverUrl = document.getElementById("tranUrlId").value+"&timestamp="+timestamp.getTime();
		var ajaxReq = getXmlHttpRequest();
		var pingStatus = processSynchronousPing( ajaxReq , serverUrl );
		document.getElementById("resultId").value = pingStatus;
	}
	
	function processAsynchronousPing(ajaxReq,serverUrl)
	{
		ajaxReq.onreadystatechange=function()
		{
			if (ajaxReq.readyState==4 && ajaxReq.status==200)
			{
				var textData = ajaxReq.responseText;
				//alert(textData);
			}
		}
		ajaxReq.open("POST",serverUrl,true);//For Asynchronous call
		ajaxReq.send();//For Asynchronous call
	}

	function processSynchronousPing(ajaxReq,serverUrl)
	{
		var pingStatus = false;
		ajaxReq.onreadystatechange=function()
		{
			if (ajaxReq.readyState==4 && ajaxReq.status==200)
			{
				var textData = ajaxReq.responseText;
				if( textData != "" )
				{
					pingStatus = textData;
				}
				else
				{
					pingStatus = false;
				}
			}
		}
		ajaxReq.open("GET",serverUrl,false);//For synchronous call
		ajaxReq.send(null);//For synchronous call
		return pingStatus;
	}
	
	function getXmlHttpRequest()
	{
		var xmlhttp;
		if (window.XMLHttpRequest) 
		{
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		}
		else 
		{
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	}

  </script>
<!-- Beginning of Relevent javascript functions -->
  
  <body>
    <h1>Sample page for Visiontek 92 POS</h1>
    <form action="/pos" method="POST">
			<table border="0">
			<tr>
				<td>
					<fieldset>
						<legend>Login &nbsp;</legend>
						<table>
							<tr> 
							  <td>Username</td>
							  <td>
								<input type="text" id="nameId" name="name" value=""/>
							  </td>
							</tr>
							<tr>&nbsp;</tr>
							<tr> 
							  <td>Password</td>
							  <td>
								<input type="password" id="pwdId" name="pass" value=""/>
							  </td>
							</tr>
							<tr>
								<td/>
								<td><input type="button" name="Login" value="Login" onclick="login();"/></td>
							</tr>
						</table>
					</fieldset>
				</td>
				
				<td>
					<fieldset>
						<legend>Result &nbsp;</legend>
						<table>
							<tr>
								<td>
									<textarea id="resultId" rows="7" cols="50">
									</textarea>
								</td>
							</tr>
						</table>
					</fieldset>
				</td>
				
			</tr>
			
			<tr>
				<td>
					<fieldset>
						<legend>Payment &nbsp;</legend>
						<table>
							<tr>
								<td>
									<label for="url">URL : </label>
								</td>
								<td>
									<input type="text" id="payUrlId" name="payUrlText" 
									value="http://localhost:8080/posserver/card?msgid=100&pin=1234&cardno=4267&pname=deba"
									size="120">
								</td>
							</tr>
							<tr>
								<td/>
								<td>
									<input type="button" value="Pay" onclick="makePayment();">
								</td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
			
			<tr>
				<td>
					<fieldset>
						<legend>Transaction &nbsp;</legend>
						<table>
							<tr>
								<td>
									<label for="url">URL : </label>
								</td>
								<td>
									<input type="text" id="tranUrlId" name="tranUrlText" 
									value="http://localhost:8080/posserver/transaction?msgid=200&pin=1234&pname=deba&cardno=4267&cvv=111&amt=1000"
									size="120">
								</td>
							</tr>
							<tr>
								<td/>
								<td>
									<input type="button" value="Make Transaction" onclick="makeTransaction();">
								</td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	
    </form>
  </body>
</html>
