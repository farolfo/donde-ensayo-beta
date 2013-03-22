<html>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div class="row">
    		<div class="row">
      			<div class="span4 offset1"><%@ include file="/WEB-INF/jsp/menu.jsp"%></div>
      			<div class="span11 offset1" style="margin-top: -30px;">
      			<div class="well" style="padding-top: 20px; padding-left: 10px; padding-right: 10px; background-color: FAF3F4">
					<h4 style="text-align: center">Busca la emisora de radio m&aacute;s cercana a donde est&eacute;s</h4>
					<div class="span3 offset6" style="margin-top: 10px;">
						<a class="btn btn-primary" href="<c:url value='/bin/radio/create'/>">Agrega una emisora nueva</a>
					</div>
					<div class="hero-unit" style="background-color: FFF6F7">
						<div class="row-fluid" style="margin-bottom: 8px;">
                			<div style="margin-left: 0;" class="span12">
                                <c:choose>
                                        <c:when test="${empty searchResultPlaces}">
                                                <span class="label label-important">La b&uacute;squeda no produjo resultados</span>
                                        </c:when>
                                        <c:otherwise>
                                        		<h5>Mostrando <c:out value="${fn:length(searchResultPlaces)}"/> emisora<c:if test="${fn:length(searchResultPlaces) gt 1}">s</c:if> de radio: </h5>
                                                <c:set var="index" value="0" />
                                                <c:forEach var="searchEntry" items="${searchResultPlaces}">
                                                        <div class="span12"
                                                                style="margin-left:0px;">
                                                                <%@ include file="/WEB-INF/jsp/utils/searchPlacesRow.jsp"%>
                                                        </div>
                                                </c:forEach>
                                            	<c:if test="${moreResultsPending}"><small ><a href="<c:url value='/bin/radio/searchNextResults?nextResults=${nextResults}'/>">Ver m&aacute;s resultados...</a></small></c:if>
                                        </c:otherwise>
                                </c:choose>
                        	</div>
                		</div>
					</div>
				</div>
			</div>
    	</div>
 	</div>

	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>