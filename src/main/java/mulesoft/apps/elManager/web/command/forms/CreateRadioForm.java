package mulesoft.apps.elManager.web.command.forms;

import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

import org.springframework.validation.Errors;

public class CreateRadioForm extends CreatePlaceForm{

	private String mode;/*AM FM*/
	private Double frequency;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Double getFrequency() {
		return frequency;
	}

	public void setFrequency(Double frequency) {
		this.frequency = frequency;
	}

	@Override
	public boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors) throws QuotaExcededException {
		StringBuilder sb = new StringBuilder();
		sb.append(name + " ( ");
		if(!errors.hasFieldErrors("frequency") && frequency != null && frequency <= 0){
			errors.reject("frequencyInvalidNegative");
		}else{
			sb.append(frequency + " ");
		}
		sb.append(mode + " ) ");
		sb.append("- EMISORA DE RADIO");
		return create(geocodingService, placeService, errors, sb.toString());
	}
	

}
