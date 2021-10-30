<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Card Activity Settings</h1>
            <h2>Values</h2>
            <div id="card-values">
                <c:forEach items="${values}" var="value">
                    <form action="remove-value" method="post" name="value_${value}">
                        <input type="hidden" name="card_value" value="${value}">
                        <input type="submit" name="submit" value="${value}" class="value">
                    </form>
                </c:forEach>
            </div>
            <div class="container-row">
                <form action="add-value" method="post">
                    <input type="text" id="value-input" name="new_value">
                    <input type="submit" id="value-submit" value="Add">
                </form>
            </div>
            <h2>Suits</h2>
            <div id="card-suits">
                <c:forEach items="${suits}" var="suit">
                    <form action="remove-suit" method="post" name="suit_${suit}">
                        <input type="hidden" name="card_suit" value="${suit}">
                        <input type="submit" name="submit" value="${suit}" class="suit">
                    </form>
                </c:forEach>
            </div>
            <div class="container-row">
                <form action="add-suit" method="post">
                    <input type="text" id="suit-input" name="new_suit">
                    <input type="submit" id="suit-submit" value="Add">
                </form>
            </div>
        </div>
    </body>
</html>