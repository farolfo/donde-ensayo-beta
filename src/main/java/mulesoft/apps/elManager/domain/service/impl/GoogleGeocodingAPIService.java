package mulesoft.apps.elManager.domain.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mulesoft.apps.elManager.domain.service.GeocodingService;
import mulesoft.apps.elManager.domain.util.InvalidLocationExeption;
import mulesoft.apps.elManager.domain.util.Location;
import mulesoft.apps.elManager.domain.util.StringCommons;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GoogleGeocodingAPIService implements GeocodingService {

	private static final String GEOCODING_API_BASE = "http://maps.googleapis.com/maps/api/geocode";

	private static final String OUT_JSON = "/json";

	public Location getLocation(String street1, String street2, Integer number,
			String city, String state, String country)
			throws InvalidLocationExeption {
		// return new Location(-34.759666, -58.395424);
		Location location;
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(GEOCODING_API_BASE);
			sb.append(OUT_JSON);
			sb.append("?sensor=false");
			sb.append("&address=" + StringCommons.decodeAccents(buildKeyword(street1, street2, number, city, state, country)));

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
		} catch (MalformedURLException e) {
			throw new InvalidLocationExeption();
		} catch (IOException e) {
			throw new InvalidLocationExeption();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		try {
			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			if(!jsonObj.getString("status").equalsIgnoreCase("OK")){
				System.out.println("ERROR : GoogleGeocodingAPI didn't reply OK");
				throw new InvalidLocationExeption();
			}
			Double lat = jsonObj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			Double lng = jsonObj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
			location = new Location(lat,lng);
		} catch (JSONException e) {
			throw new InvalidLocationExeption();
		}
		return location;
	}

	private String buildKeyword(String street1, String street2, Integer number,
			String city, String state, String country) {
		StringBuilder keyword = new StringBuilder(GEOCODING_API_BASE);
		if(number != null){
			keyword.append(street1.replace(" ", "") + "+");
		}
		keyword.append(street1.replace(" ", "+") + "+");
		if(street2 != null){
			keyword.append("and+" + street1.replace(" ", "+") + "+");
		}
		keyword.append(city.replace(" ", "+") + "+");
		keyword.append(state.replace(" ", "+") + "+");
		keyword.append(country.replace(" ", "+"));
		return keyword.toString();
	}

}