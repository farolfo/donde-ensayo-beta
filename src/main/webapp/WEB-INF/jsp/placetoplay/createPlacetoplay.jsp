<html>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div class="row">
    		<div class="row">
      			<div class="span4 offset1"><%@ include file="/WEB-INF/jsp/menu.jsp"%></div>
      			<div class="span11 offset1" style="margin-top: -30px;">
      			<div class="well" style="padding-top: 20px; padding-left: 10px; padding-right: 10px; background-color: F6F9EA;">
      				<%@ include file="/WEB-INF/jsp/utils/quotaAddingExceded.jsp"%>
					<h4 style="text-align: center">Busca el mejor lugar para tocar con tu banda</h4>
					<div class="hero-unit" style="background-color: F4F9DA">
						<h4>Formulario para agregar un lugar para tocar m&uacute;sica</h4><br />
						<form:form action="create" method="POST" class="form-horizontal" commandName="createPlaceToPlayForm">
							<%@ include file="/WEB-INF/jsp/utils/errorsForm.jsp"%>
							
							<div class="control-group">
  								<label class="control-label" for="name"><strong>Nombre del lugar:</strong></label>
  								<div class="controls">
    								<form:input type="text" class="input-large" path="name" />
									<span class="help-inline"><em>(Ej: The Roxy Club)</em></span>
  								</div>
							</div>
							
							<div class="control-group">
  								<label class="control-label" for="category"><strong>Categor&iacute;a:</strong></label>
  								<div class="controls">
									<form:select path="category">
										<option value="BAR PUB">BAR PUB</option>
   										<option value="CLUB BAILABLE">CLUB BAILABLE</option>
   										<option value="SOCIEDAD DE FOMENTO">SOCIEDAD DE FOMENTO</option>
   										<option value="SALON PRIVADO">SALON PRIVADO</option>
									</form:select>
  								</div>
							</div>
							
							<%@ include file="/WEB-INF/jsp/utils/createPlaceForm.jsp"%>
							
						</form:form>
					</div>
				</div>
			</div>
    	</div>
 	</div>

	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>