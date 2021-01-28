package poJOclassesSerialize;

//  we can also extend the class for Serialize_POJO1 and create obj for the class which is  now commented .
//This will give the same result as called this class into Serialize_POJO1.java and setting variables there;

public class SetUpdatedAddress
{
	private String place_id;//= pID;
	private String address;//="70 Summer walk, USA";
	private String key;//= "qaclick123";
	
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	
}
