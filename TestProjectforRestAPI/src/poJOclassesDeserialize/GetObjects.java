package poJOclassesDeserialize;

//for pojo classes, we first declare all nodes to variables. 

public class GetObjects {
	
	
	private String linkedIn;
	private String expertise;
	private String services;
	private String url;
	private String instructor;
	
	//Private String courses . this variable was declared like this before being changed to GetCourses
	private  GetCourses courses; // Because 
	
	
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public GetCourses getCourses() {
		return courses;
	}
	public void setCourses(GetCourses courses) {
		this.courses = courses;
	}
	
	

		
	}
	
	


