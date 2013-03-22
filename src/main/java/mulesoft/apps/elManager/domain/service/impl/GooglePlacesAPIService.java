package mulesoft.apps.elManager.domain.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import mulesoft.apps.elManager.domain.model.Place;
import mulesoft.apps.elManager.domain.service.PlaceService;
import mulesoft.apps.elManager.domain.util.QuotaExcededException;
import mulesoft.apps.elManager.domain.util.StringCommons;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GooglePlacesAPIService implements PlaceService{
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_ADD = "/add";
    private static final String TYPE_NEARBYSEARCH = "/nearbysearch";
    private static final String TYPE_DETAILS = "/details";

    private static final String OUT_JSON = "/json";
    
    private static final String LANGUAGE = "es";

    private static final String API_KEY = YOUR_KEY_HERE;
    
    public ArrayList<Place> search(String keyword, double lat, double lng, int radius, StringBuilder nextResults) throws QuotaExcededException {
        ArrayList<Place> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_NEARBYSEARCH);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&language=" + LANGUAGE);
            sb.append("&keyword=" + StringCommons.decodeAccents(keyword));//used to be URLEncoder.encode(keyword, "utf8")
            sb.append("&location=" + String.valueOf(lat) + "," + String.valueOf(lng));
            sb.append("&radius=" + String.valueOf(radius));
            if(!nextResults.toString().equals("")){
            	sb.append("&pagetoken=" + nextResults.toString());
            }
            
            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
         //   Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
           // Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            if( jsonObj.getString("status").equalsIgnoreCase("OVER_QUERY_LIMIT") ){
            	throw new QuotaExcededException();
            }else if( !jsonObj.getString("status").equalsIgnoreCase("OK") ){
            	return new ArrayList<Place>();
            }
            JSONArray predsJsonArray = jsonObj.getJSONArray("results");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<Place>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                String reference = predsJsonArray.getJSONObject(i).getString("reference");
                String name = predsJsonArray.getJSONObject(i).getString("name");
                name = StringCommons.decodeAccents(name);
                String vicinity = "";
                try{
                	vicinity = predsJsonArray.getJSONObject(i).getString("vicinity");
                }catch(JSONException E){
                	vicinity = "";
                }
                
                vicinity = StringCommons.decodeAccents(vicinity);
                //Double rating = predsJsonArray.getJSONObject(i).getDouble("rating");
                Double latitude = predsJsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                Double longitude = predsJsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                Place place = new Place(reference, name, longitude, latitude, vicinity);
                resultList.add(place);
            }
            nextResults.delete(0, nextResults.length());
            try{
                String aux = jsonObj.getString("next_page_token");
                nextResults.append(aux);
            }catch(JSONException e){
            	nextResults.append("");
            }
        } catch (JSONException e) {
        //    Log.e(LOG_TAG, "Error processing JSON results", e);
        }

        return resultList;
    }
    
    public Place details(String reference) throws QuotaExcededException {

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_DETAILS);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&language=" + LANGUAGE);
            sb.append("&reference=" + reference);
            
            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
         //   Log.e(LOG_TAG, "Error processing Places API URL", e);
            return null;
        } catch (IOException e) {
           // Log.e(LOG_TAG, "Error connecting to Places API", e);
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            if( jsonObj.getString("status").equalsIgnoreCase("OVER_QUERY_LIMIT") ){
            	throw new QuotaExcededException();
            }else if( !jsonObj.getString("status").equalsIgnoreCase("OK") ){
            	return null;
            }
            JSONObject predsJson = jsonObj.getJSONObject("result");
            // Extract the Place descriptions from the results
            String formatted_address, formatted_phone_number, website;
            try{
            	formatted_address = predsJson.getString("formatted_address");
            	formatted_address = StringCommons.decodeAccents(formatted_address);
            }catch(JSONException e){
            	formatted_address = "";
            }
            try{
            	formatted_phone_number = predsJson.getString("formatted_phone_number");
            	formatted_phone_number = StringCommons.decodeAccents(formatted_phone_number);
            }catch(JSONException e){
            	formatted_phone_number = "";
            }
            try{
            	website = predsJson.getString("website");
            	website = StringCommons.decodeAccents(website);
            }catch(JSONException e){
            	website = "";
            	if(Math.random() > 0.5){
            		website = "https://www.facebook.com/MuleSoft/"; //DEMO por si no aparecen paginas de facebook
            	}
            }
            Double latitude = predsJson.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            Double longitude = predsJson.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
            return new Place(reference, latitude, longitude, formatted_address, formatted_phone_number, website);
        } catch (JSONException e) {
        //    Log.e(LOG_TAG, "Error processing JSON results", e);
        }

        return null;
    }

	public boolean create(double latitude, double longitude, String name, String formatted_address, String telephone, String website) throws QuotaExcededException {
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_ADD);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&language=" + LANGUAGE);
            sb.append("&key=" + API_KEY);
            
            StringBuilder postDataBuilder = new StringBuilder();
            postDataBuilder.append("{\"location\": { \"lat\": "+latitude+",\"lng\": "+longitude+"}" +
            		",\"accuracy\": 5" +
            		",\"formatted_phone_number\": \""+ telephone+ "\"" + 
            		",\"formatted_address\": \""+ formatted_address +"\"");
            if(website != null && !website.equals("")){
            	postDataBuilder.append(",\"website\": \""+ website+"\"");
            }
            postDataBuilder.append(" ,\"name\": \""+name+"\", \"types\":[\"other\"]}");
            String postData = postDataBuilder.toString();
            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("CONTENT-TYPE", "text/json");
            conn.setRequestProperty("CONTENT-LENGTH", postData.length() + "");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(postData);
            out.close();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
         //   Log.e(LOG_TAG, "Error processing Places API URL", e);
            return false;
        } catch (IOException e) {
           // Log.e(LOG_TAG, "Error connecting to Places API", e);
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
        	JSONObject jsonObj = new JSONObject(jsonResults.toString());
        	if( jsonObj.getString("status").equalsIgnoreCase("OVER_QUERY_LIMIT") ){
            	throw new QuotaExcededException();
            }
			if(!jsonObj.getString("status").equalsIgnoreCase("OK")){
				System.out.println("ERROR : GooglePlacesAPI didn't reply OK on creation");
				return false;
			}
			return true;
        } catch (JSONException e) {
        //    Log.e(LOG_TAG, "Error processing JSON results", e);
        }

        return false;
	}

}