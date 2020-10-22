package models;

public class LinkedList {
	
	private Link firstLink; //always the dummy
	private Link lastLink;
	private int numElements;
	private int maxSize;
	
	public LinkedList() {
	
		Link dummyLink = new Link(null);
		this.firstLink = dummyLink;
		this.lastLink = dummyLink; //lastLink = firstLink; 
		this.numElements = 0;
		this.maxSize = 7;
	}
	
	public int size() {
		return this.numElements;
	}
	
	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public void append(String data) {
		
		if (numElements < maxSize) {
			Link newLink = new Link(data);
			this.lastLink.next = newLink;
			this.lastLink = newLink;
			this.numElements++;
		}
		
	}
	public void prepend (String data) {
		
		if (numElements < maxSize) {
			Link newLink = new Link(data);
			if (firstLink.next == null) {
				lastLink = newLink;
			}
			newLink.next = this.firstLink.next;
			this.firstLink.next = newLink;
			this.numElements++;
		}
	}
	
	public boolean equals (Object other) {
		if (other == null || getClass() != other.getClass() || this.numElements != ((LinkedList) other).numElements)
			return false;

		Link thisLink = this.firstLink;
		Link otherLink = ((LinkedList) other).firstLink;
		while (thisLink != null) {
			if (thisLink.data != otherLink.data)
				return false;
			thisLink = thisLink.next;
			otherLink = otherLink.next;
		} 

		return true;
	}
	public Link pop() {
		if (numElements > 0) {
			Link link = firstLink.next;
			this.firstLink.next = link.next; //this.firstLink.next = this.firstLink.next.next; 
			this.numElements--;
			if (numElements == 0) {
				this.lastLink = this.firstLink;
			}
			return link;
		}
		return null;
	}
	
	public Link[] toArray() {
		Link[] arr = new Link[numElements + 1];
		Link temp = this.firstLink;
		int counter = 0;
		while (temp != null) {
			arr[counter++] = temp;
			temp = temp.next;
		}
		return arr;
	}

	public Link getFirstLink() {
		return firstLink;
	}

	public Link getLastLink() {
		return lastLink;
	}

	public int getNumElements() {
		return numElements;
	}

	
}
