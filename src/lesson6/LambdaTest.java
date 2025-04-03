package lesson6;

import java.util.Arrays;

public class LambdaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		call(new MyInterface() {
			@Override
			public void print() {
				System.err.println(";ldlfjkldfsfgjkldsjg");
			}
		});
		MyInterface	mi = LambdaTest::vassya;/*
							new MyInterface() {
								public void print() {
									vassya();
								}
							}
		*/
		Class	cl = mi.getClass();
		
		System.err.println("Name="+cl.getName());
		System.err.println("Name 2="+cl.getCanonicalName());
		call(mi);
		
		call(/*test*/(/*int*/ x)->vassya()/*LambdaTest::lambda$1*/);/*
				new MyInterface() {
				public void print() {
					lambda$1();
				}
			}
		*/
		Class	cl2 = LambdaTest.class;
		
		System.err.println("MEthods="+Arrays.toString(cl2.getDeclaredMethods()));
		
	}

	/* 
	 private static void lambda$1(){
	 	vassya();
	 }
	 * */
	

	static void call(final MyInterface mi) {
		mi.print();
	}
	
	static void vassya() {
		System.err.println("vassya");
	}
}
