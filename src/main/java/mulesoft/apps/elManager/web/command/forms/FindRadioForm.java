package mulesoft.apps.elManager.web.command.forms;

import java.util.ArrayList;

import mulesoft.apps.elManager.domain.model.Place;
import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

import org.springframework.validation.Errors;

public class FindRadioForm extends PlaceForm{

	private static final String RADIO_QUERY = "emisora+de+radio";
	
	@Override
	public ArrayList<Place> search(GeocodingService geocodingService, PlaceService placeService, Errors errors, StringBuilder nextPages) throws QuotaExcededException {
		return super.search(geocodingService, placeService, RADIO_QUERY, errors, nextPages);
	}

	@Override
	public boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors) {
		//Non-supported method
		return false;
	}

}
