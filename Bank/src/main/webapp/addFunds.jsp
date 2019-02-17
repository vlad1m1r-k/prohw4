<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Пополнить счет</title>
</head>
<body>
<a href="/">На главную.</a><br><br>
Пополнить счет пользователя: <br>
<form action="addFunds" method="post" target="_self">
<table>
    <tr>
        <td style="vertical-align: top">
            <table border="1">
                <tr>
                    <th>Фамилия</th><th>Имя</th>
                </tr>
                <c:choose>
                    <c:when test="${fn:length(users) gt 0}">
                        <c:forEach items="${users}" var="u">
                            <tr onclick="location.href='?userid=${u.id}'" style="cursor: pointer">
                                <td>${u.lastName}</td>
                                <td>${u.firstName}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        Нет.
                    </c:otherwise>
                </c:choose>
            </table>
        </td>
        <td style="vertical-align: top">
            <table border="1">
                <tr>
                    <th>Номер счета</th><th>Валюта</th><th>Остаток</th><th></th>
                </tr>
                <c:forEach items="${accounts}" var="a">
                    <tr>
                        <td>${a.id}</td><td>${a.type}</td><td>${a.amount / 100}</td>
                        <td>
                            <input type="radio" name="accountId" value="${a.id}" required>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td style="vertical-align: top">
            Сумма
            <input type="number" name="amount" min="0" step="0.01" required>
        </td>
        <td style="vertical-align: top">
            <input type="submit" value="Пополнить">
        </td>
    </tr>
</table>
</form>
</body>
</html>
