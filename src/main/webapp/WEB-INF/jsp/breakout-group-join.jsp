<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Breakout Groups</title>
</head>

<body>

<div class="container">
    <c:if test="${error != null}">
        <div id="error-msg">${error}</div>
    </c:if>
    <div class="row">
        <c:if test="${group_list != null}">
            <div id="breakout-groups-container">
                <c:forEach items="${group_list}" var="group">
                    <div name="${group[0]}" class="breakout-group">
                        <h2>${group[0]}</h2>
                        <h3>Users:</h3>
                        <p name="users">${group[1]}</p>
                        <form action="/breakout-group/join" method="POST">
                            <input type="hidden" value="${group[2]}" name="group_number">
                            <input type="submit" value="Join" name="join_group">
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${users != null}">
            <div id="user-streams">
                <h2>Users:</h2>
                <c:forEach items="${users}" var="user">
                    </br>
                    <div class="user" name="${user}">${user}</div>
                    </br>
                </c:forEach>
            </div>
            <form action="/breakout-group/disband" method="POST">
                <input type="hidden" value="${group_number}" name="group_id">
                <input type="submit" value="Disband Group" name="disband_group">
            </form>
        </c:if>
    </div>
</div>

</body>

</html>