package week14;

public class Perm {
    char[] data; // 순열을 만들 문자 배열

    // 생성자: 문자 배열을 받아 초기화
    public Perm(char[] d) {
        data = d;
    }

    // 순열 생성 시작 메서드: depth 0부터 시작
    public void perm() {
        perm(0);
    }

    // 재귀적으로 순열을 생성하는 메서드
    private void perm(int depth) {
        // 기저 조건: 모든 자리를 다 채운 경우 배열 출력
        if (depth == data.length) {
            System.out.println(data); // 완성된 순열 출력
            return;
        }

        // 현재 위치(depth)부터 끝까지 반복하며 swap
        for (int i = depth; i < data.length; i++) {
            swap(depth, i);           // i번째 문자와 현재 depth 위치 문자 교환
            perm(depth + 1);          // 다음 depth로 재귀 호출
            swap(i, depth);           // 백트래킹: 원래 상태로 되돌림
        }
    }

    // 배열의 두 문자를 교환하는 메서드
    private void swap(int i, int j) {
        char temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // 실행 메인 함수
    public static void main(String[] args) {
        // 사용할 문자 배열 정의
        // char[] data = {'a', 'b', 'c', 'd', 'e'}; // 더 긴 배열 사용 시 주석 해제
        char[] data = {'a', 'b', 'c'};

        // Perm 객체 생성 및 순열 생성 실행
        Perm p = new Perm(data);
        p.perm(); // 모든 순열 출력
    }
}