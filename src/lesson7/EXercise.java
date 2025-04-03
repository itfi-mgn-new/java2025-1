package lesson7;

public class EXercise {
	public static void main(String[] args) {
		// System.err.println(isEquals(inner class, 100))
		// System.err.println(isEquals(static inner class, 200))
		// System.err.println(isEquals(anonymous class, 300))
		// System.err.println(isEquals(local class, 400))
		// System.err.println(isEquals(Test.test(int x)->{return x % 2 == 0;} lambda, 500))
		// (x)->x % 2 == 0 =====  Test.test(int x)->{return x % 2 == 0;}
		System.err.println(isEquals((x)->x % 2 == 0, 500));
		
	}

	public static boolean isEquals(Test getter, int value) {
		return getter.test(value);
	}
}

interface Test {
	/**
	 * <p>TEst value is "equals" </p>
	 * @param value value to test
	 * @return true if value is "equals", false otherwise
	 */
	boolean test(int value);
}