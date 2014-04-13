package models;

/**
 * Model object to represent a retail store and its location details.
 * 
 * @author excelsior
 *
 */
public class Store {
	private Integer id;
	private String name;
	private String address;
	private Integer zipCode;
	private String city;

	public Store(Integer id, String name, String address, int zipCode, String city) {
		super();
		this.id = id;
		this.name = name;
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
