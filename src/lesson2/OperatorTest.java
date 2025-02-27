package lesson2;

public class OperatorTest {
	static final int value = 10;

	public static int main = 10;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int	x = 10, y = 0;

		x = 20;
		x += 10;	// x = x + 10;
		
		if (x == 0 && y < 0) {
			System.err.println("X > 0");
			
		} else {
			System.err.println("X <= 0"); 
		}	// end
	
		while (x > 0) {
			x--;	// X = X - 1; 
		}
		
		do {
			x--;
		} while (x > 0);
		
		int[]	zzz = new int[]{1,2,3};
		
loop1:	for(int i = 0; i < 10; i++) {
			System.err.println("i="+i);
			zzz[i] ++;
			for(int j = 0; j < 10; j++) {
				break loop1;	// goto L;
			}
			//continue;	goto C;
		}
//L :
		/*
		 * int i = 0;
		 * while (i < 10) {
		 * 		System.err.println("i="+i);
//C:	 * 		i++;
		 * }
		 */
		
		for (int v : zzz) {
			System.err.println("V="+v);
		}
		
		x = 0;
		switch (x) {
			case 0 :
//				System.err.println("zero");
//				break;
			case 1 :
				System.err.println("one");
				break;
			case value :
				////
				break;
			default :
				System.err.println("default");
		}
		
		return;
	}

}
