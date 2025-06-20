package week02;

public class Search {


	public int seqS(int[] d, int key) {
		// d는 데이터 배열, key는 찾아야 하는 값
		int index = 0;

		// index 값이 데이터 길이보다 작을 때까지
		while(index<d.length) {
			// 인덱스 값이 키값과 같을 때
			if(d[index]==key) {
				// index 값 리턴
				return index;
			}
			else
				// 그게 아니면 인덱스 ++ 시킴
				index++;
		}
		return -1;
	}


	public int seqSortedS(int[] d, int key) {
		
		int index = 0;
//		while(index<d.length) {
//			if(d[index]==key) 
//				return index;
//				else if (d[index]>key)
//					return -1;
//				else
//					index++;
//			}
//			return -1;

		//  index 값이 데이터 길이보다 작을 때까지 + 데이터 배열의 인덱스가 키 값보다 작거나 같을 때까지
		while(index<d.length && d[index]<=key) {
			if(d[index]==key) 
				return index;
			else
				index++;
			}
			return -1;	
	}



	public int binS(int[] d, int key, int s, int e) {

		// s는 start 인덱스 / e는 end 인덱스
		if(s>e) {
			return -1;
		}
		// 중간값 정의
		int mid =(s+e)/2;

		// key 값과 mid 값 같으면 mid 리턴
		if (key==d[mid]) {
			return mid;
		}

		// key 값이 mid 값보다 작으면
		else if (key<d[mid]) {
			// start 인덱스는 그대로, 마지막 인덱스를 mid 값-1로 리턴해서 왼쪽 반쪽 재탐색
			return binS(d, key, s, mid-1);
		}
		else // key>d[mid] 경우
			// 마지막 인덱스는 그대로, start 인덱스를 mid 값+1로 리턴
			return binS(d, key, mid+1, e);
	}


	public int binSIter(int[] d, int key, int s, int e) {
		int mid =(s+e)/2;

		while (s<=e) {
			if(key==d[mid]) {
				return mid;
			}
			else if (key<d[mid]) {
				return mid-1;
			}
			else
				return mid+1;
		}
		return -1;
	}





	public int seqSRec(int[] d, int n, int key) {
		// d는 data 배열, n은 데이터 길이, key는 찾아야 하는 값
		// 데이터 길이
		if (n==0) {
			return -1;
		}
		// 배열의 마지막 값이 key 값과 같다면 해당 값을 리턴
		else if (d[n-1]==key) {
			return n-1;
		}
		// 그게 아니면 다시 data 배열, 데이터 길이(data.length)-1
		else
			return seqSRec(d, n-1, key);
	}


	
	
	public static void main(String[] args) {
		int[] data = {10, 45, 40, 20, 15, 50, 25, 5, 30, 35, 55};
		int[] sData = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
		
		Search me = new Search();
		
		System.out.println("순차 탐색: " + me.seqS(data, 25));
		System.out.println("정렬된 배열 순차 탐색: " + me.seqSortedS(sData, 25));
		System.out.println("이진 탐색: " + me.binS(sData, 25, 0, sData.length - 1)); // sData.length - 1는 end index
		System.out.println("이진 탐색 Iteration: " + me.binSIter(sData, 25, 0, sData.length - 1)); // sData.length - 1는 end index
		System.out.println("재귀 순차 탐색: " + me.seqSRec(data, data.length, 25));
	}




}

