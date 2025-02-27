package lesson3;

public class InterfaceTEst implements MyInterface {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyInterface mi = new ChildLife();
	}

	@Override
	public void print() {
		System.err.println("lkgfjjklfjfgf");
	}
	
	public void qqq() {
		
	}

}

interface MyInterface {
	/* public abstract */ void print();	
	default void aaa() {
		
	}
	
	static void of(int x) {
		
		
	}
	
	private void rrr() {
		
	}
}

interface MyInterface2 {
	/* public abstract */ void print();
	void zzz();
}

interface MyInterface3 extends MyInterface, MyInterface2 {
	/*
	 * void print();
	 * void zzz();
	 * default void aaa() {
		}
	 * 
	 */
}
