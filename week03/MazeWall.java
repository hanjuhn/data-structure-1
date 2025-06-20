package week03;

public class MazeWall {

    int[][] maze;
    int[][] cost;
    int callCount;

    public MazeWall(int[][] input) {
        maze = input;
        cost = new int[maze.length][maze[0].length];
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost[0].length; j++) {
                cost[i][j] = -1;
            }
        }
    }

    public void resetCount() {
        callCount = 0;
    }

    public int getCount() {
        return callCount;
    }

    // 2-1) 최소 비용을 재귀적으로 계산 (메모이제이션 없이)
    public int minCost(int i, int j) {
        callCount++;

        if (i < 0 || j < 0 || maze[i][j] == 999) return Integer.MAX_VALUE;
        if (i == 0 && j == 0) return maze[0][0];

        int up = minCost(i - 1, j);
        int left = minCost(i, j - 1);
        int min = Math.min(up, left);
        if (min == Integer.MAX_VALUE) return Integer.MAX_VALUE;

        return maze[i][j] + min;
    }

    // 2-2) 최소 비용을 cost 배열에 저장하며 계산 (메모이제이션)
    public int minCostAll(int i, int j) {
        callCount++;

        if (i < 0 || j < 0 || maze[i][j] == 999) return Integer.MAX_VALUE;

        if (cost[i][j] != -1) return cost[i][j];

        if (i == 0 && j == 0) return cost[i][j] = maze[0][0];

        int up = minCostAll(i - 1, j);
        int left = minCostAll(i, j - 1);
        int min = Math.min(up, left);
        if (min == Integer.MAX_VALUE) return cost[i][j] = Integer.MAX_VALUE;

        return cost[i][j] = maze[i][j] + min;
    }

    public void showCost() {
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost[0].length; j++) {
                if (cost[i][j] == Integer.MAX_VALUE)
                    System.out.printf("%5s", "INF");
                else
                    System.out.printf("%5d", cost[i][j]);
            }
            System.out.println();
        }
    }

    // 2-3) 최소 비용 경로 출력
    public String pathToString(int i, int j) {
        if (i == 0 && j == 0) return "(0,0) ";

        if (i > 0 && j > 0) {
            if (cost[i - 1][j] <= cost[i][j - 1]) return pathToString(i - 1, j) + "(" + i + "," + j + ") ";
            else return pathToString(i, j - 1) + "(" + i + "," + j + ") ";
        } else if (i > 0) {
            return pathToString(i - 1, j) + "(" + i + "," + j + ") ";
        } else {
            return pathToString(i, j - 1) + "(" + i + "," + j + ") ";
        }
    }

    public static void main(String[] args) {
        int[][] input = {
                { 3,  2,  5,   1,   4,  3,  6 },
                { 2, 999,999,  5,  999,999, 1 },
                { 5, 999, 9,   6,   3,  5,  4 },
                { 1, 999, 3,   8,   6,  1,  7 },
                { 7, 999, 8,  999,  9,  2,  2 },
                { 8,  6,  2,  999,  6,  9,  5 },
                { 2,  1,  7,   2,   4,  3,  1 },
        };

        MazeWall pf = new MazeWall(input);

        System.out.println("2-1) MinimumCost = " + pf.minCost(input.length - 1, input.length - 1));
        System.out.println("Recursive Call Count = " + pf.getCount());

        pf.resetCount();
        System.out.println("2-2) Cost Matrix");
        pf.minCostAll(input.length - 1, input[0].length - 1);
        pf.showCost();
        System.out.println("Recursive Call Count with Cost Matrix = " + pf.getCount());

        System.out.println("2-3) Minimum Cost Path");
        System.out.println(pf.pathToString(input.length - 1, input[0].length - 1));

        // 2-4) 시간복잡도 분석
        System.out.println("\n2-4) 시간복잡도");
        System.out.println("1)의 경우: O(2^(m+n)) - 중복 재귀 호출로 인해 지수적 시간복잡도 발생");
        System.out.println("2)의 경우: O(m*n) - 메모이제이션을 사용하여 각 셀을 한 번만 계산");
    }
}
