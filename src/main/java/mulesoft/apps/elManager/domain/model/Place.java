package mulesoft.apps.elManager.domain.model;

public class Place {
	private String reference;
	private String name;
	private String vicinity;
	private String formatted_address;
	private String formatted_phone_number;
	private String website;
	private Integer facebookLikes;
	private Double latitude;
	private Double longitude;

	public Place(String reference, String name, Double longitude, Double latitude, String vicinity) {
		this.reference = reference;
		this.name = name;
		this.vicinity = vicinity;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Place(String reference, Double latitude2, Double longitude2, String formatted_address, String formatted_phone_number, String website){
		this.reference = reference;
		this.latitude = latitude2;
		this.longitude = longitude2;
		this.formatted_address = formatted_address;
		this.formatted_phone_number = formatted_phone_number;
		this.website = website;
	}


	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public String getReference() {
		return reference;
	}

	public String getName() {
		return name;
	}
	
	public String getFormatted_address() {
		return formatted_address;
	}

	public String getFormatted_phone_number() {
		return formatted_phone_number;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public String getVicinity() {
		return vicinity;
	}
	
	public void setFacebookLikes(Integer facebookLikes) {
		this.facebookLikes = facebookLikes;
	}
	
	public Integer getFacebookLikes() {
		return facebookLikes;
	}
	
}
