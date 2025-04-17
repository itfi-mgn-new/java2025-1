package lesson10;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = -78055249292162343L;
	
	public transient final Object	x = new Object();
	public final String	family;
	public final String	name;
	public final String	patromName;
	public final int	age;
	public final Address	addr;

	public Person(String family, String name, String patromName, int age, Address addr) {
		this.family = family;
		this.name = name;
		this.patromName = patromName;
		this.age = age;
		this.addr = addr;
		System.err.println("slkdfjjlksdjfgklksjdlkfjdsklf");
	}

	@Override
	public String toString() {
		return "Person [family=" + family + ", name=" + name + ", patromName=" + patromName + ", age=" + age + ", addr="
				+ addr + "]";
	}
}
