package week14;

import java.util.Arrays;

// NXN 크기의 체스판에, N개의 퀸말을 다른 퀸의 좌, 우, 상, 하, 대각선 상에 위치하지 않도록 놓기
// 1. 놓을 수 있는 경우가 존재하는지 판단하기
// 2. 놓을 수 있는 경우 찾기
// 상태공간트리 / backtracking (depth first search & trial and error)

public class NQueens {

    int N;         // 체스판의 크기 (N x N)
    int[] cols;    // 각 행(row)에 퀸이 놓인 열(column) 인덱스를 저장하는 배열

    // 생성자: N값 초기화 및 cols 배열 초기화
    public NQueens(int n) {
        N = n;
        cols = new int[N];
        Arrays.fill(cols, -1); // 모든 값을 -1로 초기화 (아직 퀸을 놓지 않음)
    }

    // 퍼블릭 메서드: 퀸 배치 시작
    public boolean solve() {
        return probe(0); // 0번째 행부터 배치 시작
    }

    // 재귀적으로 퀸을 한 행씩 배치하는 함수
    // i행에 퀸을 놓는 재귀 함수
    private boolean probe(int i) {
        // 모든 행에 퀸을 성공적으로 배치한 경우 (기저 조건)
        if (i == N) {
            for (int k = 0; k < N; k++) // 각 행에 배치된 열 인덱스 출력
                System.out.print(cols[k]);
            System.out.println(); // 한 줄 출력 완료
            return true; // 하나의 해를 찾았음을 의미
        } else {
            // 현재 행 i에 대해 0부터 N-1까지 가능한 열에 퀸을 놓아봄
            for (int c = 0; c < N; c++) {
                cols[i] = c; // i행 c열에 퀸 배치
                if (feasible(i)) { // 지금까지 배치가 유효한 경우
                    if (probe(i + 1)) // 다음 행으로 재귀
                        return true; // 해를 찾은 경우 바로 종료
                }
            }
        }
        return false; // 가능한 해가 없는 경우
    }

    // 현재 행(row)에 퀸을 놓는 것이 가능한지 검사
    private boolean feasible(int row) {
        // 0행부터 row-1행까지 이미 놓인 퀸들과 비교
        for (int i = 0; i < row; i++) {
            // 같은 열에 퀸이 있으면 안 됨
            if (cols[i] == cols[row])
                return false;

                // 같은 대각선에 퀸이 있으면 안 됨
                // 대각선 조건: 행 차이 == 열 차이
            else if ((row - i) == Math.abs(cols[row] - cols[i]))
                return false;
        }
        return true; // 충돌이 없으면 배치 가능
    }

    public static void main(String[] args) {
        int n;

        // 테스트 1: N = 2
        n = 2;
        NQueens q = new NQueens(n); // N=2 크기의 NQueen 객체 생성
        System.out.print("N = " + n + " : "); // 현재 N값 출력
        System.out.println(q.solve());       // N=2에서 해가 있는지 출력 (true/false)

        // 테스트 2: N = 3
        n = 3;
        q = new NQueens(n); // 새로운 N=3 체스판 생성
        System.out.print("N = " + n + " : ");
        System.out.println(q.solve());

        // 테스트 3: N = 4
        n = 4;
        q = new NQueens(n);
        System.out.print("N = " + n + " : ");
        System.out.println(q.solve());

        // 테스트 4: N = 5
        n = 5;
        q = new NQueens(n);
        System.out.print("N = " + n + " : ");
        System.out.println(q.solve());

        // 테스트 5: N = 6
        n = 6;
        q = new NQueens(n);
        System.out.print("N = " + n + " : ");
        System.out.println(q.solve());
    }
}
