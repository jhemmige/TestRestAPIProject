//FOR PRACTISING GETTERS N SETTERS

package poJOclassesDeserialize;

public class PojoClass1GetCourse {
	
	String Greetings="what's up";
	String hi="hey";

//	public String gethi() {
//		
//		return hi;
//		
//	}
//	
//	public void setGreetings(String hi) {
//		
//		this.hi=hi;
//	}
//	

	

	public static void main(String[] args) {
		
		PojoClass1GetCourse pc= new PojoClass1GetCourse();
//System.out.println(pc.setGreetings(hi));	
		
System.out.println(pc.getGreetings());		
		pc.getHi();
		
		
		
		
	}

	public String getGreetings() {
		return Greetings;
	}

	public void setGreetings(String greetings) {
		Greetings = greetings;
	}

	public String getHi() {
		return hi;
	}

	public void setHi(String hi) {
		this.hi = hi;
	}



}
