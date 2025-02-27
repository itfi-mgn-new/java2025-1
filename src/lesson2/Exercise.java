package lesson2;

public class Exercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int[]	arr = new int[] {2,5,-3,11,7,4,9};
		
		// 1. Найти максимальный элемент в массиве. 
		// 2. Подсчитать среднее значение по массиву.
		// 3. Переставить местами элементы: {9,4,7,11,-3,5,2}.
		int x = 10, y = 20;
		
		x ^= y; y ^= x; x ^= y;
		System.err.println("x="+x+",y="+y);
		
	}

}
