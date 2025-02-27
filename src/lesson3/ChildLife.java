package lesson3;

public class ChildLife extends LifeCycleTEst {
	public static final int		x = 100;
	public static final double	y = 100+Math.random();
	
	static {
		System.err.println("Child call!");
	}
	
	public final int	z = 200;
	
	{
		System.err.println("Child INSTANCE call!");
	}
	
	/*
	 * public void <init>() {
	 * 	super.<init>();
	 *  z = 200;
	 *  System.err.println("Child INSTANCE call!");
	 *  }
	 */
	
	public ChildLife() {
		this(123);
		System.err.println("QQQQQQQQQQQQQQQQ call!");
	}

	public ChildLife(int zzz) {
		super(zzz);
		System.err.println("QQQQQQQQQQQQQQQQ call! ZZZ="+zzz);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.err.println("Hello child!");

		ChildLife	cl = new ChildLife(300);
		System.err.println("X="+LifeCycleTEst.x);
		System.err.println("Z="+((LifeCycleTEst)cl).z);
		
		ChildLife.staticPrint();
		cl.print();

		LifeCycleTEst.staticPrint();
		((LifeCycleTEst)cl).print();
	}

	public void print(/*this*/) {
		super.print();
		System.err.println("Child PRINT"+zzz);
	}

	
	public void www() {
	}
	
	public static void staticPrint() {
		System.err.println("Child Static PRINT");
	}
	
	//						this		VMT
	// invokestatic			-			-
	// invokevirtual		+			+
	// invokespecial		+			-
	// invokeinterface		+			+
	// 
}
