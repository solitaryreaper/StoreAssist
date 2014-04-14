package models;

/**
 * Location of an item within a store.
 * 
 * @author excelsior
 *
 */
public class ItemLocation {
	private String section = null;
	private String aisle = null;
	private String nearestLandmark = null;
	
	public ItemLocation(String section, String aisle) {
		super();
		this.section = section;
		this.aisle = aisle;
	}

	public String getSection() {
		return section;
	}
	
	public void setSection(String section) {
		this.section = section;
	}
	
	public String getAisle() {
		return aisle;
	}
	
	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

	public String getNearestLandmark() {
		return nearestLandmark;
	}

	public void setNearestLandmark(String nearestLandmark) {
		this.nearestLandmark = nearestLandmark;
	}
	
}
