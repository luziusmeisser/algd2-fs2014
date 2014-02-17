package ch.fhnw.algd2.martineberle;

public class Node<T> {
T val;
Node<T> next;

	public Node(){
		this.val = null;
		this.next = null;
	}
	
	@SuppressWarnings("unchecked")
	public Node(Object o){
		this.val = (T) o;
	}
}
