<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SE754 - Ass6</title>
    <link href="/css/card-activity.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <c:if test="${error != null}">
        <div id="error-msg">${error}</div>
    </c:if>
    <h1>Card Activity</h1>
    <c:if test="${cards == null}">
        <h2>Players</h2>
        <div class="players">
            <c:forEach items="${players}" var="player">
                <div class="activity-player" name="${player}">${player}</div>
            </c:forEach>
        </div>
        <div class="container-row">
            <form action="add-player" method="post">
                <input type="text" id="player-input" name="new_player">
                <input type="submit" id="add-player" value="Add">
            </form>
        </div>
        <form action="play-game" method="post">
            <input type="submit" value="Play" name="play_game" class="play-btn">
        </form>
    </c:if>
    <c:if test="${cards != null}">
        <div class="card-container" name="card_container">
            <c:forEach items="${cards}" var="card">
                <div class="card-wrapper" name="card_${card[0]}">
                    <div class="card-player">${card[0]}</div>
                    <div class="activity-card">${card[1]}</div>
                    <form action="select-card" method="post">
                        <input type="hidden" value="${card[0]}" name="player">
                        <input type="submit" value="Select" name="select_player" class="play-btn">
                    </form>
                    <c:if test="${selected_player == card[0]}">
                        <span class="card-highlight"></span>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>