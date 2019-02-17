<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Перевод средств</title>
</head>
<body>
<a href="/">На главную.</a><br><br>
<form action="transferFunds" method="post" target="_self">
    Счет отправителя: <br>
    <table>
        <tr>
            <td>
                <select>
                    <c:if test="${fn:length(users) gt 0}">
                        <c:forEach items="${users}" var="u">
                            <option onclick="location.href='?fromUserId=${u.id}&toUserId=${param.toUserId}'"
                                    <c:if test="${param.fromUserId eq u.id}">
                                        selected
                                    </c:if>
                            >${u.lastName} ${u.firstName}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </td>
            <td>
                <c:if test="${fromAccounts ne null}">
                    <select name="accountFrom" required>
                        <c:forEach items="${fromAccounts}" var="a">
                            <option value="${a.id}">
                                ${a.type} (${a.amount / 100})
                            </option>
                        </c:forEach>
                    </select>
                </c:if>
            </td>
        </tr>
    </table>
    Счет получателя: <br>
    <table>
        <tr>
            <td>
                <select>
                    <c:if test="${fn:length(users) gt 0}">
                        <c:forEach items="${users}" var="u">
                            <option onclick="location.href='?fromUserId=${param.fromUserId}&toUserId=${u.id}'"
                                    <c:if test="${param.toUserId eq u.id}">
                                        selected
                                    </c:if>
                            >${u.lastName} ${u.firstName}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </td>
            <td>
                <c:if test="${toAccounts ne null}">
                    <select name="accountTo" required>
                        <c:forEach items="${toAccounts}" var="a">
                            <option value="${a.id}">
                                    ${a.type} (${a.amount / 100})
                            </option>
                        </c:forEach>
                    </select>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                Сумма:
            </td>
            <td>
                <input type="number" min="0" step="0.01" name="amount" required>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Перевести">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
