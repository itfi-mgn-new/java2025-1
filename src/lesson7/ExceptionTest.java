package lesson7;

import java.io.IOException;

import javax.management.JMRuntimeException;

public class ExceptionTest {
	static void error(int type) throws RuntimeException, Exception, Throwable {
		switch (type) {
			case 0: throw new RuntimeException("Runtime !!!");
			case 1: throw new Exception("Exception !!!");
			case 2: throw new Throwable("Throwable !!!");
			default :
		}
	}
	
	static void nestedError(int type) throws Throwable {
		try {
			error(type);
		} catch (Exception e) {
			System.err.println("NESTED EXCEPTION!!!");
			e.printStackTrace();
			throw new IOException("CAUSE!!!", e);
		} finally {
			System.err.println("NESTED finally");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Throwable	t = new Throwable("This is a problem!");
//		System.err.println("text: "+t.getMessage());
//		t.printStackTrace();
//		System.err.println("text: "+t.getCause());
	
		for(int index = 0; index <= 3; index++) {
			try {
				nestedError(index);
				System.err.println("Success");
			} catch (RuntimeException e) {
				System.err.println("AAAAA!");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("BBBBB!");
				e.printStackTrace();
			} catch (Throwable e) {
				System.err.println("CCCCC!");
				e.printStackTrace();
			} finally {
				System.err.println("FINALLY");
			}
		}
		
		
//		throw t;
//		throw new RuntimeException("dsf;kdsf;lk");
	}

}
