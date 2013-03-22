package mulesoft.apps.elManager.web.command.forms;

import java.util.ArrayList;

import mulesoft.apps.elManager.domain.model.Place;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

public class FindNextResultsForm {

	private StringBuilder nextResults;
	
	public FindNextResultsForm(StringBuilder sb) {
		this.nextResults = sb;
	}

	public ArrayList<Place> search(PlaceService placeService) throws QuotaExcededException {
		if(nextResults.toString().equals("")){
			return null;
		}
		ArrayList<Place> places = placeService.search("", 2.0, 2.0, 10, nextResults);
		return places;
		//Solo se utiliza nextResults en la bsuqueda, los otros parametros se obvian
	}
	
	
	
}
