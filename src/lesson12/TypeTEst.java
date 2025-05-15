package lesson12;

public class TypeTEst<T extends Number,XZ> {
	public T	value;
	public XZ	value2;
//	public T 	stat;
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		// TODO Auto-generated method stub
		Class	cl = TypeTEst.class;
		
		TypeTEst<Integer, String>	i1= new TypeTEst<>();
//		TypeTEst<String, String>	i2= new TypeTEst<>();
		
		System.err.println("Value: "+cl.getField("value"));
		System.err.println("Value: "+ZZZ.class.getField("value"));
		System.err.println("Value: "+ZZZ1.class.getField("value"));
		
//		ZZZ<Double> z = new ZZZ<>();
	}

	public T add(T val1, T val2) {
		return (T)Integer.valueOf((val1.intValue()+val2.intValue()));
	}
	
	public static <T extends Number,QQQQ> T getValue(Class<T> awaited) {
		return (T)null;
	}
}

class ZZZ extends TypeTEst<Integer, String> {
	// skldkvfjklksdfjklsdjjflkjsdklfkjlkj
}

class ZZZ1<S> extends TypeTEst<Integer, S> {
	// skldkvfjklksdfjklsdjjflkjsdklfkjlkj
}

