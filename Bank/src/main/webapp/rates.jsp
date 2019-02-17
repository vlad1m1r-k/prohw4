<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Курсы валют</title>
</head>
<body>
<form action="rates" method="post" target="_self">
<table>
    <c:forEach items="${rates}" var="a">
        <tr>
            <td>${a.currency}</td>
            <td>
                <c:choose>
                    <c:when test="${a.rate eq null}">
                        <input type="number" min="0" step="0.01" name="${a.currency}" required>
                    </c:when>
                    <c:otherwise>
                        <input type="number" min="0" step="0.01" name="${a.currency}" value="${a.rate / 100}">
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
    <table>
        <tr>
            <td>
                <input type="submit" value="Установить">
            </td>
            <td>
                <button type="button" onclick="location.href='/'">Отмена</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
