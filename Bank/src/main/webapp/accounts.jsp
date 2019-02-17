<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Управление счетами.</title>
</head>
<body>
<a href="/">На главную.</a><br><br>
Добавить счет пользователю:<br>
<form action="accounts" method="post" target="_self">
    <table>
        <tr>
            <td style="vertical-align: top">
                <table border="1">
                    <tr>
                        <th>Фамилия</th><th>Имя</th><th></th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(users) gt 0}">
                            <c:forEach items="${users}" var="a">
                                <tr onclick="location.href='?userid=${a.id}'" style="cursor: pointer">
                                    <td>${a.lastName}</td>
                                    <td>${a.firstName}</td>
                                    <td>
                                        <input type="radio" name="userId" value="${a.id}" required
                                        <c:if test="${param.userid eq a.id}">
                                                checked
                                        </c:if>
                                        >
                                    </td>
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
                        <th>Номер счета</th><th>Валюта</th><th>Остаток</th>
                    </tr>
                    <c:forEach items="${accounts}" var="a">
                        <tr>
                            <td>${a.id}</td><td>${a.type}</td><td>${a.amount / 100}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td style="vertical-align: top">Тип счета:
                <select name="currency" required>
                    <c:forEach items="${currencies}" var="a">
                        <option value="${a}">${a}</option>
                    </c:forEach>
                </select>
            </td>
            <td style="vertical-align: top">
                <input type="submit" value="Добавить">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
