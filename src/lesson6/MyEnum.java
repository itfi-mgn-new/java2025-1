package lesson6;

public /*final*/ enum MyEnum /*extends java.lang.Enum*/ {
	MONDAY(false),
	TUESDAY(false),
	WEDNEDSAY(false),
	THURSDAY(false),
	FRIDAY(false),
	SATURDAY(true), /* = new MyEnum(6,"SATURDAY)"*/
	/*public static final MyEnum */SUNDAY(true) /* = new MyEnum(0,"SUNDAY)"*/;
	
	private final boolean	holiday;
	
	private MyEnum(final boolean holiday) {
		this.holiday = holiday;
	}
	
	public boolean isHolyday() {
		return holiday;
	}
	
	
}
