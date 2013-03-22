<html>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div class="row">
    		<div class="row">
      			<div class="span4 offset1"><%@ include file="/WEB-INF/jsp/menu.jsp"%></div>
      			<div class="span11 offset1" style=" margin-top: -30px;">
      			<div class="well" style="padding-top: 20px; padding-left: 10px; padding-right: 10px; background-color: FAF3F4">
      			    <%@ include file="/WEB-INF/jsp/utils/quotaSearchingExceded.jsp"%>
      				<c:if test="${succesOnCreating}">
      				<div class="alert alert-success">
  						<button type="button" class="close" data-dismiss="alert">&times;</button>
  						<h4>Gracias por contribuir con elManager</h4>
  						La nueva emisora de radio fue agregada con &eacute;xito. Puede que tarde un tiempo en aparecer en la b&uacute;squeda.
					</div>
					</c:if>
					<c:if test="${problemWhileSearching}">
      				<div class="alert alert-error">
  						<button type="button" class="close" data-dismiss="alert">&times;</button>
  						<h4>Ocurri&oacute; un error en su petici&oacute;n</h4>
  						Su b&uacute;squeda ha expirado, por favor int&eacute;ntelo de nuevo.
					</div>
					</c:if>
					<h4 style="text-align: center">Busca la emisora de radio m&aacute;s cercana a donde est&eacute;s</h4>
					<div class="span3 offset6" style="margin-top: 25px;">
						<a class="btn btn-primary" href="<c:url value='/bin/radio/create'/>">Agrega una emisora nueva</a>
					</div>
					<div class="hero-unit" style="background-color: FFF6F7">
					<form:form action="radio" method="POST" class="form-horizontal" commandName="findRadioForm">
						
						<%@ include file="/WEB-INF/jsp/utils/errorsForm.jsp"%>
						
						<%@ include file="/WEB-INF/jsp/utils/searchPlaceForm.jsp"%>
					
					</form:form>
					</div>
				</div>
			</div>
    	</div>
 	</div>


	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>