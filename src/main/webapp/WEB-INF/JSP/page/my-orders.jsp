<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="my-orders" data-page-count-order="${PAGE_COUNT_ORDER}" data-page-number-order="1">

    <table class="table table-bordered">
        <thead>
        <tr>
            <th><h3 class="text-center">My orders</h3></th>
            <th><h3 class="text-center">Created</h3></th>
        </tr>
        </thead>
        <tbody class="order-items">
        <jsp:include page="../fragment/my-orders-list.jsp"/>
        </tbody>
    </table>
</div>
<div class="text-center hidden-print">
    <img id="loadMoreIndicator" src="/static/img/loading.gif" class="hidden" alt="Loading...">
    <a id="loadMoreOrder" class="btn btn-success">Load more orders</a>
</div>