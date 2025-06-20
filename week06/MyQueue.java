package week06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyQueue {
    // 큐는 한쪽 끝(rear)에서는 삽입연산만 이루어지며 다른 한쪽 끝(front)에서는 삭제연산만 이루어지는 유한 순서 리스트


    int[] queue;
    int front, rear, queueSize;


    public MyQueue(int n) {
//      queue: 데이터를 저장할 정수 배열
        queueSize = n; 
        queue = new int[queueSize];
        // front는 다음 데이터를 꺼낼 위치
        front = 0;
        // rear는 다음 데이터를 넣을 위치
        rear = 1;
    }

    public boolean isEmpty() {
        // front와 rear가 같으면 비어 있음
        return front == rear;
    }

    public boolean isFull() {
        // rear 다음 자리가 front면 가득 참
        // queueSize 5 / rear = 4일 때 -> (4 + 1) % 5 == front면 가득 찬 거다
        return (rear + 1) % queueSize == front;
    }



    public void enqueue(int value) {
        // rear 위치에 데이터를 넣고
        // rear를 한 칸 이동시킴
        // rear가 배열 끝에 도달하면 0부터 다시 시작 (mod 연산)

        if (isFull()) {
            System.out.println(">>> Queue Full...");
            return;
        }
        // queue의 rear 인덱스에 value 삽입
        queue[rear] = value;
        // 큐를 배열로 만들었는데 배열 끝까지 갔다가 다시 앞으로 돌아오게 만들기 위해서
        // queueSize 5 / rear = 3일 때 -> (3 + 1) % 5 = 1
        rear = (rear + 1) % queueSize;
    }


    public int dequeue() {
        // front 위치의 값을 꺼낸 후
        // front를 한 칸 이동시킴
        // front도 역시 mod 연산으로 원형 이동
        if (isEmpty()) {
            System.out.println(">>> Queue Empty...");
            return -999;
        }
        // 지금 front가 가리키는 위치의 값을 value에 저장함
        int value = queue[front];
        // 데이터를 꺼냈으니 front를 다음 위치로 이동시킴
        // 예를 들어 front가 4였고 queueSize가 5면 → (4+1)%5 = 0이 돼서 다시 처음으로 돌아감
        front = (front + 1) % queueSize;
        return value;
    }


    public void showQueue() {
        System.out.println(Arrays.toString(queue));
    }


    public static void main(String[] args) {
        int[] data = {3, 5, 4, 1, 7, 2, 9, 8, 6, 0};
        
        List<Integer> list = new ArrayList<>();

        MyQueue queue = new MyQueue(10);

        for (int i = 0; i < data.length; i++) {
            queue.enqueue(data[i]);
            queue.showQueue();
        }

        for (int i = 0; i < data.length; i++) {
            System.out.println(queue.dequeue());
//            queue.showQueue();
        }
    }
}