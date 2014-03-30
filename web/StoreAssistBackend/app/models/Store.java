package models;

public class Store {
	private long id;
	private String address;
	private int zipCode;
	private String city;

	public Store(long id, String address, int zipCode, String city) {
		super();
		this.id = id;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Store [id=").append(id).append(", address=")
				.append(address).append(", zipCode=").append(zipCode)
				.append(", city=").append(city).append("]");
		return builder.toString();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
