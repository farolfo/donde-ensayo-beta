package mulesoft.apps.elManager.domain.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import mulesoft.apps.elManager.domain.service.SocialNetworkService;
import mulesoft.apps.elManager.domain.util.NoSocialNetworkSiteException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class FacebookService implements SocialNetworkService {
	
	private static final String SOCIALNETWORK_API_BASE =  "https://graph.facebook.com";
	private static final String TYPE_SEARCH = "/search";
	
	private static final String FACEBOOK_PAGE_PATTERN = "(((https://|http://)(www.)?)|(www.))facebook.com(.ar)?/(pages/)?.+";

	public Integer getLikes(String website) throws NoSocialNetworkSiteException{
		if(!Pattern.matches(FACEBOOK_PAGE_PATTERN, website)){
			throw new NoSocialNetworkSiteException();
		}
		HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(SOCIALNETWORK_API_BASE);
            sb.append(TYPE_SEARCH);
            sb.append("?q="+getPageName(website));
            sb.append("&type=page");
        
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

        String idToSearch = null;
        
        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("data");

            idToSearch = predsJsonArray.getJSONObject(0).getString("id");
            
        } catch (JSONException e) {
        	throw new NoSocialNetworkSiteException();
        }
        
        if(idToSearch != null){
        	return getLikesById(idToSearch);
        }
        throw new NoSocialNetworkSiteException();
	}

	private String getPageName(String website) {
		Integer index = 0;
		if(website.startsWith("http://")){
			index = 7;
		}else if(website.startsWith("https://")){
			index = 8;
		}
		if(website.substring(index, website.length()).startsWith("www.")){
			index+=4;
		}
		//remove facebook.com
		index+=12;
		if(website.substring(index, website.length()).startsWith(".ar")){
			index+=3;
		}
		//remove /
		index+=1;
		if(website.substring(index, website.length()).startsWith("pages/")){
			index+=5;
		}
		return website.substring(index, website.length()).split("/")[0];
	}

	private Integer getLikesById(String idToSearch) throws NoSocialNetworkSiteException {
		HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(SOCIALNETWORK_API_BASE);
            sb.append("/"+idToSearch);
        
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

        Integer likes = null;
        
        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            likes = jsonObj.getInt("likes");
            return likes;
        } catch (JSONException e) {
        	throw new NoSocialNetworkSiteException();
        }
	}

}
