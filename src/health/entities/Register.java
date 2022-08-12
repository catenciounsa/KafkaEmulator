package health.entities;

public class Register {
	
	protected final String name;
	protected final char sex;
	protected final int age;
	protected final int height;
	protected final int weight;
	
	public Register(String name, char sex, int age, int height, int weight) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public char getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public int getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}
	
}
