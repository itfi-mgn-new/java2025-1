package lesson8;

import java.util.Arrays;

public class WrapperTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print(/*new Object[] {*/new WrapperTest(), /*new Integer(*/1/*)*/, new int[] {2,3,4}/*}*/);
	
		Integer	y = /*Integer.valueOf(*/10/*)*/;
		int z = y/*.intValue()*/;
		Object	x = /* Integer.valueOf(
						/*Integer.valueOf(*/10/*).intvalue()*/ 
						+ z 
						- y/*.intValue()*/;
					/*)*/
		x = Integer.valueOf("123");
				
	}

	static void print(final Object... list) {
		System.err.println("Content: "+Arrays.toString(list));
		print1(list);
	}

	static void print1(final Object[] list) {
	}
}
