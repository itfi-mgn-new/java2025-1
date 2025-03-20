package lesson6;

import java.util.Arrays;

public class AnonClassTest {
	private static int x = 10;
	private int y = 20;

	public void call() {
		int	z = 30;

		MyInterface	mi = new MyInterface() {
			int	t = 40;
			{
				System.err.println("sdfkjsdfkljslkdfjlksdfj");

			}
			@Override
			public void print() {
				System.err.println("AAAA@@@@@"+x+/*AnonClassTest.this.*/y+z+t);
			}
		};
		Class	cl = mi.getClass();
		
		System.err.println("Name="+cl.getName());
		System.err.println("Name 2 ="+cl.getCanonicalName());

		System.err.println("Consructors="+Arrays.toString(cl.getDeclaredConstructors()));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new AnonClassTest().call();
		
		int	z = 30;
		
		MyInterface	mi = new MyInterface() {
							int	t = 40;
							{
								System.err.println("sdfkjsdfkljslkdfjlksdfj");

							}
							@Override
							public void print() {
								System.err.println("AAAA@@@@@"+x+/*y+*/z+t);
							}
						};
		Class	cl = mi.getClass();
		
		System.err.println("Name="+cl.getName());
		System.err.println("Name 2 ="+cl.getCanonicalName());

		System.err.println("Consructors="+Arrays.toString(cl.getDeclaredConstructors()));
		System.err.println("Fields="+Arrays.toString(cl.getDeclaredFields()));
		
		MyInterface	mi2 = new MyInterface() {
					int	t = 40;
					@Override
					public void print() {
						System.err.println("AAAA@@@@@"+x+/*y+*/z+t);
					}
				};
		Class	cl2 = mi2.getClass();
		System.err.println("Name="+cl2.getName());
	
		Object zx = new Object() {{
					System.err.println("ssssssssss");
				}};
		Class	cl3 = zx.getClass();
		System.err.println("Name="+cl3.getName());
	}

}

@FunctionalInterface
interface MyInterface {
	void print();
}
