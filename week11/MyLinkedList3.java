package week11;

// 이중 연결 리스트
// 제네릭 기반이라서 어떤 타입(T)이든 저장 가능

public class MyLinkedList3<T> {

	// 내부 클래스: 리스트의 각 노드를 나타냄
	public class Node {
		T data; // 노드에 저장된 데이터
		Node prev, next; // 이전 노드, 다음 노드 참조
		
		public Node() {
			
		}
		
		public Node(T d) {
			data=d;
			prev=null;
			next=null;
		}

		// 노드를 문자열로 표현할 때 데이터만 출력
		public String toString() {
			return data.toString();
		}
	}

	Node head, tail; // 리스트의 첫 노드와 마지막 노드
	int size; // 리스트에 저장된 요소 수

	// 생성자: 빈 리스트로 초기화
	public MyLinkedList3() {
		head=null;
		tail=null;
		size=0;
	}

	// 인덱스가 유효한지 검사 (0 <= index < size)
	private boolean checkIndexValidation(int index) {
		return (index>=0 && index<size)? true:false;
	}

	// 데이터를 리스트 끝에 추가
	public void add(T data) { //save data at the end of array
		addLast(data);
	}

	// 리스트 끝에 노드 추가
	public void addLast(T data) {
		Node newNode = new Node(data);
		if (tail==null) { // 리스트가 비어 있을 경우
			tail=newNode;
			head=newNode;
		}
		else {
			newNode.prev=tail;
			tail.next=newNode;
			tail=newNode;
		}
		size++;
	}

	// 리스트 앞에 노드 추가
	public void addFirst(T data) {
		Node newNode = new Node(data);
		if (head==null) { // 비어 있을 경우
			tail=newNode;
			head=newNode;
		}
		else {
			newNode.next=head;
			head.prev=newNode;
			head=newNode;
		}
		size++;
	}

	// index 위치에 새 노드 삽입
	public void add(int index, T data) {  // index<=size
		if (checkIndexValidation(index)) {
			if (index==0)
				addFirst(data);
			else {
				Node temp = head;
				for (int i=0;i<index;i++)
					temp=temp.next;
				Node newNode = new Node(data);
				newNode.next=temp;
				newNode.prev=temp.prev;
				temp.prev.next=newNode;
				temp.prev=newNode;
				size++;
			}	
		}
		else if(index==size) // 끝에 삽입하는 경우
			addLast(data);
	}

	// 첫 번째 노드 제거
	public T removeFirst() {
		if (head!=null) {
			return removeANode(head);
		}
		else
			return null;
	}

	// 마지막 노드 제거
	public T removeLast() {
		if (tail!=null) {
			return removeANode(tail);
		}
		else
			return null;
	}

	// index 위치 노드 제거
	public T remove(int index) {
		if (checkIndexValidation(index)) {
			Node p = head;
			for (int i=0;i<index;i++)
				p=p.next;
			return removeANode(p);
		}
		else
			return null;
	}

	// 실제 노드를 삭제하는 공통 메서드
	private T removeANode(Node node) {
//		if (head==node) {
//			if (tail==node) {
//				head=null;
//				tail=null;
//			}
//			else {
//				head=head.right;
//				head.left=null;
//			}
//		}
//		else {
//			if (tail==node) {
//				tail=tail.left;
//				tail.right = null;
//			}
//			else {
//				node.right.left = node.left;
//				node.left.right = node.right;
//			}
//		}
//		size--;
		
		
		T ret= node.data;

		// 이전 노드 연결 갱신
        if (node.prev == null) {
        	head = node.next;
        } else {
            node.prev.next = node.next;
            node.prev = null;
        }

		// 다음 노드 연결 갱신
        if (node.next == null) {
        	tail = node.prev;
        } else {
        	node.next.prev = node.prev;
        	node.next = null;
        }
        size--;
        return ret;

	}

	// 값이 일치하는 노드 제거 (처음 발견된 하나만)
	public boolean remove(T data) {
		Node temp = head;
		while (temp !=null) {
			if (temp.data.equals(data)) {
				removeANode(temp);
				return true;
			}
			else {
				temp = temp.next;
			}	
		}
		return false;
	}

	// 리스트의 모든 요소를 문자열로 출력
	public String toString() {
		String ret = "";
		Node temp=head;
		while(temp!=null) {
			ret = ret +temp.data.toString()+" ";
			temp=temp.next;
		}
		return ret;
	}

	// 특정 데이터의 인덱스를 반환 (없으면 -1)
	public int indexOf(T data) {
		if (head==null) return -1;
		Node temp=head;
		for (int i=0;i<size;i++) {
			if (temp.data.equals(data))
				return i;
			temp=temp.next;
		}
		return -1;
	}

	// index 위치 노드의 데이터를 반환
	public T get(int index) {
		if (checkIndexValidation(index)) {
			Node temp = head;
			for (int i=0;i<index;i++)
				temp=temp.next;
			return temp.data;
		}
		else
			return null;
	}

	// index 위치 노드의 데이터를 수정
	public void set(int index, T data) {
		if (checkIndexValidation(index)) {
			Node temp = head;
			for (int i=0;i<index;i++)
				temp=temp.next;
			temp.data = data;
		}
	}
	
	public int sizeOf() {
		return size;
	}

	// 리스트의 크기 반환
	public int size() {
		return size;
	}

	// 리스트 전체 출력
	public void showList() {
		System.out.println(toString());
	}
	
	
	public static void main(String[] args) {
		
//		String [] data = {"kim","lee","park","choi","jung","kang","cho","yoon","jang"};
		Object [][] data = {{1,"kim"},{2,"lee"},{3,"park"},{4,"choi"},{5,"jung"},
				{6,"kang"},{7,"cho"},{8,"yoon"},{9,"jang"}};
		
		MyLinkedList3<MyData> list = new MyLinkedList3<>();

		for (int i=0;i<data.length; i++) {
			list.add(new MyData((int)data[i][0], (String)data[i][1]));
			list.showList();
		}
		
		System.out.println(list.get(0));
		System.out.println(list.get(5));
		System.out.println(list.get(8));
		
		list.add(0,new MyData(10, "Lim"));
		list.add(5,new MyData(11, "han"));
		list.add(list.size(),new MyData(12, "oh"));
		list.showList();

		System.out.println(">>>  "+ list.indexOf(new MyData(2, "lee")));

		System.out.println(list.remove(0));
		list.showList();
		System.out.println(list.remove(5));
		list.showList();
		System.out.println(list.remove(list.size()-1));
		list.showList();
		
		System.out.println(list.indexOf(new MyData(11, "han")));
		System.out.println(list.remove(new MyData(11, "han")));
		list.showList();

		
	}
}