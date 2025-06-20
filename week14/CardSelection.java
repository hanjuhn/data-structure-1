package week14;

// 다음과 같은 카드가 나열되어 있을 때, 두 사람이 교대로 양쪽 끝의 카드중 하나를 선택하여
// 자신의 카드 숫자 합이 더 큰 사람이 이기는 게임이다.
// 가능한 가장 큰 합이 얼마인지 찾아서 출력하는 알고리즘을 작성하시오.

public class CardSelection {
    int[] card; // 카드 점수를 저장하는 배열

    // 생성자: 카드 배열을 전달받아 초기화
    public CardSelection(int[] d) {
        card = d;
    }

    // 카드 전체 범위(0 ~ N-1)에 대해 재귀 함수 호출
    public int selectNSum() {
        return selectNSum(0, card.length - 1);
    }

    // i부터 j까지 남았을 때 현재 플레이어가 얻을 수 있는 최대 점수 계산
    private int selectNSum(int i, int j) {
        // 카드가 두 장만 남았을 경우: 큰 점수를 선택
        if (i + 1 == j) {
            return Math.max(card[i], card[j]);
        } else {
            // 양쪽 끝 중 하나를 선택한 후, 상대가 최적으로 움직인다고 가정
            // 상대가 최소 점수를 남기도록 하기 때문에 Math.min 사용
            return Math.max(
                    card[i] + Math.min(selectNSum(i + 2, j), selectNSum(i + 1, j - 1)),
                    card[j] + Math.min(selectNSum(i + 1, j - 1), selectNSum(i, j - 2))
            );
        }
    }

    // 메인 함수: 카드 배열을 만들고 결과 출력
    public static void main(String[] args) {
        // 테스트용 카드 배열 (짝수 개여야 함)
        int[] d = {11, 21, 3, 4, 5, 9, 8, 7, 6, 10};  // 반드시 짝수 개 카드

        // 카드 선택 객체 생성
        CardSelection s = new CardSelection(d);

        // 결과 출력
        System.out.println("\nMaxSum = " + s.selectNSum());
    }
}