package week10;

// 이중 연결 리스트
public class MyLinkedList<T> {


    class Node {
        T data; // 노드에 저장하는 데이터
        Node prev; // 이전 노드에 대한 참조
        Node next; // 다음 노드에 대한 참조

        Node(T d) {
            data = d;
            prev = null;
            next = null;
        }

        public String toString() {
            return data.toString(); // 노드의 데이터를 문자열로 반환
        }
    }

    Node head, tail; // 연결 리스트의 가장 앞과 끝을 나타내는 포인터
    int size; // 리스트에 저장된 노드 수

    public MyLinkedList() { // 생성자: 빈 리스트 초기화
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() { // 리스트가 비어있는지 확인
        return head == null;
    }

    public int size() { // 리스트의 크기 반환
        return size;
    }

    public void add(T value) { // 기본적으로 리스트 끝에 요소 추가
        addLast(value);
    }

    // 리스트의 앞에 새 노드 추가
    private void addFirst(T value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode; // 비어있으면 head와 tail 모두 새 노드를 가리킴
        } else {
            // 새 노드가 기존 head 앞에 연결됨
            newNode.next = head;
            head.prev = newNode;
            head = newNode; // 새 노드를 head로 갱신
        }
        size++; // 크기 증가
    }

    // 리스트의 끝에 새 노드 추가
    private void addLast(T value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode; // 비어있으면 head와 tail 모두 새 노드를 가리킴
        } else {
            // tail 뒤에 새 노드를 연결
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode; // 새 노드를 tail로 설정
        }
        size++; // 크기 증가
    }

    // index 위치에 새 노드 삽입
    public void add(int index, T value) {
        // index가 0 이상 size 이하일 때만 삽입 가능
        if (!checkIndexRange(index) && index != size) return;

        if (index == 0) {
            addFirst(value); // 맨 앞에 삽입
        } else if (index == size) {
            addLast(value); // 맨 뒤에 삽입
        } else {
            // 중간 위치 삽입
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            Node newNode = new Node(value);
            newNode.prev = p.prev;
            newNode.next = p;
            p.prev.next = newNode;
            p.prev = newNode;
            size++;
        }
    }

    // index 위치 노드의 데이터를 반환
    public T get(int index) {
        if (!checkIndexRange(index)) return null;
        Node p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.data;
    }

    // index 위치 노드의 데이터를 수정
    public void set(int index, T value) {
        if (!checkIndexRange(index)) return;
        Node p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        p.data = value;
    }

    // 특정 값의 위치(인덱스)를 반환
    public int indexOf(T value) {
        Node p = head;
        int index = 0;
        while (p != null) {
            if (p.data.equals(value)) return index;
            p = p.next;
            index++;
        }
        return -1; // 찾지 못한 경우
    }

    // index 위치의 노드를 삭제하고 삭제된 데이터를 반환
    public T remove(int index) {
        if (!checkIndexRange(index)) return null;
        Node target;
        if (index == 0) {
            // 첫 노드 삭제
            target = head;
            head = head.next;
            if (head != null) head.prev = null;
            else {
                // 삭제 후 비었으면 tail도 null로
                tail = null;
            }
        } else if (index == size - 1) {
            // 마지막 노드 삭제
            target = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            // 중간 노드 삭제
            target = head;
            for (int i = 0; i < index; i++) {
                target = target.next;
            }
            target.prev.next = target.next;
            target.next.prev = target.prev;
        }
        size--;
        return target.data;
    }

    // 유효한 인덱스인지 검사하는 도우미 메서드
    private boolean checkIndexRange(int index) {
        return index >= 0 && index < size;
    }

    // 리스트 전체를 순서대로 출력
    public void showList() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
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