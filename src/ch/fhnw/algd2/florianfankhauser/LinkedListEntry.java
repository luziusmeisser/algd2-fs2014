// Created by Florian Fankhauser on Feb 17, 2014

package ch.fhnw.algd2.florianfankhauser;

public class LinkedListEntry<X> {
	protected X value;
	protected LinkedListEntry<X> next;

	protected LinkedListEntry(X value) {
		this.value = value;
	}

	protected boolean hasNext() {
		return next != null;
	}
}
