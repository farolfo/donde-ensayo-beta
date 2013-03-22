<html>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div class="row">
    		<div class="row">
      			<div class="span4 offset1"><%@ include file="/WEB-INF/jsp/menu.jsp"%></div>
      			<div class="span11 offset1" style="margin-top: -30px;">
      			<div class="well" style="padding-top: 20px; padding-left: 10px; padding-right: 10px">
      				<%@ include file="/WEB-INF/jsp/utils/quotaAddingExceded.jsp"%>
					<h4 style="text-align: center">Busca el estudio de grabaci&oacute;n o la sala de ensayo m&aacute;s pr&oacute;ximo a donde est&eacute;s</h4>
					<div class="hero-unit">
						<h4>Formulario para agregar un estudio o sala nueva</h4><br />
						<form:form action="create" method="POST" class="form-horizontal" commandName="createStudioForm">
							<%@ include file="/WEB-INF/jsp/utils/errorsForm.jsp"%>
							
							<div class="control-group">
  								<label class="control-label" for="name"><strong>Nombre del Estudio:</strong></label>
  								<div class="controls">
    								<form:input type="text" class="input-large" path="name" />
									<span class="help-inline"><em>(Ej: Estudio 2.1)</em></span>
  								</div>
							</div>
							
							<div class="control-group">
    							<div class="controls">
        							<form:checkbox path="recStudio" /> Cuenta con <strong>estudio de grabaci&oacute;n</strong>
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