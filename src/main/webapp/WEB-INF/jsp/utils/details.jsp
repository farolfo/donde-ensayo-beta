<!-- No es una buena practica este codigo javascript en un .jsp ! -->
<script type="text/javascript">
	if(${hasFacebookLikes}){$('#likes_'+${index}).show();}
</script>
<small style="padding-left: 10px; ">${placeToShow.formatted_address}</small><br/>
<small style="margin-left: 10px;">${placeToShow.formatted_phone_number}</small><br/>
<small><a href="${placeToShow.website}" style="margin-left: 10px;"> ${placeToShow.website}</a></small> <span id="likes_${index}" style="display: none" class="label label-info">cuenta con ${placeToShow.facebookLikes} likes</span>
<div id="placeMap_${index}" style="width:50%; height:50%; margin-left: auto; margin-right: auto" ></div>
