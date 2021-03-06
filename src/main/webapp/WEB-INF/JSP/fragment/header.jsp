<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mobile-shop" tagdir="/WEB-INF/tags" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#ishopNav" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/products">IShop</a>
        </div>
        <div class="collapse navbar-collapse" id="ishopNav">
            <ul id="currentShoppingCart" class="nav navbar-nav navbar-right ${CURRENT_SHOPPING_CART == null ? 'hidden': ''} ">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i> Shopping cart (<span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span>)<span class="caret"></span>
                    </a>
                    <div class="dropdown-menu shopping-cart-desc">
                        Total count: <span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span><br>
                        Total cost: <span class="total-cost">${CURRENT_SHOPPING_CART.totalCost}</span><br>
                        <a href="/shopping-cart" class="btn btn-primary btn-block">View cart</a>
                    </div>
                </li>
            </ul>
            <c:choose>
                <c:when test="${CURRENT_ACCOUNT != null }">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a>Welcome ${CURRENT_ACCOUNT.description }</a></li>
                        <li><a href="/my-orders">My orders</a></li>
                        <li><mobile-shop:sign-out/></li>
                       <%-- <li><a href="/sign-out">Sign out</a></li>--%>
                    </ul>
                </c:when>
                <c:otherwise>
                    <mobile-shop:sign-in/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
