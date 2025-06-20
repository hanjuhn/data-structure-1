package week13;

// 배열(Array) 기반 최소 힙(Min Heap) 자료구조
// 주어진 MinHeap 클래스는 문자(char)를 저장하고 우선순위가 낮은 문자부터 꺼낼 수 있는 자료구조로 동작

public class MinHeap {

	char [] data; // 힙을 구성하는 배열
	int size; // 현재 힙에 삽입된 원소 수 (== 마지막으로 삽입된 인덱스)


	// 힙 생성자: 최대 크기를 지정하여 배열 초기화
	MinHeap(int max) {
		data=new char[max];
		data[0]=' '; // index 0은 사용하지 않음 (1-based index 사용)
		size=0;
	}

	// 힙에 새로운 원소 삽입
	public void insert(char c) {
		data[++size]=c; // 다음 위치에 삽입
		heapifyUpward(size); // 위로 올라가며 힙 조건 복구
	}

	// 위로 올라가며 부모와 비교해 최소 힙 조건 유지
	private void heapifyUpward(int k) {
		int parent =k/2;
		if (parent>0) {
			if (data[k]<data[parent]) {
				swap(parent, k);
				heapifyUpward(parent); // 재귀적으로 올라감
			}
		}
	}

	// 두 인덱스의 원소를 교환
	private void swap(int i, int j) {
		char temp=data[i];
		data[i]= data[j];
		data[j]=temp;
	}

	// 최소값 삭제 (루트 노드 제거)
	public Character delete() {
		Character ret=null;
		if (size>=1) {
			ret=data[1]; // 루트 저장
			data[1]=data[size]; // 마지막 원소를 루트로 이동
			data[size]=' '; // 마지막 자리 비움
			size--;
			heapifyDownward(1); // 아래로 내려가며 힙 조건 복구
		}
		return ret;
	}

	// 아래로 내려가며 자식과 비교해 최소 힙 조건 유지
	private void heapifyDownward(int k) {

		int left=2*k;
		int right = 2*k+1;
		int smaller = left;

		if (right<=size) {
			if (data[left]>data[right])
				smaller=right;
		}
		else if (left>size)
			return; // 자식 없음
		if (data[smaller]<data[k]) {
			swap(smaller, k);
			heapifyDownward(smaller); // 재귀적으로 내려감
		}
	}
	//////////////////////////////////////////////////////////////
	// 힙의 원소를 순서대로 출력
	public void showHeap() {
		for (int i=1;i<=size;i++)
			System.out.print(data[i]);
		System.out.println();
	}

	// 힙을 트리 구조로 레벨별 출력
	public void showLevel() {
		int h=height();
		for (int level=0;level<=h;level++) {
			System.out.print("\n Level "+level+" : ");
			int levelStart=(int) Math.pow(2,level);
			int levelEnd=(int) Math.min(Math.pow(2,level+1)-1, size);
			for (int i=levelStart;i<=levelEnd;i++ )
				System.out.print(data[i]);
		}
		System.out.println();
	}

	// 힙의 높이를 계산 (완전 이진 트리 기준)
	private int height() {
		return (int)(Math.log(data.length)+1); // size 기준으로 높이 추정
	}

	// 메인 함수: 힙 삽입, 트리 출력, 삭제를 통한 정렬 시연
	public static void main(String[] args) {
		char[] data = {'q','a','z','w','s','x','e','d','c','r','f','v','t','g','b','y','h','n','u','j','m','i','k','o','l','p'};

		// 힙 생성 (최대 원소 수 + 1)
		MinHeap heap = new MinHeap(data.length+1);

		// 힙에 데이터 삽입 및 상태 출력
		for (int i=0;i<data.length;i++) {
			heap.insert(data[i]);
			heap.showHeap();
		}

		System.out.println("\n<< Tree Created >>");
		heap.showHeap();

		heap.showLevel(); // 트리 구조 출력

		System.out.println("\n\n<< Sorted List >>\n");

		// 삭제를 통해 정렬 결과 출력
		for (int i=0;i<26;i++) {
			System.out.print(heap.delete());
			heap.showHeap();
		}
		System.out.println();
	}
}
