<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Банк</title>
</head>
<body>
<table>
    <tr>
        <td>
            <form action="users" method="get" target="_self">
                <input type="submit" value="Пользователи">
            </form>
        </td>
        <td>
            <form action="accounts" method="get" target="_self">
                <input type="submit" value="Счета">
            </form>
        </td>
        <td>
            <form action="addFunds" method="get" target="_self">
                <input type="submit" value="Пополнить счет">
            </form>
        </td>
        <td>
            <form action="transferFunds" method="get" target="_self">
                <input type="submit" value="Перевод средств">
            </form>
        </td>
        <td>
            <form action="rates" method="get" target="_self">
                <input type="submit" value="Курсы валют">
            </form>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td style="vertical-align: top">
            <table border="1">
                <tr>
                    <th>Фамилия</th><th>Имя</th>
                </tr>
                <c:if test="${fn:length(users) gt 0}">
                    <c:forEach items="${users}" var="u">
                        <tr onclick="location.href='?userid=${u.id}'" style="cursor: pointer">
                            <td>${u.lastName}</td>
                            <td>${u.firstName}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </td>
        <td style="vertical-align: top">
            <c:if test="${fn:length(accounts) gt 0}">
                <table border="1">
                    <tr>
                        <th>Номер счета</th><th>Валюта</th><th>Остаток</th>
                    </tr>
                    <c:forEach items="${accounts}" var="a">
                        <tr onclick="location.href='?userid=${param.userid}&account=${a.id}'" style="cursor: pointer">
                            <td>${a.id}</td><td>${a.type}</td><td>${a.amount / 100}</td>
                        </tr>
                    </c:forEach>
                </table>
                Остаток на счетах: ${balance / 100} грн.
            </c:if>
        </td>
        <td style="vertical-align: top">
            <c:if test="${fn:length(transactions) gt 0}">
                <table border="1">
                    <tr>
                        <th>Дата</th><th>Отправитель</th><th>Списано</th><th>Получатель</th><th>Начислено</th>
                    </tr>
                    <c:forEach items="${transactions}" var="t">
                        <tr>
                            <td>${t.date}</td><td>Счет № ${t.from.id} (${t.from.type})</td><td>${t.subtracted / 100}</td><td>Счет № ${t.to.id} (${t.to.type})</td><td>${t.appended / 100}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>
