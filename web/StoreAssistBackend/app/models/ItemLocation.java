package models;

/**
 * Location of an item within a store.
 * 
 * @author excelsior
 *
 */
public class ItemLocation {
	private long id;
	private String section = null;
	private String aisle = null;
	private String shelf = null;
	
	public ItemLocation(long id, String section, String aisle, String shelf) {
		super();
		this.id = id;
		this.section = section;
		this.aisle = aisle;
		this.shelf = shelf;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemLocation [id=").append(id).append(", section=")
				.append(section).append(", aisle=").append(aisle)
				.append(", shelf=").append(shelf).append("]");
		return builder.toString();
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getShelf() {
		return shelf;
	}
	public void setShelf(String shelf) {
		this.shelf = shelf;
	}
	
}
