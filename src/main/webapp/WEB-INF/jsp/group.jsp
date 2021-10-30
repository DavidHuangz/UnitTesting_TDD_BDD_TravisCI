<html>

<head>
<title>SE754 A6</title>
</head>

<body>
    <label id ="message" color="red">${message}</label>
	<h1>Group</h1>
	<h2>Create a group</h2>
	<form method="post">
		<p> Enter a groupName : <input type="text" name="groupName" id="groupName" > </p>
		<p> Add user with userName : <input type="text" name="userName" id="name"/> <p>
		<input type="submit" name = "Create" value = "Create" id="createbtn" />
	</form>
    <a href="/group-configuration">Click here</a> to manage your users.
</body>

</html>