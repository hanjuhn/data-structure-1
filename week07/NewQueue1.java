package week07;

public class NewQueue1 {

    // private MyArrayList2<T> list;
    private MyArrayList2<MyArrayList1> list;

    public NewQueue1() {
        list = new MyArrayList2<>();
    }

    // 요소 추가 (enqueue: 뒤에 추가)
    public void enqueue(MyArrayList1 item) {
        list.add(item);
    }

    // 요소 제거 (dequeue: 앞에서 제거)
    public MyArrayList1 dequeue() {
        if (isEmpty()) return null;
        MyArrayList1 front = list.get(0);
        list.remove(0);
        return front;
    }

    // 가장 앞에 있는 요소 확인 (제거하지 않음)
    public MyArrayList1 peek() {
        if (isEmpty()) return null;
        return list.get(0);
    }

    // 큐가 비었는지 확인
    public boolean isEmpty() {
        return list.size() == 0;
    }

    // 큐의 크기 반환
    public int size() {
        return list.size();
    }

    // 전체 요소 출력 (테스트용)
    public void showQueue() {
        list.showList();
    }

}
