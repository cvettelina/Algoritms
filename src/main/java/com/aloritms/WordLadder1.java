package com.aloritms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Given two words (start and end), and a dictionary, find the length of
 * shortest transformation sequence from start to end, such that only one letter
 * can be changed at a time and each intermediate word must exist in the
 * dictionary. For example, given:
 * 
 * start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"] One shortest
 * transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", the program
 * should return its length 5.
 * 
 * @author Cvettelina
 *
 */
public class WordLadder1 {

	public static void main(String[] args) {
		String start = "hit";
		String end = "cog";
		String[] dictionary = { "hot", "dot", "dog", "lot", "log" };
		Node node = createGraph(start, end, dictionary);

		// BFS
		System.out.println(findPath(node, end));

		// Proposed solution
		System.out.println(ladderLength(start, end, dictionary));

		// DFS
		node = createGraph(start, end, dictionary);
		System.out.println(findPathDFS(node, end));

	}

	/**
	 * create a structure from the dictionary
	 * 
	 * @param start
	 * @param end
	 * @param dictionary
	 * @return
	 */
	private static Node createGraph(String start, String end,
			String[] dictionary) {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node(start));
		for (int i = 0; i < dictionary.length; i++) {
			nodes.add(new Node(dictionary[i]));
		}
		nodes.add(new Node(end));
		for (int i = 0; i < nodes.size(); i++) {
			for (int k = 0; k < nodes.size(); k++) {
				int dif = 0;

				for (int j = 0; j < nodes.get(i).getWord().length(); j++) {
					if (nodes.get(i).getWord().charAt(j) != nodes.get(k)
							.getWord().charAt(j)) {
						dif++;
					}
				}
				if (dif == 1) {
					nodes.get(i).getChildren().add(nodes.get(k));
				}
			}
		}

		return nodes.get(0);
	}

	/**
	 * Find path by BFS on the created graph
	 * 
	 * @param node
	 * @param end
	 * @return
	 */
	private static int findPath(Node node, String end) {
		Queue<Node> queue = new LinkedList<Node>();
		Node currentNode = node;
		int count = 1;
		queue.add(currentNode);
		while (!queue.isEmpty()) {
			currentNode = queue.poll();
			currentNode.setVisited(true);

			for (Node child : currentNode.getChildren()) {
				if (!child.isVisited()) {
					if (child.getWord().equals(end)) {
						return count;
					}
					child.setVisited(true);
					queue.add(child);

				}
			}
			count++;
		}
		return count;
	}

	private static int findPathDFS(Node node, String end) {
		Stack<Node> stack = new Stack<Node>();
		Node currentNode = node;
		int count = 1;
		stack.push(currentNode);
		while (!stack.isEmpty()) {
			currentNode = stack.pop();
			currentNode.setVisited(true);

			for (Node child : currentNode.getChildren()) {
				if (!child.isVisited()) {
					System.out.println(currentNode.getWord() + "child "
							+ child.getWord());
					if (child.getWord().equals(end)) {
						return count;
					}
					child.setVisited(true);
					stack.push(child);
					break;
				}
			}
			count++;
		}
		return count;
	}

	public static int ladderLength(String beginWord, String endWord,
			String[] dictionary) {
		LinkedList<WordNode> queue = new LinkedList<WordNode>();
		queue.add(new WordNode(beginWord, 1));
		Set<String> wordDict = new HashSet<String>();
		for (int i = 0; i < dictionary.length; i++) {
			wordDict.add(dictionary[i]);
		}
		wordDict.add(endWord);

		while (!queue.isEmpty()) {
			WordNode top = queue.remove();
			String word = top.word;

			if (word.equals(endWord)) {
				return top.numSteps;
			}

			char[] arr = word.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char temp = arr[i];
					if (arr[i] != c) {
						arr[i] = c;
					}

					String newWord = new String(arr);
					if (wordDict.contains(newWord)) {
						queue.add(new WordNode(newWord, top.numSteps + 1));
						wordDict.remove(newWord);
					}

					arr[i] = temp;
				}
			}
		}

		return 0;
	}

	public static class WordNode {
		String word;
		int numSteps;

		public WordNode(String word, int numSteps) {
			this.word = word;
			this.numSteps = numSteps;
		}
	}

	static class Node {
		private String word;
		private boolean visited;
		private List<Node> children;

		int numSteps;

		public Node(String word, int numSteps) {
			this.word = word;
			this.numSteps = numSteps;
		}

		public Node(String word) {
			this.word = word;
			this.children = new ArrayList<WordLadder1.Node>();
			this.visited = false;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

		public List<Node> getChildren() {
			return children;
		}

		public void setChildren(List<Node> children) {
			this.children = children;
		}

	}

}
