package lesson5;

import java.lang.reflect.Field;
import java.util.Arrays;

public class REflectionTEst {
	
	int	field = 10;

	void print() {
		System.err.println("CALL print");
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		test(new REflectionTEst());
		test(new MyClass());
	}

	static void test(final Object x) throws Exception {
		int sum = 0;
		Class	temp = x.getClass();
		while (temp != null) {
			for(Field fld : temp.getDeclaredFields()) {
				if (fld.getType() == int.class) {
					fld.setAccessible(true);
					sum += fld.getInt(x);
				}
			}
			temp = temp.getSuperclass();
		}
		System.err.println("Sum="+sum);
		
		final Class	cl = x.getClass();
		
		System.err.println("name="+cl.getName());
		System.err.println("Methods="+Arrays.toString(cl.getDeclaredMethods()));
		System.err.println("Super="+cl.getSuperclass());
	
		java.lang.reflect.Method m = cl.getDeclaredMethod("print");
		
		System.err.println("Method="+m);
		System.err.println("PArameters="+Arrays.toString(m.getParameters()));
		System.err.println("Returned="+m.getReturnType());
		
		m.setAccessible(true);
		System.err.println("Execute: "+m.invoke(x));
		
		System.err.println("Fields="+Arrays.toString(cl.getDeclaredFields()));

		java.lang.reflect.Field f = cl.getDeclaredField("field");

		System.err.println("Field="+f);
		System.err.println("Type="+f.getType());
		f.setAccessible(true);
		System.err.println("Value="+f.get(x));
	
		f.set(x,  25);
		
		
//		System.err.println("Value="+((REflectionTEst)x).field);
	}
	
}
