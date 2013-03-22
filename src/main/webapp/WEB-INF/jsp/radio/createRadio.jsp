<html>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div class="row">
    		<div class="row">
      			<div class="span4 offset1"><%@ include file="/WEB-INF/jsp/menu.jsp"%></div>
      			<div class="span11 offset1" style="margin-top: -30px;">
      			<div class="well" style="padding-top: 20px; padding-left: 10px; padding-right: 10px; background-color: FAF3F4">
      				<%@ include file="/WEB-INF/jsp/utils/quotaAddingExceded.jsp"%>
					<h4 style="text-align: center">Busca la emisora de radio m&aacute;s cercana a donde est&eacute;s</h4>
					<div class="hero-unit" style="background-color: FFF6F7">
						<h4>Formulario para agregar una emisora nueva</h4><br />
						<form:form action="create" method="POST" class="form-horizontal" commandName="createRadioForm">
							<%@ include file="/WEB-INF/jsp/utils/errorsForm.jsp"%>
							
							<div class="control-group">
  								<label class="control-label" for="name"><strong>Nombre de la Emisora:</strong></label>
  								<div class="controls">
    								<form:input type="text" class="input-large" path="name" />
									<span class="help-inline"><em>(Ej: Radio Region)</em></span>
  								</div>
							</div>
							
							<div class="control-group">
  								<label class="control-label" for="mode"><strong>Emite en:</strong></label>
  								<div class="controls">
    								<form:select path="mode">
										<option value="FM">FM</option>
   										<option value="AM">AM</option>
									</form:select>
  								</div>
							</div>							
							
							<div class="control-group">
  								<label class="control-label" for="frequency"><strong>Frecuencia:</strong></label>
  								<div class="controls">
    								<form:input type="text" class="input-large positiveRestrictedDouble" path="frequency" />
									<span class="help-inline"><em>(Ej: 90.5)</em></span>
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