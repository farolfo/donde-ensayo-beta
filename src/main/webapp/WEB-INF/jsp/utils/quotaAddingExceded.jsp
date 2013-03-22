<c:if test="${quotaExceded}">
<div class="alert alert-error">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <h4>Lo sentimos, ha excedido la cantidad de agregados disponibles por d&iacute;a</h4>
  Por favor int&eacute;ntalo nuevamente mañana
</div>
</c:if>