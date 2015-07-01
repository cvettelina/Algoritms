package com.aloritms;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic. Two strings are
 * isomorphic if the characters in s can be replaced to get t.
 * 
 * For example,"egg" and "add" are isomorphic, "foo" and "bar" are not.
 * 
 * @author Cvettelina
 *
 */
public class IsomorphicStrings {

	public static void main(String[] args) {
		String one = "bcf";
		String two = "abc";

		System.out.println(isIsomorphic(one, two));

	}

	private static boolean isIsomorphic(String one, String two) {
		if (one.length() != two.length()) {
			return false;
		}
		if (one.equals(two)) {
			return true;
		}

		Map<Character, Character> map = new HashMap<Character, Character>();
		for (int i = 0; i < one.length(); i++) {
			if (map.containsKey(one.charAt(i))) {
				if (two.charAt(i) != map.get(one.charAt(i))) {
					return false;
				}
			}
			map.put(one.charAt(i), two.charAt(i));
		}

		return true;
	}

}
