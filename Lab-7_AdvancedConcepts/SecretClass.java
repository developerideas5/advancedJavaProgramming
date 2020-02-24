
public class SecretClass {
	public int field1;
	protected String field2;
	private double field3;
	
	public SecretClass() {
		this.field1 = 42;
		this.field2 = "what's up?";
		this.field3 = 3.14;
	}

	public SecretClass(int field1, String field2) {
		super();
		this.field1 = field1;
		this.field2 = field2;
	}
	
	public void sayHello() {
		System.out.println("hi");
	}
	
	private int doSomething() {
		System.out.println("does nothing");
		return -1;
	}
}
