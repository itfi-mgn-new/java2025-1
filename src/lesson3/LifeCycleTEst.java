package lesson3;

public class LifeCycleTEst implements MyInterface {
	public static final int		x = 10;
	public static final double	y = Math.random();
	
	/*
	 * private static void <clinit>() {
	 *   y = Math.random();
	 *   System.err.println("Call!");
	 * }
	 */
	
	static {
		System.err.println("Call!");
	}
	
	public final int	z = 10;
	public final double	t = Math.random();
	public final int	zzz;
	
	{
		System.err.println("Instance call!");
	}
	
	/*
	 * public void <init>() {
	 *    super.<init>();
	 *    z = 10;
	 *    t = Math.random();
	 *    System.err.println("Instance call!");
	 * }
	 */
	
	public LifeCycleTEst(/*this*/) {
		// super.<init>();
		// z = 10;
		// t = Math.random();
		// System.err.println("Instance call!");
		System.err.println("sasassasasassas");
		this.zzz = 0;
	}
	
	public LifeCycleTEst(/*this, */final int zzz) {
		// super.<init>();
		// z = 10;
		// t = Math.random();
		// System.err.println("Instance call!");
		System.err.println("ZZZ="+zzz);
		this.zzz = zzz;
	}
	
	public void print(/*this*/) {
		System.err.println("PRINT"+zzz);
	}

	public static void staticPrint() {
		System.err.println("Static PRINT");
	}
	
	public static void main(String[] args) {
		System.err.println("Hello world!");
		
		LifeCycleTEst	lct = new LifeCycleTEst(20);//LifeCycleTEst.<init>(new, 20); 
		lct.print();	// LifeCycleTEst.print(lct); 
		LifeCycleTEst.staticPrint();
		new LifeCycleTEst(30).print();
		
		lct = null;
	}

	static {
		System.err.println("Call 2!");
	}

}
