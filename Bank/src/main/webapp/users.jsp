<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Пользователи</title>
</head>
<body>
<a href="/">На главную.</a><br><br>
Добавить пользователя: <br>
<form action="users" method="post" target="_self">
    <table>
        <tr>
            <td>Имя </td>
            <td>
                <input type="text" name="firstName" required>
            </td>
        </tr>
        <tr>
            <td>Фамилия</td>
            <td>
                <input type="text" name="lastName" required>
            </td>
        </tr>
    </table>
    <input type="submit" value="Добавить">
</form>
Наши пользователи: <br>
<c:choose>
    <c:when test="${fn:length(users) gt 0}">
        <table border="1">
            <tr>
                <th>Фамилия</th><th>Имя</th>
            </tr>
            <c:forEach items="${users}" var="a">
                <tr>
                    <td>${a.lastName}</td>
                    <td>${a.firstName}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        Нет.
    </c:otherwise>
</c:choose>
</body>
</html>
