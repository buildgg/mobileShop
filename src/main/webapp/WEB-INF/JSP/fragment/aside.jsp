<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div class="visible-xs-block xs-option-container">
    <a class="pull-right" data-toggle="collapse" href="#productCatalog">Product catalog <span class="caret"></span></a>
    <a data-toggle="collapse" href="#findProducts">Find products <span class="caret"></span></a>
</div>
<!-- Search form -->
<form class="search" action="/search">
    <div id="findProducts" class="panel panel-success collapse">
        <div class="panel-heading">Find products</div>
        <div class="panel-body">
            <div class="input-group">
                <input type="text" name="query" class="form-control" placeholder="Search query">
                  <span class="input-group-btn">
                    <a id="goSearch" class="btn btn-default">Go!</a>
                  </span>
            </div>
            <div class="more-options">
                <a data-toggle="collapse" href="#searchOptions">More filters <span class="caret"></span></a>
            </div>
        </div>
        <div id="searchOptions" class="collapse">
            <div class="panel-heading">Category filters</div>
            <div class="panel-body categories">
                <label> <input type="checkbox" id="allCategories"> All </label>
                <c:forEach var="search_cat" items="${CATEGORIES}">
                    <div class="form-group">
                        <div class="checkbox">
                            <label><input type="checkbox" name="category" value="1" class="search-option">
                            ${search_cat.name}(${search_cat.productCount})</label>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="panel-heading">Producers filters</div>
            <div class="panel-body producers">
                <label> <input type="checkbox" id="allProducers"> All </label>
                <c:forEach var="search_producer" items="${PRODUCERS}">
                    <div class="form-group">
                        <div class="checkbox">
                            <label><input type="checkbox" name="producer" value="1" class="search-option">
                               ${search_producer.name}(${search_producer.productCont})</label>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
</form>
<!-- /Search form -->
<!-- Categories -->

<div id="productCatalog" class="panel panel-success collapse">
    <div class="panel-heading">Product catalog</div>
    <div class="list-group">
        <c:forEach var="cat" items="${CATEGORIES}">
            <a href="/products${cat.url}" class="list-group-item"> <span
                    class="badge">${cat.productCount}</span> ${cat.name}</a>
        </c:forEach>
    </div>
</div>

<!-- /Categories -->
