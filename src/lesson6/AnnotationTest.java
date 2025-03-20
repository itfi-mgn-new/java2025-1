package lesson6;

@MyAnnotation(value="dd",depth=2,name="petya")
public class AnnotationTest {
	public static final int 	x = 10;
	
	@MyAnnotation("ssss")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class	cl = AnnotationTest.class;
		
		MyAnnotation	ma = (MyAnnotation)cl.getDeclaredAnnotation(MyAnnotation.class);
		System.err.println("Depth: "+ma.depth());
		System.err.println("Name: "+ma.name());


	}

}


