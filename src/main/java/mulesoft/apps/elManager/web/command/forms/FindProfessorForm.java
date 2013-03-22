package mulesoft.apps.elManager.web.command.forms;

import java.util.ArrayList;

import mulesoft.apps.elManager.domain.model.Place;
import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

import org.springframework.validation.Errors;

public class FindProfessorForm extends PlaceForm{

	private String instrument;
	
	private static final String PROFESSOR_QUERY = "profesor+de+";
	
	public String getInstrument() {
		return instrument;
	}
	
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	
	@Override
	public ArrayList<Place> search(GeocodingService geocodingService, PlaceService placeService, Errors errors, StringBuilder nextPages) throws QuotaExcededException {
		instrument = instrument.replace(" ", "+");
		return super.search(geocodingService, placeService, PROFESSOR_QUERY+instrument, errors, nextPages);
	}

	@Override
	public boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors) {
		//Non-supported method
		return false;
	}

}
