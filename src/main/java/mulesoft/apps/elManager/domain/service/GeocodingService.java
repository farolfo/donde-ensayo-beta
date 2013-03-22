package mulesoft.apps.elManager.domain.service;

import mulesoft.apps.elManager.domain.util.InvalidLocationExeption;
import mulesoft.apps.elManager.domain.util.Location;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;

public interface GeocodingService {

	/***
	 * Returns the location(latitude,longitude) of a given place.
	 * 
	 * @param street1
	 * @param street2
	 * @param number
	 * @param city
	 * @param state
	 * @param country
	 * @return
	 * @throws InvalidLocationExeption
	 */
	Location getLocation(String street1, String street2, Integer number,
			String city, String state, String country) throws InvalidLocationExeption, QuotaExcededException;

}
