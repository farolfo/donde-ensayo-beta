<div class="control-group">
  								<label class="control-label" for="street1"><strong>Calle:</strong></label>
  								<div class="controls">
    								<form:input type="text" class="input-large" path="street1" />
    								<span class="help-inline"><em>(Ej: Av. Cordoba)</em></span>
  								</div>
							</div>
				
							<div class="control-group">
  								<label class="control-label" for="number"><strong>Altura:</strong></label>
  								<div class="controls">
									<form:input type="text" class="input-small positiveRestricted" path="number" />
    								<span class="help-inline"><em>(Ej: 1824)</em> <span class="label label-info">Solo debes ingresar la altura o la esquina </span></span>
  								</div>
							</div>
				
             				<div class="control-group">
  								<label class="control-label" for="street2"><strong>o Esquina:</strong></label>
  								<div class="controls">
									<form:input type="text" class="input-large" path="street2" />
    								<span class="help-inline"><em>(Ej: 9 de Julio)</em></span>
  								</div>
							</div>
							
							<div class="control-group">
  								<label class="control-label" for="city"><strong>Ciudad</strong></label>
  								<div class="controls">
									<form:input type="text" class="input-large" path="city" />
    								<span class="help-inline"><em>(Ej: Capital Federal)</em></span>
  								</div>
							</div>
				
							<div class="control-group">
  								<label class="control-label" for="state"><strong>Provincia</strong></label>
  								<div class="controls">
									<form:input type="text" class="input-large" path="state" />
    								<span class="help-inline"><em>(Ej: Buenos Aires)</em></span>
  								</div>
							</div>
				
							<div class="control-group">
  								<label class="control-label" for="country"><strong>Pa&iacute;s</strong></label>
  								<div class="controls">
									<form:input type="text" class="input-large" path="country" />
    								<span class="help-inline"><em>(Ej: Argentina)</em></span>
  								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="radius"><strong>Radio de cobertura</strong></label>
  								<div class="controls">
									<form:input type="text" class="input-small positiveRestricted" path="radius" value="25" />
    								<span class="help-inline"><em>Especifica un radio de cuadras a analizar, el standar es 25</em></span>
  								</div>
							</div>
							
							<div class="control-group">
    							<div class="controls">
									<input type="submit" class="btn" value="Buscar" />
    							</div>
  							</div>