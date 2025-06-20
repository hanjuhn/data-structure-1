package week07;

import java.util.ArrayList;

public class PriorityQueue {

    ArrayList<MyData> queue = new ArrayList<>();

    // 일반 add: 큐 맨 뒤에 추가
    public void add(MyData data) {
        queue.add(data);
    }

    // 우선순위 기반 삽입
    public void addPriority(MyData data) {
        for (int i = 0; i < queue.size(); i++) {
            // 현재 요소보다 우선순위가 낮으면 그 앞에 삽입
            if (data.compareTo(queue.get(i)) > 0) {
                queue.add(i, data);
                return;
            }
        }
        // 전부 우선순위가 높거나 같으면 마지막에 삽입
        queue.add(data);
    }

    public void showQueue() {
        for (MyData data : queue) {
            System.out.print(data + "  ");
        }
        System.out.println();
    }

    // 내부 클래스
    public class MyData {
        int priority;
        String name;

        public MyData(int p, String s) {
            priority = p;
            name = s;
        }

        public boolean equals(MyData that) {
            return this.name.equals(that.name);
        }

        public int compareTo(MyData that) {
            if (this.priority > that.priority)
                return 1;
            else if (this.priority < that.priority)
                return -1;
            else
                return 0;
        }

        public String toString() {
            return name + "(" + priority + ")";
        }
    }

    // 테스트 main
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();

        pq.add(pq.new MyData(3, "ddd"));
        pq.addPriority(pq.new MyData(1, "paaa"));
        pq.add(pq.new MyData(1, "aaa"));
        pq.addPriority(pq.new MyData(5, "pccc"));
        pq.add(pq.new MyData(2, "bbb"));
        pq.add(pq.new MyData(5, "ccc"));
        pq.addPriority(pq.new MyData(2, "pbbb"));
        pq.addPriority(pq.new MyData(3, "pddd"));

        pq.showQueue();
    }
}
