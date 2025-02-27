package lesson2;

public class ObjectTEst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object	obj = new Object();
		obj.toString();
		obj.getClass();
		obj.equals(null);
		obj.hashCode();

		obj = new int[] {2,3,4};
		obj = 3; // Integer.valueOf(3);
		
		Integer	x = 10,		// Integer.valueOf(10) 
				y = 20, 	// Integer.valueOf(20)
				z = x + y;	// Integer.valueOf(x.intValue()+y.intValue())
		obj = x;
		// boolean	Boolean		extends Object
		// char		Character	extends	Object
		//			Number		extends Object
		// byte		Byte		extends Number	
		// short	Short
		// int		Integer
		// long		Long
		// float	Float
		// double	Double
		print("sdds");
		print(3);
	}

	static void print(Object z) {
		System.err.println("z="+z);
	}
}
