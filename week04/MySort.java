package week04;

import java.util.Arrays;

public class MySort {

	// Sorting(정렬) 알고리즘


	// Selection Sort Iteration
	public int[] selectionSort(int[] data) {
		// 배열에서 가장 큰 값을 찾아 마지막 위치와 교환하는 방식으로 정렬
		// 가장 큰 값을 찾아서 배열의 끝(index i)과 교환하는 방식


		int n = data.length;

		// i는 현재 정렬해야 할 마지막 위치(index)
		// 그 최댓값을 마지막으로 보내기 위한 위치
		// 전체 배열이 10개라면, 먼저 마지막 위치(인덱스 9)에 가장 큰 수를 놓고, 그다음 인덱스 8에 두 번째 큰 수를 놓는 방식
		for (int i=n-1; i>0; i--) {

			// 최댓값이 있는 위치 저장
			int maxIndex = i;

			for (int j=0; j<i; j++) {
				// max 인덱스가 j 인덱스보다 클때까지 j를 maxIndex에 넣는다
				// 아직 정렬되지 않은 부분에서 반복하면서 최댓값을 찾아냄
			if (data[j]>data[maxIndex]) {
				maxIndex = j;
				}
			}

			// maxIndex를 i로 바꿈
			swap(data, maxIndex, i);
		}
		return data;
	}


	public void selectionSortMin(int[] data) {
		// min index 값 활용
		int n = data.length;

		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;

			// i 이후에서 가장 작은 값의 인덱스 찾기
			for (int j = i + 1; j < n; j++) {
				if (data[j] < data[minIndex]) {
					minIndex = j;
				}
			}

			// i번째 값과 가장 작은 값을 교환
			if (minIndex != i) {
				int temp = data[i];
				data[i] = data[minIndex];
				data[minIndex] = temp;
			}
		}
	}


	
	private int[] swap(int [] d, int i, int j) {
		int temp = d[i];
		d[i] = d[j];
		d[j] = temp;
		return d;
	}
	
	// Insertion Sort Iteration
	public int[] insertionSort(int[] data) {
		// 이미 정렬된 부분과 비교하며 새로운 값을 삽입

		// n은 배열의 크기
		int n = data.length;

		// i = 1부터 n-1까지 반복하며 두번째 원소부터 마지막 원소까지 차례로 정렬된 부분과 비교
		for (int i=1; i<n; i++) {
			// 현재 값(value) 저장
			int value = data[i];
			int j = i-1; // value 보다 작은 값을 찾아서 삽입할 위치를 찾기 위해 j를 i-1로 설정

			// j가 0 이상이고, data[j]가 value 보다 큰 경우 반복하여 j를 하나씩 감소시킴
			while(j>=0 && (data[j] > value)) {
				j--; // j를 하나씩 왼쪽으로 이동시키며 value 보다 작은 값을 찾아감
				
			}
			int myPosition = j+1;
			int k = i-1;
			while (k>= myPosition) {
				data[k+1] = data[k];
				k--;
			}
			data[myPosition] = value;
	
//				if (data[j] > value) -> j--
//				if (data[j] < value) -> j 뒤에 삽입 -> j+1 ~ i-1을 하나씩 뒤로 민다 
				
		}
		return data;
	}

	
	// Bubble Sort Iteration
	public int[] bubbleSort(int[] data) {
		// 인접한 두 값을 비교하여 swap

		// 배열의 크기를 n에 저장
		int n = data.length;

		// 배열의 끝에서부터 시작하여 비교할 범위를 점차 줄여나감
		for (int i=n-1; i>=0; i--) {
			// i는 비교해야 할 범위의 끝 지점을 의미
			// 배열의 크기가 n이면, i는 n-1부터 시작해서 1까지 감소함
			for (int j=0; j<i; j++)
				// 인접한 두 원소를 비교하여 큰 값이 오른쪽으로 가도록 교환
				if (data[j] > data[j+1])
					swap(data, j, j+1); // swap 메소드를 이용해 데이터를 교환
		}
		return data; // 정렬된 배열 반환
		
	}
	
	
	
