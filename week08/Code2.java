package week08;

public class Code2 {

	static class ScoreCard {
		int idNum;
		int score1;
		int score2;
		public ScoreCard(int id, int s1, int s2) {
			idNum =id;
			score1=s1; score2=s2;
		}
		public boolean equals(Object that) {
			return (this.idNum==((ScoreCard)that).idNum);
		}
		public String toString() {
			return ""+idNum+"["+score1+","+score2+"]";
		}
	}

	MyArrayList2<MyArrayList2<ScoreCard>> sList ;
	
	public Code2() {
		sList = new MyArrayList2<>();
		sList.add(new MyArrayList2<ScoreCard>()); // 1학년
		sList.add(new MyArrayList2<ScoreCard>()); // 2학년
		sList.add(new MyArrayList2<ScoreCard>()); // 3학년
		sList.add(new MyArrayList2<ScoreCard>()); // 4학년	
	}
	
				
	//	     Q4 : Complete add method			
	public void add(int grade, ScoreCard sc) {   //	add without duplication-filtering
		if (grade >= 1 && grade <= 4) {
			sList.get(grade - 1).add(sc);
		} else
			System.out.println("Invalid Grade");
	}
	
	public MyArrayList2<ScoreCard> get(int gradeIndex) {
		return sList.get(gradeIndex);
	}
	
	public ScoreCard get(int gradeIndex, int idNum) {  
		return sList.get(gradeIndex).get(idNum);
	}


	//	     Q5 : Complete SortAll(Selection Sort) & swap			
	public void sortAll() { // 학년별로 학번 오름차순으로 정렬
//		int n = sList.size();
//		// i는 현재 정렬해야 할 마지막 위치(index)
//		// 그 최댓값을 마지막으로 보내기 위한 위치
//		// 전체 배열이 10개라면 먼저 마지막 위치(인덱스 9)에 가장 큰 수를 놓고 그다음 인덱스 8에 두 번째 큰 수를 놓는 방식
//		for (int i=n-1; i>0; i--) {
//
//			// 최댓값이 있는 위치 저장
//			int maxIndex = i;
//
//			for (int j=0; j<i; j++) {
//				// max 인덱스가 j 인덱스보다 클때까지 j를 maxIndex에 넣는다
//				// 아직 정렬되지 않은 부분에서 반복하면서 최댓값을 찾아냄
//				if (sList.get(i).get(j).idNum>sList.get(i).get(maxIndex).idNum) {
//					maxIndex = j;
//				}
//			}
//			// maxIndex를 i로 바꿈
//			swap(sList.get(i), maxIndex, i);
//		}

		for (int grade=1;grade<=4;grade++) {
			MyArrayList2<ScoreCard> aList = sList.get(grade-1);
			int n = aList.size();
			for (int i=0; i<n-1; i++) {
				int minIndex = i;
				for (int j=i+1; j<n; j++) {
					if (aList.get(minIndex).idNum > aList.get(j).idNum) {
						minIndex = j;
					}
				}
				swap(aList, i, minIndex);
			}
		}
	}

	private int swap(MyArrayList2<ScoreCard> aList, int i, int j) {
		ScoreCard temp = aList.get(i);
		aList.set(i, aList.get(j));
		aList.set(j, temp);
		return i;
	}

	//	     Q6 : Complete Insertion Sort with eliminating Duplication 			
	public void addInsertionSortNoDup(int grade, ScoreCard sc) {  // 학번 오름차순 정렬되도록 add, 동일인은 추가안함
//		if (grade >= 1 && grade <= 4) {
//			int i;
//			for (i=0;i<sList.get(grade-1).size();i++) {
//				if (sList.get(grade-1).get(i).idNum>sc.idNum)
//					break;
//			}
//			sList.get(grade-1).add(i, sc);
//		} else
//			System.out.println("Invalid Grade");

		if (grade >= 1 && grade <= 4) {
			MyArrayList2<ScoreCard> aList = sList.get(grade-1);
			for (int i=0; i<aList.size(); i++) {
				if (sc.idNum==aList.get(i).idNum) {
					return;
				}
				else if (sc.idNum<aList.get(i).idNum) {
					aList.add(i, sc);
					return;
				}
			}
			aList.add(sc);
		}

	}
	
	//	     Q7 : Complete Binary Search			
	public ScoreCard search(int grade, int id) { // binary search
		if (grade>=1 && grade<=4) {
			return binarySearch(grade-1, id, 0, sList.get(grade-1).size()-1);
		}
		else
			return null;
	}
	
	private ScoreCard binarySearch(int gradeIndex, int id, int p, int r) {
		if (p>r)
			return null;
		int q = (p+r)/2;
		ScoreCard sc = sList.get(gradeIndex).get(q);
		if (sc.idNum==id)
			return sc;
		else if (sc.idNum>id)
			return binarySearch(gradeIndex, id, p, q-1);
		else
			return binarySearch(gradeIndex, id, q+1, r);
		
	}
	
	public void clear() {
		for(int i=0;i<sList.size();i++)
			sList.get(i).clear();
	}
	
	public void showList(String s) {
		System.out.println("\n< Score List : "+s+">");
		for (int i=0;i<sList.size();i++) {
			int grade =i+1;
			System.out.println("Grade"+grade+" : "+sList.get(i).toString());
		}
	}

	
	public static void main(String[] args) {
		int [][] data = { {2,2401,95,90},{1,2501,75,91},{3,2302,92,91},{2,2404,99,98},   // 학년, 학번, 중간고사점수, 기말고사점수
								{4,2202,75,98},{1,2503,56,77},{3,2303,44,67},{1,2504,59,49},
								{2,2403,74,71},{4,2203,86,79},{3,2301,66,79},{1,2505,77,86},
								{2,2403,74,71},{4,2203,86,79},{3,2301,66,79},{1,2505,77,86},
								{1,2502,99,98},{2,2402,97,73},{4,2201,67,82}};
		
		Code2 list = new Code2();
		
		for (int i=0; i<data.length;i++) {
			list.add(data[i][0], new ScoreCard(data[i][1],data[i][2],data[i][3]));
		}
		System.out.print("\n<Q4> ");
		list.showList("Unsorted");
		
		list.sortAll();
		System.out.print("\n<Q5> ");
		list.showList("Sorted with Duplication Data");
		
		list.clear();
		
		for (int i=0; i<data.length;i++) {
			list.addInsertionSortNoDup(data[i][0], new ScoreCard(data[i][1],data[i][2],data[i][3]));
		}
		System.out.print("\n<Q6> ");
		list.showList("Insertion Sort with Eliminating Duplication");
		
		// 학번 2303, 2504 학생의 성적 조회
		System.out.print("\n<Q7> ");
		System.out.println("\nBinary Search Results : 2303, 2504");
		System.out.println(list.search(3, 2303));
		System.out.println(list.search(1, 2504));

	}
}
