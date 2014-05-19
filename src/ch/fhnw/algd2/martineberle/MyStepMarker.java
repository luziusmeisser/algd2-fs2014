//Copied by Martin Eberle on 15.05.2014 

package ch.fhnw.algd2.martineberle;

import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.tanks.EOrientation;

public class MyStepMarker implements Comparable<MyStepMarker> {

		private Node node;
		private Node origin;
		private Node next;
		private int totWeight;

		public MyStepMarker(Node node) {
			this(null, node, 0);
		}

		public MyStepMarker(Node origin, Node node, int i) {
			this.origin = origin;
			this.node = node;
			this.totWeight = i;
			this.node.setMarker(this);
		}

		public Node getNode() {
			return node;
		}
		
		public void setNext(Node next){
			this.node = next;
		}
		
		public EOrientation getDirection() {
			return node.getDirection(next);
		}
		@Override
		public int compareTo(MyStepMarker o) {
			return Integer.compare(totWeight, o.totWeight);
		}

		public boolean isFirst() {
			return origin == null;
		}

		public Node getFirstStep() {
			if (isFirst()){
				return null;
			} else {
				MyStepMarker prev = (MyStepMarker) origin.getMarker();
				return prev.isFirst() ? node : prev.getFirstStep();
			}
		}

		public boolean isCurrent() {
			return node.getMarker() == this;
		}

		public int getWeight() {
			return totWeight;
		}

		public void fillPath() {
			if (!isFirst()){
				MyStepMarker prev = (MyStepMarker) origin.getMarker();
				prev.next = node;
				prev.fillPath();
			}
		}
		public void createPath() {
			while(!isFirst()){
				
			}
		}
}
