<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html lang="en">
<head>
    <title>SE754 - Ass6</title>
</head>
<body>
<div class="container" style="display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center">

    <BR/><BR/>
    <h1 id="HomePageMessage">Home Page</h1>
    <a id="createMeetingBtn" href="/createMeeting">Create a meeting</a>

    <form method="post">
        <BR/><BR/>
        Meetings:
        <select name="sortSelection" id="sortSelection" size="1" onchange="this.form.submit()">
            <option value="">Sort by</option>
            <option value="All">All</option>
            <option value="Past">Past</option>
            <option value="Upcoming">Upcoming</option>
        </select>
        <c:choose>
            <c:when test="${param.sortSelection=='All'}">
                <BR/><BR/>
                <label id="message">${message}</label> Meetings:
                <BR/>
                <c:forEach items="${allMeetings}" var="meeting" varStatus="i">
                    ${i.index+1}:
                    <BR/>
                    ID: <label id="meetingID">${allMeetings.get(i.index).getMid()}</label>
                    <BR/>
                    Title: <label id="meetingTitle">${allMeetings.get(i.index).getMeetingTitle()} </label>
                    <BR/>
                    Description: <label id="meetingDescription">${allMeetings.get(i.index).getMeetingDescription()} </label>
                    <BR/>
                    Time: <label id="meetingTime">${allMeetings.get(i.index).getStartTime()}
                        ${allMeetings.get(i.index).getEndTime()} </label>
                    <BR/>
                    Users: <label id="Usernames">${allMeetings.get(i.index).getAllUsernames()} </label>
                    <BR/><BR/>
                </c:forEach>
            </c:when>

            <c:when test="${param.sortSelection=='Past'}">
                <BR/><BR/>
                <label id="message">${message}</label> Meetings:
                <BR/>
                <c:forEach items="${pastMeetings}" var="meeting" varStatus="i">
                    ${i.index+1}:
                    <BR/>
                    ID: ${pastMeetings.get(i.index).getMid()}
                    <BR/>
                    Title: ${pastMeetings.get(i.index).getMeetingTitle()}
                    <BR/>
                    Description: ${pastMeetings.get(i.index).getMeetingDescription()}
                    <BR/>
                    Time: ${pastMeetings.get(i.index).getStartTime()}
                    ${pastMeetings.get(i.index).getEndTime()}
                    <BR/>
                    Users: ${pastMeetings.get(i.index).getAllUsernames()}
                    <BR/><BR/>
                </c:forEach>
            </c:when>

            <c:when test="${param.sortSelection=='Upcoming'}">
                <BR/><BR/>
                <label id="message">${message}</label> Meetings:
                <BR/>
                <c:forEach items="${upcomingMeetings}" var="meeting" varStatus="i">
                    ${i.index+1}:<BR/>
                    ID: ${upcomingMeetings.get(i.index).getMid()}
                    <BR/>
                    Title: ${upcomingMeetings.get(i.index).getMeetingTitle()}
                    <BR/>
                    Description: ${upcomingMeetings.get(i.index).getMeetingDescription()}
                    <BR/>
                    Time: ${upcomingMeetings.get(i.index).getStartTime()}
                    ${upcomingMeetings.get(i.index).getEndTime()}
                    <BR/>
                    Users: ${upcomingMeetings.get(i.index).getAllUsernames()}
                    <BR/><BR/>
                </c:forEach>
            </c:when>

        </c:choose>
    </form>
<button type="button" name="logoutBtn" id="logoutBtn" onclick="location = ''">logout</button>

</div>
</body>
</html>