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
        <h1>Breakout Groups</h1>
        <h2>Make groups by:</h2>
        <div class="row">
            <form method="POST">
                <input type="hidden" value="users" name="format">
                <input type="submit" value="Number of Users" name="make_num_users">
            </form>
            <form method="POST">
                <input type="hidden" value="groups" name="format">
                <input type="submit" value="Number of Groups" name="make_num_groups">
            </form>
        </div>
        <div class="row">
            <c:if test="${format != null}">
                <form action="/breakout-group/create" method="POST" id="parameters">
                    <input type="hidden" value="${format}" name="format">
                    <label for="users-in-meeting">In Meeting:</label>
                    <input type="text" value="${meeting_users}" name="users_in_meeting" id="users-in-meeting" readonly>
                    <c:if test="${format == 'users'}">
                        <div name="user_params">
                            <label for="number_users">Number of users per group:</label>
                            <input type="number" name="number_users" id="number_users" min="1" max="80">
                        </div>
                    </c:if>
                    <c:if test="${format == 'groups'}">
                        <div name="group_params">
                            <label for="number_groups">Number of groups:</label>
                            <input type="number" name="number_groups" id="number_groups" min="1" max="80">
                        </div>
                    </c:if>
                    <label for="random_users">Assign users to random groups:</label>
                    <input type="radio" id="random_users" name="allocation" value="random">
                    </br>
                    <label for="user_choice">Let users choose room:</label>
                    <input type="radio" id="user_choice" name="allocation" value="choice">
                    </br>

                    <input type="submit" name="make_groups" value="Make Groups">
                </form>
            </c:if>
        </div>
    </div>

</body>

</html>