package com.aloritms;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 * 
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 * @author Cvettelina
 *
 */
public class EvaluateReversePolishNotation {

	private static final String DIGIT_PATTERN = "\\d+";

	public static void main(String[] args) {
		String[] array1 = { "2", "1", "+", "3", "*" };
		String[] array2 = { "4", "13", "5", "/", "+" };
		System.out.println(calculate(array1));
		System.out.println(calculate(array2));
	}

	/**
	 * My implementation with stack
	 * 
	 * @param array
	 * @return
	 */
	private static int calculate(String[] array) {
		int result = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (array[i].matches(DIGIT_PATTERN)) {
				stack.push(Integer.valueOf(array[i]));
			} else {
				Integer value1 = stack.pop();
				Integer value2 = stack.pop();

				switch (array[i]) {
				case "-":
					result = value2 - value1;
					break;
				case "+":
					result = value2 + value1;
					break;
				case "*":
					result = value2 * value1;
					break;
				case "/":
					result = value2 / value1;
					break;
				}
				stack.push(result);
			}
		}
		return result;
	}

}
