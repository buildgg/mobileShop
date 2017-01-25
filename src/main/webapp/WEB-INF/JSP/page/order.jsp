<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mobile-shop" tagdir="/WEB-INF/tags" %>
<div id="order">
  <c:if test="${CURRENT_MESSAGE != null}">
    <div class="alert alert-success hidden-print" role="alert">${CURRENT_MESSAGE}</div>
  </c:if>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th>Product</th>
      <th>Price</th>
      <th>Count</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${ORDER.items}">
      <tr id="product${order.product.id}" class="item">
        <td class="text-center">
          <img class="small" src="${order.product.imageLink}" alt="${order.product.name}"><br>${order.product.name}
        </td>
        <td class="price">$${order.product.price}</td>
        <td class="count">${order.count}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<div class="row hidden-print">
  <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
    <c:choose>
      <c:when test="${CURRENT_ACCOUNT != null }">
        <a href="/my-orders" class="btn btn-primary btn-block">My orders</a>
      </c:when>
    </c:choose>
  </div>
</div>