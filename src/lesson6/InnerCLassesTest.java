package lesson6;

import java.util.Arrays;

public class InnerCLassesTest {
	public static int	x = 10;
	public int			y = 20;

	public void call() {
		NestedClass ccc = /*this.*/new NestedClass();
	}
	
	public class NestedClass {
		public static int 	z = 30;
		public int 			y = 40;
		public int			t = 40;
		
		public void print() {
			int	a = 100;
			System.err.println("sum="+InnerCLassesTest.x+InnerCLassesTest.this.y+NestedClass.z+this.t+a);
		}
	}
	
	public static void main(String[] args) {
		System.err.print("Hello world");
		Class	cl = NestedClass.class;
		
		System.err.println("Name="+cl.getName());
		System.err.println("Name="+cl.getCanonicalName());
		
		System.err.println("Constructors="+Arrays.toString(cl.getConstructors()));
		System.err.println("Fields="+Arrays.toString(cl.getDeclaredFields()));
		
		InnerCLassesTest	owner = new InnerCLassesTest();
		
		NestedClass	item = owner.new NestedClass();
	}
}
