package mulesoft.apps.elManager.web.command.forms;

import java.util.ArrayList;
import java.util.regex.Pattern;

import mulesoft.apps.elManager.domain.model.Place;
import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

import org.springframework.validation.Errors;

public abstract class CreatePlaceForm extends PlaceForm{

	private static final String TELEPHONE_REGEXP = "[0-9A-Z+-a-z][0-9 A-Z+-a-z]*";

	private static final String WEBSITE_REGEXP = "(((https://|http://)(www.)?)|(www.))([a-zA-Z0-9-+/:;%~\\$#?=._]+)";
	
	protected String name;
	protected String telephone;
	protected String website;
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public ArrayList<Place> search(GeocodingService geocodingService, PlaceService placeService, Errors errors, StringBuilder nP) {
		//non supported method
		return null;
	}
	
	public boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors, String name) throws QuotaExcededException {
		if(!Pattern.matches(TELEPHONE_REGEXP, telephone)){
			errors.reject("telephoneInvalid");
		}
		if(!website.equals("") && !Pattern.matches(WEBSITE_REGEXP, website)){
			errors.reject("websiteInvalid");
		}
		return super.create(geocodingService, placeService, errors, name, telephone, website);
	}
}
