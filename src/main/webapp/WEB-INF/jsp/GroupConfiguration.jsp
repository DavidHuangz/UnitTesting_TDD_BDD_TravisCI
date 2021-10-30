<html>

<head>
<title>SE754 A6</title>
</head>

<label id ="message" color="red">${message}</label>
<h1>Group configuration</h1>

<body>
    Here are a list of group members:
    ${AllUserNames}
    <form action=add-user method="post">
    <p>Add a user : <input type="text" name="userName" id="name"/>
    <input type="submit" name = "Add" value = "Add" id="addbtn" /></p>
    </form>
    <form action=remove-user method="post">
    <p>Remove a user : <input type="text" name="userName" id="name"/>
    <input type="submit" name = "remove" value = "remove" id="removebtn" /></p>
    </form>
</body>

</html>