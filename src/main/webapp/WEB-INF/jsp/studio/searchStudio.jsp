<html>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div class="row">
    		<div class="row">
      			<div class="span4 offset1"><%@ include file="/WEB-INF/jsp/menu.jsp"%></div>
      			<div class="span11 offset1" style=" margin-top: -30px;">
      			<div class="well" style="padding-top: 20px; padding-left: 10px; padding-right: 10px; ">
      			    <%@ include file="/WEB-INF/jsp/utils/quotaSearchingExceded.jsp"%>
      				<c:if test="${succesOnCreating}">
      				<div class="alert alert-success">
  						<button type="button" class="close" data-dismiss="alert">&times;</button>
  						<h4>Gracias por contribuir con elManager</h4>
  						El nuevo estudio fue agregado con &eacute;xito. Puede que tarde un tiempo en aparecer en la b&uacute;squeda.
					</div>
					</c:if>
					<c:if test="${problemWhileSearching}">
      				<div class="alert alert-error">
  						<button type="button" class="close" data-dismiss="alert">&times;</button>
  						<h4>Ocurri&oacute; un error en su petici&oacute;n</h4>
  						Su b&uacute;squeda ha expirado, por favor int&eacute;ntelo de nuevo.
					</div>
					</c:if>
					<h4 style="text-align: center" class="brand">Busca el estudio de grabaci&oacute;n o la sala de ensayo m&aacute;s pr&oacute;ximo a donde est&eacute;s</h4>
					<div class="span3 offset6" style="margin-top: 30px;">
						<a class="btn btn-primary" href="<c:url value='/bin/studio/create'/>">Agrega un estudio nuevo</a>
					</div>
					<div class="hero-unit">
						<form:form action="studio" class="form-horizontal" method="POST" commandName="findStudioForm">
							<%@ include file="/WEB-INF/jsp/utils/errorsForm.jsp"%>
							
							<div class="control-group">
  								<label class="control-label" for="category"><strong>¿Qu&eacute; buscas?</strong></label>
  								<div class="controls">
    								<form:select path="category">
										<option value="SALA DE ENSAYO">SALA DE ENSAYO</option>
   										<option value="ESTUDIO DE GRABACION">ESTUDIO DE GRABACION</option>
									</form:select>
  								</div>
							</div>
							
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