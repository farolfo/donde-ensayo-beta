package mulesoft.apps.elManager.web.command.forms;

import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

import org.springframework.validation.Errors;

public class CreateProfessorForm extends CreatePlaceForm{

	private String instrument;

	public String getInstrument() {
		return instrument;
	}
	
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	@Override
	public boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors) throws QuotaExcededException {
		StringBuilder sb = new StringBuilder();
		sb.append(name + " - PROFESOR DE "+instrument);
		return create(geocodingService, placeService, errors, sb.toString());
	}
	
}