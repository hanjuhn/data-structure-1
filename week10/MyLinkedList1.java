package week10;


// 단일 연결 리스트(Singly Linked List)
// String 전용

public class MyLinkedList1 {

	class Node {
		String data; // 노드에 저장되는 데이터
		Node next; // 다음 노드에 대한 참조

		Node(String d) {
			data = d;
			next = null;
		}

		public String toString() {
			return "" + data;
		}
	}

	Node head; // 리스트의 첫 번째 노드

	public MyLinkedList1() {
		head = null;
	}

	// 리스트가 비어있는지 확인
	public boolean isEmpty() {
		return (head==null);
	}

	// 앞에 추가 (기본 삽입 방식)
	public void add(String value) {
		addFirst(value);
//        addLast(value);
	}

	// 리스트 앞에 노드 추가
	private void addFirst(String value) {
		Node newNode = new Node(value);
		newNode.next = head;
		head = newNode;
	}

	// 리스트 끝에 노드 추가
	private void addLast(String value) {
		if (isEmpty()) {
			addFirst(value);
		}
		else {
			Node newNode = new Node(value);
			Node p = head;
			while (p.next != null) {
				p = p.next;
			}
			p.next = newNode;
		}
	}

	// 특정 값이 있는 인덱스를 반환
	public int indexOf(String value) {
		int index = -1;
		Node p = head;
		while (p != null) {
			if (p.data == value) {
				return ++index;
			}
			else {
				index++;
				p = p.next;
			}
		}
		return -1;
	}

	// 지정된 인덱스에 노드 삽입
	public void add(int index, String value) {  // 0 <= index <= size()
		if (checkIndexRange(index)) {  // 0 <= index < size()
			if (index == 0) {
				addFirst(value);
			}
			Node newNode = new Node(value);
			int i = 1;
			Node p = head;  // p != null
			while(p.next != null) {
				if (i == index) {
					newNode.next = p.next;
					p.next = newNode;
					return;
				}
				else {
					i++;
					p = p.next;
				}
			}
		}
		else if (index==size()) {
			addLast(value);
		}
	}

	// 리스트 크기 반환
	private int size() {
		int n = 0;
		Node p = head;
		while (p != null) {
			n++;
			p = p.next;
		}
		return n;
	}

	// 인덱스 유효성 확인
	private boolean checkIndexRange(int index) {
		if (index >= 0 && index<size()) {
			return true;
		}
		else {
			return false;
		}
	}

	// 리스트 전체 비우기
	public void clear() {
		head = null;
	}

	// 값 포함 여부 확인
	public boolean contains(String value) {
		return (indexOf(value) != -1);
	}

	// 인덱스 위치 데이터 반환
	public String get(int index) {
		int i = 0;
		Node p = head;
		while (p != null) {
			if (i == index) {
				return p.data;
			}
			i++;
			p = p.next;
		}
		return null; // null에 해당하는 임의의 정한 값
	}

	// 인덱스 위치의 데이터 수정
	public void set (int index, String value) {
		int i = 0;
		Node p = head;
		while (p != null) {
			if (i == index) {
				p.data = value;
			}
			i++;
			p = p.next;
		}
		// error message
	}

	// 인덱스 위치 노드를 삭제하고 데이터 반환
	public String remove(int index) { // return the value removed
		String ret = null;
		if (checkIndexRange(index)) {
			if (index == 0) {
				ret = removeFirst();
			}
//            else {
//                int i = 1;
//                Node p = head;
//                while (p.next != null) {
//                    if (i == index) {
//                        ret = p.next.data;
//                        p.next = p.next.next;
//                        break;
//                    }
//                }
//            }
			else {
				int i = 1;
				Node p = head;
				Node q = p.next;
				while (q != null) {
					if (i == index) {
						ret = q.data;
						p.next = q.next;
						break;
					}
					i++;
					p = q;
					q = q.next;
				}
			}
		}
		return null;
	}

	// 값으로 노드를 삭제
	public String remove(String value) {
		if (head != null) {
			if (head.data == value) {
				return removeFirst();
			}
			else {
				Node p = head;
				Node q = p.next;
				while (q != null) {
					if (q.data.equals(value)) {  // == -> equals(string은 등호와 equals 둘 다 사용 가능), <> -> compareTo(...)
						p.next = q.next;
						return q.data;
					}
					p = q;
					q = q.next;
				}

			}
		}
		return null;
	}

	// 첫 번째 노드 제거 후 데이터 반환
	private String removeFirst() {
		String ret = null;
		if (head != null) {
			ret = head.data;
			head = head.next;
		}
		return null;
	}

	// 리스트 내용을 문자열로 반환
	public String toString() {
		String str = "";
		Node p = head;
		while (p != null) {
			str = str + p.data+ "  ";
			p = p.next;
		}
		return str;
	}

	// 리스트 출력
	public void showList() {
		System.out.println(toString());
	}


	
	public static void main(String[] args) {
		
		String [] data = {"kim","lee","park","choi","jung","kang","cho","yoon","jang"};
		
		MyLinkedList1 list = new MyLinkedList1();

		for (int i=0;i<data.length; i++) {
			list.add(data[i]);
			list.showList();
		}
		
		System.out.println(list.get(0));
		System.out.println(list.get(5));
		System.out.println(list.get(11));
		
		list.add(0, "Lim");
		list.add(5, "han");
		list.add(list.size(), "oh");
		list.showList();

		System.out.println(list.indexOf("lee"));

		System.out.println(list.remove(0));
		list.showList();
		System.out.println(list.remove(5));
		list.showList();
		System.out.println(list.remove(list.size()-1));
		list.showList();
		
		System.out.println(list.remove("han"));
		list.showList();

		
	}

}
