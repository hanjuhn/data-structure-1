package week11;


// 이중 원형 연결 리스트: 이전과 다음 노드를 모두 참조하며, 마지막 노드는 처음 노드와 연결됨
public class MyCircularDoublyLinkedList<T> {

    // 내부 클래스: 노드를 나타냄
    public class Node {
        T data;        // 노드가 저장하는 데이터
        Node prev;     // 이전 노드를 가리키는 포인터
        Node next;     // 다음 노드를 가리키는 포인터

        public Node() {}

        public Node(T d) {
            data = d;
            prev = null;
            next = null;
        }

        public String toString() {
            return data.toString();
        }
    }

    private Node head;  // 리스트의 시작 노드
    private int size;   // 리스트의 크기

    // 생성자: 빈 리스트로 초기화
    public MyCircularDoublyLinkedList() {
        head = null;
        size = 0;
    }

    // 유효한 인덱스인지 확인 (0 <= index < size)
    private boolean checkIndexValidation(int index) {
        return (index >= 0 && index < size);
    }

    // 마지막 위치에 데이터 추가
    public void add(T data) {
        addLast(data);
    }

    // 리스트 끝에 노드 추가
    public void addLast(T data) {
        Node newNode = new Node(data);

        if (head == null) { // 리스트가 비어있는 경우
            head = newNode;
            head.prev = head;
            head.next = head;
        } else {
            Node tail = head.prev; // 마지막 노드 참조
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
        size++;
    }

    // 리스트 앞에 노드 추가
    public void addFirst(T data) {
        addLast(data);     // 우선 끝에 추가한 후
        head = head.prev;  // head를 새로운 노드로 이동
    }

    // index 위치에 노드 삽입
    public void add(int index, T data) {
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else if (checkIndexValidation(index)) {
            Node temp = head;
            for (int i = 0; i < index; i++)
                temp = temp.next;

            Node newNode = new Node(data);
            newNode.prev = temp.prev;
            newNode.next = temp;
            temp.prev.next = newNode;
            temp.prev = newNode;
            size++;
        }
    }

    // 첫 번째 노드 제거
    public T removeFirst() {
        if (head == null) return null;
        return removeANode(head);
    }

    // 마지막 노드 제거
    public T removeLast() {
        if (head == null) return null;
        return removeANode(head.prev); // tail = head.prev
    }

    // index 위치의 노드 제거
    public T remove(int index) {
        if (!checkIndexValidation(index)) return null;
        Node temp = head;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        return removeANode(temp);
    }

    // 특정 노드를 삭제하는 공통 메서드
    private T removeANode(Node node) {
        T ret = node.data;

        if (size == 1) {
            head = null; // 유일한 노드일 경우 리스트 비우기
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            if (node == head)
                head = node.next; // head가 삭제되는 경우 갱신
        }

        node.prev = null;
        node.next = null;
        size--;
        return ret;
    }

    // 특정 데이터와 일치하는 노드를 삭제 (첫 번째 일치 항목만)
    public boolean remove(T data) {
        if (head == null) return false;
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(data)) {
                removeANode(temp);
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // 리스트의 모든 요소를 문자열로 반환
    public String toString() {
        if (head == null) return "";
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        for (int i = 0; i < size; i++) {
            sb.append(temp.data.toString()).append(" ");
            temp = temp.next;
        }
        return sb.toString();
    }

    // 특정 데이터의 인덱스를 반환 (없으면 -1)
    public int indexOf(T data) {
        if (head == null) return -1;
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(data))
                return i;
            temp = temp.next;
        }
        return -1;
    }

    // index 위치의 데이터를 반환
    public T get(int index) {
        if (!checkIndexValidation(index)) return null;
        Node temp = head;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        return temp.data;
    }

    // index 위치의 데이터를 수정
    public void set(int index, T data) {
        if (!checkIndexValidation(index)) return;
        Node temp = head;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        temp.data = data;
    }

    // 리스트의 크기 반환
    public int size() {
        return size;
    }

    // 리스트 전체를 출력
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