package week14;

import java.util.ArrayList;
import java.util.List;

// 괄호 조합 출력
// 정수 n이 주어지면 n개의 여는 괄호 '('과 n개의 닫는 괄호 ')'로 만들 수 있는 의미있는 괄호 조합을 모두 출력하시오.

class Parenthesis {

    // 괄호 조합 생성 시작 메서드 (n쌍 입력)
    public void genCases(int n) {
        String curStr = ""; // 현재까지 생성된 문자열
        List<String> cases = new ArrayList<>(); // 결과 저장 리스트
        int open = 0; // 열린 괄호 수
        int close = 0; // 닫힌 괄호 수

        // 재귀 호출로 모든 경우 생성
        genCases(cases, curStr, open, close, n);

        // 결과 출력
        for (String str : cases)
            System.out.println(str);
    }

    // 재귀적으로 괄호 조합을 생성하는 메서드
    private void genCases(List<String> cases, String curStr, int open, int close, int n) {
        // 종료 조건: 문자열 길이가 2n이면 유효한 조합 완성
        if (curStr.length() == 2 * n) {
            cases.add(curStr); // 리스트에 저장
            return;
        }

        // 열린 괄호가 아직 n개 미만이면 '(' 추가 가능
        if (open < n)
            genCases(cases, curStr + "(", open + 1, close, n);

        // 닫힌 괄호는 열린 괄호보다 적을 때만 추가 가능
        if (close < open)
            genCases(cases, curStr + ")", open, close + 1, n);
    }

    // 실행 메서드
    public static void main(String[] args) {
        Parenthesis p = new Parenthesis();

        // n을 1부터 4까지 증가시키며 테스트
        for (int n = 1; n < 5; n++) {
            System.out.println("\n>>> N = " + n);
            p.genCases(n); // 괄호 조합 출력
        }
    }
}