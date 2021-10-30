<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
<title>SE754 A6</title>
</head>

<body>

    <label id ="message">${message}</label>
	<h1>My Meeting</h1>
	<h2>Active meetings</h2>
	<div class = "currentMeeting">
	    <c:forEach items="${meetings}" var="meeting">
            <div class="meetingId">ID: ${meeting.key}</div>
            <div class="meetingTitle">Title: ${meeting.value}</div>
        </c:forEach>
    </div>
    <h2>Join a Meeting</h2>
    <form action="/joinMeeting" method="post">
    Enter a meeting ID : <input type="text" name="meetingId" id="meetingID"/>
    <input type="submit" name = "Join" value = "Join" id="Joinbtn" />
    </form>

</body>

</html>