<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="order" items="${ORDER_LIST}">
  <tr id="order${order.id}" class="item">
  <td class="text-center">
    <a class="btn" href="/order?id=${order.id}" role="button"><h4> Order number ${order.id} </h4></a>
  </td>
  <td class="created text-center"><h4>${order.created}
    <%--  <fmt:formatDate type="both" value="${order.created}"/>--%>
  </h4>
  </td>
  </tr>
</c:forEach>

