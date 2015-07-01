package com.aloritms;

/**
 * Rotate an array of n elements to the right by k steps. For example, with n =
 * 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. How
 * many different ways do you know to solve this problem?
 * 
 * @author Cvettelina
 *
 */
public class RotateArray {

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };

		System.out.println("Algoritm 1:" + rotate1(array, 2));
		System.out.println("Algoritm 2:" + rotate2(array, 2));
		System.out.println("Algoritm 2:" + rotate3(array, 2));

	}

	/**
	 * My implementation of the algoritm.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	private static String rotate1(int[] array, int k) {
		int[] newArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			if (i < k) {
				newArray[i] = array[array.length - k + i];
			} else {
				newArray[i] = array[i - k];
			}
		}
		String result = new String();
		for (int i = 0; i < array.length; i++) {
			result = result.concat(" " + newArray[i]);
		}
		return result;
	}

	private static String rotate2(int[] array, int k) {
		int[] result = new int[array.length];

		for (int i = 0; i < k; i++) {
			result[i] = array[array.length - k + i];
		}

		int j = 0;
		for (int i = k; i < array.length; i++) {
			result[i] = array[j];
			j++;
		}

		String resultString = new String();
		for (int i = 0; i < array.length; i++) {
			resultString = resultString.concat(" " + result[i]);
		}
		return resultString;
	}

	private static String rotate3(int[] array, int k) {
		for (int i = 0; i < k; i++) {
			for (int j = array.length - 1; j > 0; j--) {
				int temp = array[j];
				array[j] = array[j - 1];
				array[j - 1] = temp;
			}
		}

		String resultString = new String();
		for (int i = 0; i < array.length; i++) {
			resultString = resultString.concat(" " + array[i]);
		}
		return resultString;
	}
}
