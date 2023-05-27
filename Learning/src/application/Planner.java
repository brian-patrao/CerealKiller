package application;

public class Planner {
	private int id;
	private String day;
	private String breakfast;
	private String lunch;
	private String dinner;
	
	public Planner(int id,  String day, String breakfast,String lunch, String dinner) {
		this.id = id;
		this.day = day;
		this.breakfast = breakfast;
		this.dinner = dinner;
		this.lunch = lunch;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getDinner() {
		return dinner;
	}
	public void setDinner(String dinner) {
		this.dinner = dinner;
	}
	
	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

}
