<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>SE754 A6</title>
</head>

<body>
<div class="container" style="display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center">
    <h1>Create a meeting</h1>
    <form method="post">
        <p> Meeting ID : <input type="text" name="meetingID" id="meetingID" value="${meetingID}" /></p>
        <p> Title : <input type="text" name="meetingTitle" id="meetingTitle" value="${meetingTitle}"/> </p>
        <p> Description : <input type="text" name="meetingDescription" id="meetingDescription" value="${meetingDescription}" /> </p>
        <p> Start time: <input type="date" id="startDate" name="startDate" value="${startDate}"  >
            <input type="time" id="startTime" name="startTime" value="${startTime}" ></p>
        <p> End time: <input type="date" id="endDate" name="endDate" value="${endDate}" >
            <input type="time" id="endTime" name="endTime" value="${endTime}" ></p>
        Choose a group:
        <select name="chooseGroup" id="chooseGroup" size="1">
            <c:forEach items="${groupNames}" var="group" varStatus="i">
                <option id="${i.index+1}" value="${groupNames.get(i.index)}" name="${groupNames.get(i.index)}">${groupNames.get(i.index)}</option>
            </c:forEach>
        </select>
        <BR/><BR/>
        Recurring:
        <select name="recurring" id="recurring" size="1">
                <option id="recurringFalse" value="recurringFalse" name="recurringFalse">False</option>
                <option id="recurringTrue" value="recurringTrue" name="recurringTrue">True</option>
        </select>
        <BR/><BR/>
        <input type="submit" name = "createBtn"  id="submitCreateMeetingBtn" />
    </form>
    <BR/>
    <label id="message" color="red">${message}</label>

</div>
</body>

</html>