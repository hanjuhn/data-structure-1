package week03;

public class Maze{
	
	int[][] maze;
	int[][] memo;
	int count;
	
	
	public Maze(int [][] input) {
		maze = input;
		memo = new int[maze.length][maze[0].length];
		for (int i=0; i<maze.length; i++)
			for (int j=0; j<maze[i].length; j++)
				memo[i][j] = -1;
	}

	public void resetCount() {
		count=0;
	}
	
	public int getCount() {
		return count;
	}
	

	public int findMaxIter(int m, int n) {
		int [][] temp = new int[m+1][n+1];
		temp[0][0] = maze[0][0];
		for (int i=1; i<=m; i++) {
			count++;
			temp[i][0] = temp[i-1][0] + maze[i][0];
			}
			
		for (int j=1; j<=n; j++) {
			count++;
			temp[0][j] = temp[0][j-1] + maze[0][j];
			}
		
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				count++;
				temp[i][j] = Math.max (temp[i-1][j] , temp[i][j-1]) + maze[i][j];
			}
		}
		
		showMatrix("result", temp);
		
		return temp[m][n];

	}
	
	
	public int findMaxRecMemo(int m, int n) {
		count++;
		
		if (m==0 && n==0) {
			memo[m][n] = maze[0][0];
		return memo[0][0];
		}
		
		if (m==0) {
			if (memo[m][n]==-1)
				memo[m][n] = maze[m][n] + findMaxRecMemo(m, n-1);
			
			return memo[m][n];
		}
		
		if(n==0) {
			if (memo[m][n]==-1)
				memo[m][n] = maze[m][n] + findMaxRecMemo(m-1, n);
			return maze[m][n];
		}
		
		else
			if (memo[m][n]==-1)
				memo[m][n] = maze[m][n] + Math.max(findMaxRecMemo(m, n-1), findMaxRecMemo(m-1, n));
			return memo[m][n];

			
	}
	
	
	public void showMatrix(String name, int [][] m) {
		System.out.println("< Matrix   "+name+" >");
		
		
		for (int i=0; i<=m.length; i++) {
			for (int j=1; j<=m.length; j++)
				System.out.printf("%5d", m[i][j]);
			System.out.println();
		}
	}
	

	
	
	public int findMaxRec(int m, int n) {
		if (m==0 && n==0)
			return maze[0][0];
		if(m==0)
			return maze[m][n] + findMaxRec(m, n-1);
		
		if(n==0)
			return maze[m][n] + findMaxRec(m-1, n);
		else
			return maze[m][n] + Math.max(m, n);
	}
	

	
	
	

	public static void main(String[] args) {
		int [][] maze = {
				{1,2,1,5,8,4},
				{4,1,9,4,2,3},
				{8,5,4,3,8,2},
				{1,5,3,5,7,3},
				{4,7,7,9,2,8},
				{2,4,6,3,1,4}
		};

		Maze me = new Maze(maze);
		me.resetCount();
		
		System.out.println("MaxSum = "+me.findMaxIter(maze.length-1,maze.length-1)
		+" Count result = "+me.getCount());
		
		System.out.println("MaxSum = "+me.findMaxRec(maze.length-1,maze.length-1)
		+" Count result = "+me.getCount());
		
		

	}
}