//	public int[] selectionSort2(int[] data) {
//			if (n==0) {
//				return data;
//			} else {
//				int maxIndex = n;
//				for (int j=0; j<i-1;j++) {
//					if (data[j]>data[maxIndex]) {
//						maxIndex = j;
//						}
//					swap(data, maxIndex, i);
//				
//					return selectionSort2(data, n-1);
//			}
//			}
//
//		}

	// Quick Sort Recursion
	public int[] quickSort(int[] data) {
		// 데이터를 인자로 받아서, 0번 인덱스부터 마지막 인덱스까지를 기준으로 quickSort를 호출

		return quickSort(data, 0, data.length-1);
	}
	
	
	private int[] quickSort(int[] data, int p, int r) { // overloading
		// 분할 정복 방식으로 배열을 정렬하는 재귀적 메서드
		if(p<r) {
			// p가 r보다 작은 경우에만 배열을 나누고 정렬
			int q = partition(data, p, r); // 배열을 분할하여 피벗을 기준으로 분리된 인덱스를 반환받음
			quickSort(data, p, q-1); // 왼쪽 부분 배열 정렬
			quickSort(data, q+1, r); // 오른쪽 부분 배열 정렬
		}
	return data;
	}

	// Quick Sort Iteration
	public int[] quickSortIterative(int[] data) {
		int[] stack = new int[data.length];

		int top = -1;

		// 초기 구간 push
		stack[++top] = 0;
		stack[++top] = data.length - 1;

		while (top >= 0) {
			// 범위 pop
			int r = stack[top--];
			int p = stack[top--];

			// 파티션 수행
			int q = partition(data, p, r);

			// 왼쪽 구간 push
			if (q - 1 > p) {
				stack[++top] = p;
				stack[++top] = q - 1;
			}

			// 오른쪽 구간 push
			if (q + 1 < r) {
				stack[++top] = q + 1;
				stack[++top] = r;
			}
		}

		return data;
	}


	private int partition(int[] data, int p, int r) {
		// 퀵 정렬에서 배열을 피벗을 기준으로 두 부분으로 나누고 피벗의 최종 위치를 반환
		
//		int pivot = r;
//		
//		int left = p;
//		int right = r;
//		
//		while(left<right) {
//			while(data[left]<data[pivot]) {
//				left++;
//			}
//			while(right>left && data[right]>= data[pivot]) {
//				right--;
//			}
//			if (left<right) {
//				swap(data, left, right);
//			}
//		}
//		swap(data, pivot, left);
//
//		return left; // == right
		
		
		int x = data[r]; // 피벗 값 설정 (배열의 마지막 값)
		int k = p-1; // 왼쪽 부분 배열의 마지막 인덱스
		
		for (int i=p; i<r; i++) {
			if(data[i]<x) { // 현재 값이 피벗보다 작으면
				swap(data, ++k, i); // k를 증가시키고, data[k]와 data[i]를 교환
			}
		}
		swap(data, k+1, r); // 피벗을 정렬된 위치에 배치
		return k+1; // 피벗이 놓인 인덱스를 반환
	}



	
	// Marge Sort Recursion
	// 전체 배열을 반으로 나누고 각각을 정렬한 뒤 병합(merge)하여 전체를 정렬하는 방식
	public int[] mergeSort(int[] data) {

		return mergeSort(data, 0, data.length-1);
	}
	

	private int[] mergeSort(int[] data, int p, int r) {
		
		if (p<r) {
			// 배열을 두 개의 부분 배열로 나누기
			int q = (p+r)/2; // 가운데 인덱스를 계산
			mergeSort(data, p, q); // 왼쪽 부분 배열 정렬
			mergeSort(data, q+1, r); // 오른쪽 부분 배열 정렬
			merge(data, p, q, r); // 두 정렬된 부분 배열 병합
		}
		return data;
	}

	
	

	private int [] merge(int[] data, int p, int q, int r) {
		int [] temp = new int[data.length]; // 데이터를 임시로 저장할 배열 생성
		
		// merge {data[p], ... data[q]} and {data[q+1], ... data[r]}
		
		int i = p; // 왼쪽 부분 배열의 시작 인덱스
		int j = q+1; // 오른쪽 부분 배열의 시작 인덱스
		int k = p; // 병합된 배열의 시작 인덱스

		// 두 부분 배열을 병합
		while(i<=q && j<=r) {
			if(data[i]<data[j]) {
				temp[k++] = data[i++]; // 왼쪽 배열의 값이 더 작으면 temp[k]에 저장
			} else {
				temp[k++] = data[j++]; // 오른쪽 배열의 값이 더 작거나 같으면 temp[k]에 저장
			}
		}
		// 왼쪽 배열에 남은 값이 있으면 모두 복사
		while(i<=q) {
			temp[k++] = data[i++];
		}
		// 오른쪽 배열에 남은 값이 있으면 모두 복사
		while(j<=r) {
			temp[k++] = data[j++];
		}
		// 병합된 배열을 원래 배열에 복사
		for(int l=p; l<=r; l++) {
			data[l] = temp[l];
		}
		return data; // 병합된 결과 배열을 반환
	}


	
	

	public static void main(String[] args) {
		int [] data = {113,  336,  74,  71,  86,  176,  313,  80,  225,  342,
				  170,  292,  275,  266 , 79,  16,  109,  175 , 245,  156,
				  50,  61,  277,  167,  81,  24,  76,  186,  78,  101,
				  301,  62,  152,  219,  294};

		System.out.println("\n< Initial Data >");
		showData(data);
		
		MySort ms = new MySort();
		// deep copy data to another array, dataSorted, for argument...
		// call each sort method
		// showData(dataSorted)
		
		// shallow copy (얕은 복사)
		int [] sample = new int [35];
		sample = data;
		int [] sample2 = data;
		
		// deep copy (깊은 복사) 방법 1
		for (int i=0; i<20; i++) {
			sample[i] = data[i];
		}
		
		// deep copy (깊은 복사) 방법 2
		sample = data.clone();
		
		// deep copy (깊은 복사) 방법 3
		// 부분만 copy 하고 싶으면 data.length 대신에 숫자 넣기  
		sample = Arrays.copyOf(data, data.length);
		
		// deep copy (깊은 복사) 방법 4
		System.arraycopy(data, 0, sample, 0, 20);

		
		
		
		int [] dataSorted = new int[data.length];
		
		System.out.println("\n< Quick Sort >");
		dataSorted=data.clone();
		ms.quickSort(dataSorted);
		showData(dataSorted);
		
		System.out.println("\n< Merge Sort >");
		dataSorted=data.clone();
		ms.mergeSort(dataSorted);
		showData(dataSorted);
		
		
	
		int[] toBeSorted = data.clone();
		ms.selectionSort(toBeSorted);
		System.out.println("\n< Selection Sort >");
		showData(toBeSorted);

		
		toBeSorted = data.clone();
		ms.insertionSort(toBeSorted);
		System.out.println("\n< Insertion Sort >");
		showData(toBeSorted);

		
		toBeSorted = data.clone();
		ms.bubbleSort(toBeSorted);
		System.out.println("\n< Bubble Sort >");
		showData(toBeSorted);
		
	}
	
	private static void showData(int[] data) {
		int nData = data.length;
		int nPrinted=0;
		while(nPrinted<nData) {
			for (int i=0;(nPrinted<nData)&&(i<10); i++) {
				System.out.printf("%6d ",data[nPrinted++]);
			}
			System.out.println();
		}
	}

}
