<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="ishop" tagdir="/WEB-INF/tags" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mobile-shop" tagdir="/WEB-INF/tags" %>

<div id="shoppingCart">
    <c:if test="${CURRENT_ACCOUNT== null}">
    <div class="alert alert-warning hidden-print" role="alert">To make order, please sign in</div>
     </c:if>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Count</th>
            <th class="hidden-print">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cart" items="${CURRENT_SHOPPING_CART.items}">
            <tr id="product${cart.product.id}" class="item">
                <td class="text-center">
                    <img class="small" src="${cart.product.imageLink}" alt="${cart.product.name}"><br>${cart.product.name}
                </td>
                <td class="price">$${cart.product.price}</td>
                <td class="count">${cart.count}</td>
                <td class="hidden-print">
                <c:choose>
                <c:when test="${cart.count > 1}">

                    <a class="btn btn-danger remove-product" data-id-product="${cart.product.id}" data-count="1">Remove one</a><br>
                    <a class="btn btn-danger remove-product all" data-id-product="${cart.product.id}" data-count="${cart.count}">Remove all</a>

                </c:when>
                <c:otherwise>
                    <a class="btn btn-danger remove-product" data-id-product="${cart.product.id}" data-count="1">Remove one</a>
                </c:otherwise>
                </c:choose>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2" class="text-right"><strong>Total:</strong></td>
            <td colspan="2" class="total">${CURRENT_SHOPPING_CART.totalCost}</td>
        </tr>
        </tbody>
    </table>
<%--    <div class="row hidden-print">
        <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
            <a class="btn btn-primary btn-block"><i class="fa fa-facebook-official" aria-hidden="true"></i> Sign in</a>
        </div>
    </div>--%>
    <c:if test="${CURRENT_ACCOUNT== null}">
    <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
        <mobile-shop:sign-in/>
    </div>
    </c:if>
</div>