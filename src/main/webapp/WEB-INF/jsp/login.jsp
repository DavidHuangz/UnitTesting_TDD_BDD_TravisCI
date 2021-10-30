<html>
<head>
<title>Login</title>
</head>

<body>
	<h1>Online learning tool</h1>
	<form method="post">
		<h2>Login</h2>
		Name : <input type="text" name="name" id="name" />
		Password : <input type="password" name="password" id="password"/>
		<input type="submit" id="submitbtn" />
		<font id ="error" color="red">${errorMessage}</font>

	    <h2>Forgot Password</h2>
		    New Password : <input type="password" name="newPassword" id="newPassword"/>
            <input type="submit" id="resetPassBtn" value = "Reset"/>
       <font id ="newPass" color="blue">${newPass}</font>
	</form>

</body>

</html>