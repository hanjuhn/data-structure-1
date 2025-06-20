package week07;

public class TestQueue {

    public static void main(String[] args) {
        NewQueue1 queue = new NewQueue1();

        MyArrayList1 a = new MyArrayList1();
        a.add(10);
        a.add(20);

        MyArrayList1 b = new MyArrayList1();
        b.add(30);
        b.add(40);

        queue.enqueue(a);
        queue.enqueue(b);

        queue.showQueue();       // 전체 출력
        System.out.println(queue.peek());   // 첫 요소 확인
        queue.dequeue();         // 첫 요소 제거
        queue.showQueue();       // 나머지 출력
    }
}
