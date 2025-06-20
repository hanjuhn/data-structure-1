package week10;

// 단일 연결 리스트(Singly Linked List)
// 제네릭 기반이라서 어떤 타입(T)이든 저장 가능

public class MyLinkedList2<T> {

	class Node {
		T data;
		Node next;

		Node(T d) {
			data = d;
			next = null;
		}

		public String toString() {
			return "" + data.toString();
		}
	}

	Node head;

	public MyLinkedList2() {
		head = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void add(T value) {
		addFirst(value);  // addLast(value)도 가능
	}

	private void addFirst(T value) {
		Node newNode = new Node(value);
		newNode.next = head;
		head = newNode;
	}

	private void addLast(T value) {
		if (isEmpty()) {
			addFirst(value);
		} else {
			Node newNode = new Node(value);
			Node p = head;
			while (p.next != null) {
				p = p.next;
			}
			p.next = newNode;
		}
	}

	public int indexOf(T value) {
		int index = 0;
		Node p = head;
		while (p != null) {
			if (p.data.equals(value)) {
				return index;
			}
			p = p.next;
			index++;
		}
		return -1;
	}

	public void add(int index, T value) {  // 0 <= index <= size()
		if (index < 0 || index > size()) return;

		if (index == 0) {
			addFirst(value);
			return;
		}

		Node newNode = new Node(value);
		Node p = head;
		for (int i = 0; i < index - 1; i++) {
			p = p.next;
		}
		newNode.next = p.next;
		p.next = newNode;
	}

	public int size() {
		int n = 0;
		Node p = head;
		while (p != null) {
			n++;
			p = p.next;
		}
		return n;
	}

	public void clear() {
		head = null;
	}

	public boolean contains(T value) {
		return (indexOf(value) != -1);
	}

	public T get(int index) {
		if (index < 0 || index >= size()) return null;

		Node p = head;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.data;
	}

	public void set(int index, T value) {
		if (index < 0 || index >= size()) return;

		Node p = head;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		p.data = value;
	}

	public T remove(int index) {
		if (index < 0 || index >= size()) return null;

		if (index == 0) {
			return removeFirst();
		}

		Node p = head;
		for (int i = 0; i < index - 1; i++) {
			p = p.next;
		}
		T ret = p.next.data;
		p.next = p.next.next;
		return ret;
	}

	public T remove(T value) {
		if (head == null) return null;

		if (head.data.equals(value)) {
			return removeFirst();
		}

		Node p = head;
		while (p.next != null) {
			if (p.next.data.equals(value)) {
				T ret = p.next.data;
				p.next = p.next.next;
				return ret;
			}
			p = p.next;
		}
		return null;
	}

	private T removeFirst() {
		if (head == null) return null;
		T ret = head.data;
		head = head.next;
		return ret;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		Node p = head;
		while (p != null) {
			str.append(p.data.toString()).append("  ");
			p = p.next;
		}
		return str.toString();
	}

	public void showList() {
		System.out.println(toString());
	}

	public static void main(String[] args) {

		Object[][] data = {
				{1, "kim"}, {2, "lee"}, {3, "park"}, {4, "choi"}, {5, "jung"},
				{6, "kang"}, {7, "cho"}, {8, "yoon"}, {9, "jang"}
		};

		MyLinkedList2<MyData> list = new MyLinkedList2<>();

		for (int i = 0; i < data.length; i++) {
			list.add(new MyData((int) data[i][0], (String) data[i][1]));
			list.showList();
		}

		System.out.println(list.get(0));
		System.out.println(list.get(5));
		System.out.println(list.get(8));

		list.add(0, new MyData(10, "Lim"));
		list.add(5, new MyData(11, "han"));
		list.add(list.size(), new MyData(12, "oh"));
		list.showList();

		System.out.println(list.indexOf(new MyData(2, "lee")));

		System.out.println("remove(0): " + list.remove(0));
		list.showList();
		System.out.println("remove(5): " + list.remove(5));
		list.showList();
		System.out.println("remove(last): " + list.remove(list.size() - 1));
		list.showList();

		System.out.println("indexOf(11 han): " + list.indexOf(new MyData(11, "han")));
		System.out.println("remove(11 han): " + list.remove(new MyData(11, "han")));
		list.showList();
	}
}