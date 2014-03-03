// Created by Luzius on Mar 3, 2014

package ch.fhnw.algd2.lesson3.exercise;

public abstract class Node {

	private Node[] neighbors;
	private ThreadLocal marker = new ThreadLocal<>();

	/**
	 * Sets the marker. This can be any kind of object used by an external application.
	 * It is not used by the node itself. The idea is that this could be used to mark nodes
	 * as traversed or store other forms of external state.
	 */
	public final void setMarker(Object o){
		if (this.marker == null){
			this.marker = new ThreadLocal();
		}
		this.marker.set(o);
	}

	/**
	 * Returns the marker (if any).
	 */
	public Object getMarker(){
		return marker.get();
	}
	
	/**
	 * Returns all the neighbors of this node.
	 * You can assume that the graph is bi-directional. That means that if node A has B in its
	 * neighbors list, then node B also has A in its list.
	 * Children and parent nodes are not distinguished.
	 */
	public Node[] getNeighbors(){
		return neighbors;
	}
	
}
