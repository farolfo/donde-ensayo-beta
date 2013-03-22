package mulesoft.apps.elManager.web.command.forms;

import java.util.ArrayList;
import java.util.regex.Pattern;

import mulesoft.apps.elManager.domain.model.Place;
import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.InvalidLocationExeption;
import mulesoft.apps.elManager.domain.util.Location;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

import org.springframework.validation.Errors;

public abstract class PlaceForm {
	private static final String WORD_REGEXP = "[a-zA-Z.áéíóúÁÉÍÓÚ´'0-9]+( +[a-záéíóúÁÉÍÓÚ´'A-Z.0-9]+)*" ;
	
	private static final String STREET_REGEXP = WORD_REGEXP ;

	private static final String CITY_REGEXP = WORD_REGEXP;

	private static final String STATE_REGEXP = WORD_REGEXP;

	private static final String COUNTRY_REGEXP = WORD_REGEXP;
	
	protected String street1;
	protected String street2;
	protected Integer number;
	protected String city;
	protected String state;
	protected String country;
	protected Integer radius;
	
	public PlaceForm(){
	}

	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getRadius() {
		return radius;
	}
	
	public void setRadius(Integer radius){
		this.radius = radius;
	}
	
	/***
	 * Valida los datos y retorna un ArrayList<Place> con los resultados. En caso de algun error se notifica en 'errors' y se retorna null
	 * @throws QuotaExcededException 
	 */
	public abstract ArrayList<Place> search(GeocodingService geocodingService, PlaceService placeService, Errors errors, StringBuilder nextPages) throws QuotaExcededException;
	
	/***
	 * Valida los datos y retorna true si se creo el place.
	 * @throws QuotaExcededException 
	 */
	public abstract boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors) throws QuotaExcededException;
	
	protected ArrayList<Place> search(GeocodingService geocodingService, PlaceService placeService, String query, Errors errors, StringBuilder nextPagesAnswer) throws QuotaExcededException{
		validate(errors);
		if(!errors.hasFieldErrors("radius") && radius != null && radius <= 0){
			errors.reject("radiusInvalidNegative");
		}
		if(!Pattern.matches(STREET_REGEXP, street2) && ( errors.hasFieldErrors("number") || number == null || number <= 0)){
			errors.reject("street2AndNumberEmpty");
		}
		if(errors.hasErrors()){
			return null;
		}
		try {
			Location location = geocodingService.getLocation(street1, street2, number, city,
				state, country);
			System.out.println("Location founded > " + location.getLatitude() + "," + location.getLongitude());
			nextPagesAnswer.delete(0, nextPagesAnswer.length());
			nextPagesAnswer.append("");
			ArrayList<Place> places = placeService.search(query, location.getLatitude(), location.getLongitude(), radius * 100, nextPagesAnswer);
//			for(Place p : places ){
//				placeService.details(p);
//				try{
//					p.setFacebookLikes(socialNetworkService.getLikes(p.getWebsite()));
//				}catch(NoSocialNetworkSiteException e){
//					p.setFacebookLikes(null);
//				}
//			}
			return places;
		} catch (InvalidLocationExeption e) {
			errors.reject("locationNoFounded");
		}
		return null;
	}

	protected boolean create(GeocodingService geocodingService, PlaceService placeService, Errors errors, String name, String telephone, String website) throws QuotaExcededException{
		validate(errors);
		if(!Pattern.matches(STREET_REGEXP, street2)){
			errors.reject("street2Invalid");
		}
		if( !errors.hasFieldErrors("number") && number != null && number <= 0){
			errors.reject("numberInvalidNegative");
		}
		if(errors.hasErrors()){
			return false;
		}
		//Load a new place
		try {
			Location location = geocodingService.getLocation(street1, street2, number, city,
				state, country);
			System.out.println("Location founded to add > " + location.getLatitude() + "," + location.getLongitude());
			String formatted_address = number + " " + street1 + ", " + city + ", " + state + ", " + country;
			return placeService.create(location.getLatitude(), location.getLongitude(), name, formatted_address, telephone, website);
		} catch (InvalidLocationExeption e) {
			errors.reject("locationNoFounded");
		}
		return false;
	}

	private void validate(Errors errors) {
		if(!Pattern.matches(STREET_REGEXP, street1)){
			errors.reject("street1Invalid");
		}
		if(!Pattern.matches(CITY_REGEXP, city)){
			errors.reject("cityInvalid");
		}
		if(!Pattern.matches(STATE_REGEXP, state)){
			errors.reject("stateInvalid");
		}
		if(!Pattern.matches(COUNTRY_REGEXP, country)){
			errors.reject("countryInvalid");
		}
	}
	
}
