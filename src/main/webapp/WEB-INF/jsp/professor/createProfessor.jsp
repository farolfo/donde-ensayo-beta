<html>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div class="row">
    		<div class="row">
      			<div class="span4 offset1"><%@ include file="/WEB-INF/jsp/menu.jsp"%></div>
      			<div class="span11 offset1" style="margin-top: -30px;">
      			<div class="well" style="padding-top: 20px; padding-left: 10px; padding-right: 10px; background-color: F9FFFC">
      				<%@ include file="/WEB-INF/jsp/utils/quotaAddingExceded.jsp"%>
					<h4 style="text-align: center">Busca el profesor de tu instrumento m&aacute;s pr&oacute;ximo a donde est&eacute;s</h4>
					<div class="hero-unit" style="background-color: EBFFF5">
						<h4>Formulario para agregar un profesor nuevo</h4><br />
						<form:form action="create" method="POST" class="form-horizontal" commandName="createProfessorForm">
							<%@ include file="/WEB-INF/jsp/utils/errorsForm.jsp"%>
							
							<div class="control-group">
  								<label class="control-label" for="name"><strong>Nombre y Apellido del Profesor:</strong></label>
  								<div class="controls">
    								<form:input type="text" class="input-large" path="name" />
									<span class="help-inline"><em>(Ej: Juan Foo Perez)</em></span>
  								</div>
							</div>
							
							<div class="control-group">
  								<label class="control-label" for="instrument"><strong>Instrumento:</strong></label>
  								<div class="controls">
									<form:select path="instrument">
										<option value="Armonica">Armonica</option>
										<option value="Armonio">Armonio</option>
										<option value="Arpa">Arpa</option>
										<option value="Bajo">Bajo</option>
										<option value="Balalaica">Balalaica</option>
										<option value="Bandurria">Bandurria</option>
										<option value="Banjo">Banjo</option>
										<option value="Bombardino">Bombardino</option>
										<option value="Brazalete de semillas">Brazalete de semillas</option>
										<option value="Campana">Campana</option>
										<option value="Cascabeles">Cascabeles</option>
										<option value="Casta�uelas">Casta�uelas</option>
										<option value="Charango">Charango</option>
										<option value="Citara">Citara</option>
										<option value="Clarinete">Clarinete</option>
										<option value="Clarinete bajo">Clarinete bajo</option>
										<option value="Clavecin">Clavecin</option>
										<option value="Clavicordio">Clavicordio</option>
										<option value="Contrabajo">Contrabajo</option>
										<option value="Contrafagot">Contrafagot</option>�
										<option value="Corno ingles">Corno ingles</option>
										<option value="Cuatro">Cuatro</option>
										<option value="Espineta">Espineta</option>
										<option value="Fagot">Fagot</option>
										<option value="Flabiol">Flabiol</option>
										<option value="Flauta">Flauta</option>
										<option value="Fliscorno">Fliscorno</option>
										<option value="Gaita">Gaita</option>
										<option value="Gong">Gong</option>
										<option value="Guitarra">Guitarra</option>
										<option value="Laud">Laud</option>
										<option value="Lira viola">Lira viola</option>�
										<option value="Lira">Lira</option>
										<option value="Maracas">Maracas</option>
										<option value="Melodica">Melodica</option>
										<option value="Oboe">Oboe</option>
										<option value="OboeFlautin o piccolo">OboeFlautin o piccolo</option>�
										<option value="Ocarina">Ocarina</option>
										<option value="Organo">Organo</option>
										<option value="Piano">Piano</option>
										<option value="Platillos">Platillos</option>
										<option value="Quena">Quena</option>
										<option value="Rabel">Rabel</option>
										<option value="Requinto">Requinto</option>
										<option value="Saxofon">Saxofon</option>
										<option value="Sicu">Sicu</option>
										<option value="Sitar">Sitar</option>
										<option value="Sonajas">Sonajas</option>
										<option value="Sousafon">Sousafon</option>
										<option value="Tin y Low Whistle">Tin y Low Whistle</option>�
										<option value="Triangulo">Triangulo</option>
										<option value="Trombon">Trombon</option>
										<option value="Trompeta">Trompeta</option>
										<option value="TrompetaTrompa">TrompetaTrompa</option>
										<option value="Tuba">Tuba</option>
										<option value="Txistuv">Txistuv</option>
										<option value="Viola">Viola</option>
										<option value="Violin">Violin</option>
										<option value="Violonchelo">Violonchelo</option>��
										<option value="Xilofono">Xilofono</option>
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