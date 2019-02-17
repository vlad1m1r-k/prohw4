<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Меню в ресторане</title>
</head>
<body>
Новое блюдо: <br>
<form action="add" method="post" target="_self">
    <table>
        <tr>
            <td>Название: </td>
            <td>
                <input type="text" min="0" step="any" name="name" required>
            </td>
            <td>Цена: </td>
            <td>
                <input type="number" min="0" step="0.01" name="price" required>
            </td>
            <td>Вес: </td>
            <td>
                <input type="number" min="0" step="0.001" name="weight" required>
            </td>
            <td>Скидка: </td>
            <td>
                <input type="checkbox" name="discount" value="true">
            </td>
            <td>
                <input type="submit" value="Добавить">
            </td>
        </tr>
    </table>
</form>
<br>
Подобрать меню:
<br>
<form action="#" method="post" target="_self">
<table>
    <tr>
        <td>Стоимость от </td>
        <td>
            <input type="number" min="0" name="priceFrom"> до
        </td>
        <td>
            <input type="number" min="0" name="priceTo">
        </td>
    </tr>
    <tr>
        <td>Скидка </td>
        <td>
            <input type="checkbox" name="discount" value="true">
        </td>
    </tr>
    <tr>
        <td>Вес до </td>
        <td>
            <input type="number" min="0" step="0.001" name="weightTo"> Кг.
        </td>
    </tr>
</table>
    <input type="submit" value="Подобрать">
</form>
<br>
Наши блюда: <br>
<c:choose>
    <c:when test="${(dishes ne null) && (fn:length(dishes) gt 0)}">
        <table border="1">
            <tr>
                <th>Название</th><th>Цена (грн)</th><th>Вес (гр)</th><th>Скидка</th>
            </tr>
            <c:forEach items="${dishes}" var="a">
                <tr>
                    <td>${a.name}</td>
                    <td>${a.price / 100.0}</td>
                    <td>${a.weight / 1000.0}</td>
                    <td>
                        <c:choose>
                            <c:when test="${a.discount eq true}">
                                Есть
                            </c:when>
                            <c:otherwise>
                                Нету
                            </c:otherwise>
                        </c:choose>
                    </td>
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
