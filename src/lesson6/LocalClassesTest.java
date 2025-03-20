package lesson6;

import java.util.Arrays;

public class LocalClassesTest {
	static int	a = 10;
	int	b = 20;

	void call() {
		int	t = 200;

		class Inner {
			static int	z = (int)Math.random();
			int	x = 100;
			
			public void print() {
				System.err.println("sum"+x+z+t+a+b);
			}
		}
		Inner	q = new Inner();
		Class	cl = q.getClass();
		
		System.err.println("Name="+cl.getName());
		System.err.println("Name 2 ="+cl.getCanonicalName());
		System.err.println("Constructors="+Arrays.toString(cl.getDeclaredConstructors()));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int	t = 200;

		/*static*/ class Inner {
			static int	z = (int)Math.random();
			int	x = 100;
			
			public void print() {
				System.err.println("sum"+x+z+t+a);
			}
		}
		Inner	q = new Inner();
		Class	cl = q.getClass();
		
		System.err.println("Name="+cl.getName());
		System.err.println("Name 2 ="+cl.getCanonicalName());
		System.err.println("Constructors="+Arrays.toString(cl.getDeclaredConstructors()));

		new LocalClassesTest().call(); 
	}

}
