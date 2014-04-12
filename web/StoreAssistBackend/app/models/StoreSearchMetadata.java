package models;

import com.google.common.base.Strings;

/**
 * Metadata holder for searching a store by various parameters : id/address/city/zip.
 * @author excelsior
 *
 */
public class StoreSearchMetadata {
	private String storeId;
	private String name;
	private String address;
	private Integer zip;
	private String city;

	public StoreSearchMetadata()
	{
		
	}
	
	public StoreSearchMetadata(String storeId, String name, String address, Integer zip, String city) {
		super();
		if(!Strings.isNullOrEmpty(storeId)) {
			this.storeId = storeId;			
		}
		if(!Strings.isNullOrEmpty(name)) {
			this.name = name;			
		}
		if(!Strings.isNullOrEmpty(address)) {
			this.address = address;			
		}
		if(zip != null) {
			this.zip = zip;			
		}
		if(Strings.isNullOrEmpty(city)) {
			this.city = city;			
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StoreSearchMetadata [storeId=").append(storeId)
				.append(", address=").append(address).append(", zip=")
				.append(zip).append(", city=").append(city).append("]");
		return builder.toString();
	}
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
