package homework2;
public class MyLinked {
	static class Node {
		public Node() { }
		public double item;
		public Node next;
	}

	int N;
	Node first;

	public MyLinked () {
		first = null;
		N = 0;
		assert checkInvariants ();
	}


	private boolean checkInvariants() {
		assert((N != 0) || (first == null));
		Node x = first;
		for (int i = 0; i < N; i++) {
			if (x==null) {
				return false;
			}
			x = x.next;
		}
		assert(x == null);
		return true;
	}

	public boolean isEmpty () { return first == null; }
	public int size () { return N; }
	public void add (double item) {
		Node newfirst = new Node ();
		newfirst.item = item;
		newfirst.next = first;
		first = newfirst;
		N++;
	}


	// delete the kth element
	public void delete (int k) {
		if (k < 0 || k >= N) throw new IllegalArgumentException ();
		// TODO 1.3.20
		Node x = first;
		if (k == 0) {
			first = x.next;
			N--;
			return;
		} 
		for (int i = 0; i < k-1 && x.next != null; i++) {
			x = x.next;
		}
		Node next = x.next.next;
		x.next=next;
		N--;
		assert(checkInvariants ());
	}

	// reverse the list "in place"... without creating any new nodes
	public void reverse () {
		Node currNode = first;
		Node nextNode = null;
		Node prevNode = null;

		while(currNode!=null){
			nextNode = currNode.next;
			currNode.next = prevNode;
			prevNode = currNode;
			currNode = nextNode;
		}
		first = prevNode;

		assert(checkInvariants ());
	}


	// remove 
	public void remove (double item) {
		if (N == 1 && first.item == item) {
			first = null;
			N = 0;
		} else if (N > 1){
			while (N > 1 && first.item == item) {
				first = first.next;
				N--;
			}
			if (N == 1 && first.item == item) {
				first = null;
				N = 0;
			}else {
				Node oldNode = first;
				Node currentNode = oldNode.next; 
				int counter = 0;
				for (int i = 0; i < N-1; i++){
					if (currentNode.item == item){
						oldNode.next = currentNode.next;
						counter++;
					} else {   
						oldNode = currentNode;
					}
					currentNode = currentNode.next;
				}
				N = N - counter;
			}
		}

		assert(checkInvariants ());

	}
}