package mulesoft.apps.elManager.web.command.forms;

import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

import org.springframework.validation.Errors;

public class CreateStudioForm extends CreatePlaceForm{

	private boolean recStudio;

	public Boolean getRecStudio() {
		return recStudio;
	}

	public void setRecStudio(Boolean recStudio) {
		this.recStudio = recStudio;
	}

	@Override
	public boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors) throws QuotaExcededException {
		StringBuilder sb = new StringBuilder();
		sb.append(name + " ");
		if(recStudio){
			sb.append("(ESTUDIO DE GRABACION)");
		}
		return super.create(geocodingService, placeService, errors, sb.toString());
	}
	

}
