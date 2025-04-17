package lesson10;

import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final String	street;
	public final int	house;

	public Address(String street, int house) {
		this.street = street;
		this.house = house;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", house=" + house + "]";
	}
}
