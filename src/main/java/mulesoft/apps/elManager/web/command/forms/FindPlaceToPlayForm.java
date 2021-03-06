package mulesoft.apps.elManager.web.command.forms;

import java.util.ArrayList;

import mulesoft.apps.elManager.domain.model.Place;
import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

import org.springframework.validation.Errors;

public class FindPlaceToPlayForm extends PlaceForm{

	private String category;
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public ArrayList<Place> search(GeocodingService geocodingService, PlaceService placeService, Errors errors, StringBuilder nextPages) throws QuotaExcededException {
		category = category.replace(" ", "+");
		return super.search(geocodingService, placeService, category, errors, nextPages);
	}

	@Override
	public boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors) {
		//Non-supported method
		return false;
	}

}
