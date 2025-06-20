package week09;

import week07.MyArrayList0; // 테스트용 배열리스트 사용

public class MyLinkedList0 {

    // 내부 클래스 Node: 연결 리스트의 각 노드를 표현
    class Node {
        int data;  // 저장된 값
        Node next;  // 다음 노드의 참조

        Node(int d) {
            data = d;
            next = null;
        }

        public String toString() {
            return "" + data;
        }
    }

    Node head;  // 리스트의 시작 노드를 가리킴


    public MyLinkedList0() {
        head = null;
    }

    // 리스트가 비었는지 확인
    public boolean isEmpty() {
        return (head==null);
    }

    // 앞에 추가
    public void add(int value) {
        addFirst(value);
//        addLast(value);  // 필요에 따라 변경 가능
    }

    // 맨 앞에 노드 추가
    private void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head; // 기존 head를 뒤로 밀음
        head = newNode;
    }

    // 맨 뒤에 노드 추가
    private void addLast(int value) {
        if (isEmpty()) {
            addFirst(value);
        }
        else {
            Node newNode = new Node(value);
            Node p = head;
            while (p.next != null) { // 마지막 노드까지 이동
                p = p.next;
            }
            p.next = newNode; // 마지막 노드의 next에 새 노드 연결
        }
    }

    // 특정 값의 인덱스 반환 (못 찾으면 -1)
    public int indexOf(int value) {
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

    // 지정한 인덱스에 값 삽입
    public void add(int index, int value) {  // 0 <= index <= size()
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

    // 인덱스가 유효한지 확인
    private boolean checkIndexRange(int index) {
        if (index >= 0 && index<size()) {
            return true;
        }
        else {
            return false;
        }
    }

    // 리스트 초기화
    public void clear() {
        head = null;
    }

    // 특정 값이 포함되어 있는지 확인
    public boolean contains(int value) {
        return (indexOf(value) != -1);
    }

    // 지정한 인덱스의 값 반환
    public int get(int index) {
        int i = 0;
        Node p = head;
        while (p != null) {
            if (i == index) {
                return p.data;
            }
            i++;
            p = p.next;
        }
        return -999; // null에 해당하는 임의의 정한 값 (오류를 나타내는 값)
    }

    // 특정 인덱스의 값을 수정
    public void set (int index, int value) {
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

    // 인덱스로 노드 삭제하고 삭제된 값 반환
    public int remove(int index) { // return the value removed
        int ret = -999;
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
        return ret;
    }

    // 값으로 노드 삭제하고 삭제된 값 반환
    public int remove(Object value) {
        if (head != null) {
            if (head.data == (int)value) {
                return removeFirst();
            }
            else {
                Node p = head;
                Node q = p.next;
                while (q != null) {
                    if (q.data == (int)value) {
                        p.next = q.next;
                        return q.data;
                    }
                    p = q;
                    q = q.next;
                }

            }
        }
        return -999;
    }

    // 첫 번째 노드를 삭제하고 값 반환
    private int removeFirst() {
        int ret = -999;
        if (head != null) {
            ret = head.data;
            head = head.next;
        }
        return ret;
    }

    // 리스트 문자열로 출력
    public String toString() {
        String str = "";
        Node p = head;
        while (p != null) {
            str = str + p.data+ "  ";
            p = p.next;
        }
        return str;
    }

    // 콘솔에 리스트 출력
    public void showList() {
        System.out.println(toString());
    }


    // 테스트용 메인 함수
    public static void main(String[] args) {
        int [] data = {113,  336,  74,  71,  86,  176,  313,  80,  225,  342,
                170,  292,  275,  266 , 79,  16,  109,  175 , 245,  156,
                50,  61,  277,  167,  81,  24,  76,  186,  78,  101,
                301,  62,  152,  219,  294};

        MyArrayList0 list = new MyArrayList0(20); // 배열 기반 리스트 사용

        for (int i=0;i<10; i++)
            list.add(data[i]);  // cf :  list[i]=data[i]
        list.showList();

        list.set(5, 999);     // list[5]=999
        int x =(int) list.get(5);  // cf : x = list[5]
        System.out.println("\nx = "+x);

        list.remove((Integer)336);
        list.showList();

        list.add(3, 111);
        list.showList();
        list.add(list.size(), 222);
        list.showList();

        list.clear();
        list.showList();


    }

}
