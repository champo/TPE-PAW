package ar.edu.itba.paw.grupo1.web.LabelsExample;

public class Weather {

	private int min;
	private int max;
	private String City;
	
	public Weather(int min, int max, String city) {
		super();
		this.min = min;
		this.max = max;
		City = city;
	}
	
	
	public String getCity() {
		return City;
	}
	
	public void setCity(String city) {
		City = city;
	}
	
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
}
