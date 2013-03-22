package mulesoft.apps.elManager.web.command.forms;

import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

import org.springframework.validation.Errors;

public class CreatePlaceToPlayForm extends CreatePlaceForm{

	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors) throws QuotaExcededException {
		StringBuilder sb = new StringBuilder();
		sb.append(name + " ");
		if(!category.equalsIgnoreCase("otro")){
			sb.append(" - "+category);
		}
		return create(geocodingService, placeService, errors, sb.toString());
	}
	

}
