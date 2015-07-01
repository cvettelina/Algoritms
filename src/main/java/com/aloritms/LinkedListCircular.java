package com.aloritms;

/**
 * Suppose that you are given a linked list that is either circular or or not
 * circular (another word for not circular is acyclic).
 * 
 * Write a function that takes as an input a pointer to the head of a linked
 * list and determines whether the list is circular or if the list has an ending
 * node. If the linked list is circular then your function should return true,
 * otherwise your function should return false if the linked list is not
 * circular. You can not modify the linked list in any way.
 * 
 * @author Cvettelina
 *
 */
public class LinkedListCircular {

	public static void main(String[] args) {

		System.out.println("Welcome");
		Node sixthNode = new Node("Six");
		Node fifthNode = new Node("Five", sixthNode);
		Node forthNode = new Node("Four", fifthNode);
		Node thirdNode = new Node("Three", forthNode);
		Node secondNode = new Node("Two", thirdNode);
		Node firstNode = new Node("One", secondNode);

		System.out.println("\n Acylic");
		System.out.println("Custom implementation result: " + isCircular(firstNode));
		System.out.println("Floyd implementation result: " + isCircular2(firstNode));

		// Make the list circular
		sixthNode.setNext(forthNode);
        System.out.println("\n Circular");
		System.out.println("Custom implementation result: " + isCircular(firstNode));
		System.out.println("Floyd implementation result: " + isCircular2(firstNode));

	}

	/**
	 * Returns true if the linked list is cyclic and false if not. Goes through
	 * all elements of the list and searches if there are two elements pointing
	 * to one and the same element
	 * 
	 * @param firstNode
	 * @return
	 */
	private static boolean isCircular(Node firstNode) {
		Node current = firstNode;
		while (current.getNext() != null) {
			// if the last element is pointing to the first element return true
			if (current.getNext().equals(firstNode)) {
				return true;
			}

			// always start the second loop from the first element
			Node tmp = firstNode;
			while (tmp.getNext() != null) {
				// break if the current element is reached to avoid infinite
				// loop
				if (tmp.equals(current)) {
					break;
				}

				// return true if there are two elements pointing to the same
				// element
				if (current.getNext().equals(tmp.getNext())) {
					return true;
				}
				tmp = tmp.getNext();
			}
			current = current.getNext();
		}
		return false;
	}

	/**
	 * Returns true if the linked list is cyclic and false if not. Implemented
	 * with Floyd algoritm. There is one slow and one fast pointer. If they
	 * overlap then the list is cyclic
	 * 
	 * @param firstNode
	 * @return
	 */
	private static boolean isCircular2(Node firstNode) {
		Node slow = firstNode;
		Node fast = firstNode;

		if (firstNode == null) {
			return false;
		}
		while (fast != null && fast.getNext() != null) {
			fast = fast.getNext().getNext();
			slow = slow.getNext();
			if (slow.equals(fast)) {
				return true;
			}
		}

		return false;
	}
}

class Node {
	private String param;
	private Node next;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node(String param, Node next) {
		this.param = param;
		this.next = next;
	}

	public Node(String param) {
		this.param = param;
		this.next = null;
	}

}
