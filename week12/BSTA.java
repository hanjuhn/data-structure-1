package week12;

import java.util.Arrays;

// 배열로 구현한 이진 탐색 트리 (Binary Search Tree using Array)

public class BSTA {

    // array로 구현한 tree

    int [] array; // 트리를 배열로 표현 (index 1부터 사용)
    int nNode; // 삽입된 노드 수

    public BSTA() {
        array = new int[100]; // 충분한 크기의 배열 선언 (고정 크기)
        Arrays.fill(array, -1); // -1은 빈 노드를 의미
        nNode = 0;
    }

    // 노드 삽입
    public void insert(int d) {
        if (nNode == 0) {
            array[1] = d; // 루트에 첫 노드 삽입
            nNode++;
        }
        else {
            int i = 1; // 루트부터 시작
            while (array[i] != -1) { // 빈 위치 찾을 때까지 반복
                if (d < array[i]) {
                    i = i*2; // 왼쪽 자식으로 이동
                }
                else {
                    i = i*2+1; // 오른쪽 자식으로 이동
                }
            }
            array[i] = d; // 빈 위치에 삽입
            nNode++;
        }
    }

    // 트리 구조를 배열로 출력
    public void showTree() {
        for (int i = 1; i <= 30; i++) {
            System.out.print(array[i] + " "); // -1은 빈 노드를 의미
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int [] keys = {4,7,5,1,0,3,9,2,6,8};
//        int [] keys = {0,1,2,3,4,5,6,7,8,9}; // -> 이건 에러 난다, array는 메모리 공간 낭비가 심해서 이런 경우에는 array로 구현하면 안 됨 -> 그럼 linked list로 구현

        BSTA t = new BSTA();

        for (int i=0; i<keys.length;i++) {
            t.insert(keys[i]);
            t.showTree();
        }

    }

}
