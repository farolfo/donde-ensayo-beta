<div style="background: white; " class="well">
<strong style="padding-left: 10px; ">${searchEntry.name}</strong><br>
<label style="padding-left: 10px; "> ${searchEntry.vicinity}</label>
<div id="loading_${index}" style="margin-left: auto; margin-right: auto; text-align: center; display: none"><img src="<c:url value='/img/loading.gif'/>" class="img-rounded" alt="Buscando..." style="margin-left: auto; margin-right: auto;"> </div>
<div id="showDetails_${index}"><small><a href="#" onclick="loadDetails(${index}, ${searchEntry.latitude}, ${searchEntry.longitude}, '${searchEntry.reference}');return false;">Ver m&aacute;s...</a></small></div>
<div id="placeDetails_${index}" style="display: none;"></div>
<div id="hideDetails_${index}" style="display: none;"><small><a href="#" onclick="hideDetails(${index});return false;">Ocultar</a></small></div>
</div>
<c:set var="index" value="${index + 1}" />
