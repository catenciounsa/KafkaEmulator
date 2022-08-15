package health.entities;


public class RegisterMeasured extends Register {
	
	protected String measure;

	public RegisterMeasured() {
		super();
	}
	
	public RegisterMeasured(String name, char sex, int age, int height, int weight, String measure) {
		super(name, sex, age, height, weight);
		this.measure = measure;
	}
	

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
}
