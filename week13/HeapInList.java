package week13;

import java.util.ArrayDeque;
import java.util.Deque;

// Linked List 기반 이진 트리 구조로 구현한 최대 힙(Max Heap)
// 완전 이진 트리 구조를 유지하면서 각 노드의 값(여기서는 char)이 부모보다 작지 않도록 보장하는 최대 힙을 구현

public class HeapInList {

	// 노드 클래스: 이진 트리 기반 힙 구성
	class Node{
		char priority; // 우선순위 (문자)
		Node left, right, parent;
		public Node(char val, Node l, Node r, Node p) {
			priority=val;
			left=l;
			right=r;
			parent=p;
		}
		public String toString() {
			return " "+priority;
		}
	}
	
	Node heapRoot; // 힙의 루트 노드
	Node last; // 마지막에 삽입된 노드 (완전 이진 트리 위치 계산에 사용)

	// 힙 생성자
	public HeapInList() {
		heapRoot=null;
		last=null;
	}

	// 힙에 노드 삽입
	public void insert(char c) {
		Node parentOfNext=null;
		
		if (heapRoot==null) {
			// 첫 번째 노드 삽입
			heapRoot=new Node(c, null, null, parentOfNext);
			last=heapRoot;
		}
		else if (heapRoot==last) {
			// 두 번째 노드 → 루트의 왼쪽 자식
			parentOfNext=heapRoot;
			heapRoot.left=new Node(c, null, null, parentOfNext);
			last=heapRoot.left;
		}
		else if (last==last.parent.left) {
			// 왼쪽 자식 → 오른쪽 자식으로 삽입
			parentOfNext=last.parent;
			parentOfNext.right=new Node(c, null, null, parentOfNext);
			last=parentOfNext.right;
		}
		else {
			// 새로운 층의 가장 왼쪽 노드 탐색
			parentOfNext=last;
			while (parentOfNext.parent!=null && parentOfNext==parentOfNext.parent.right)
				parentOfNext=parentOfNext.parent;
			
			if (parentOfNext.parent!=null ) // ie. left child
				parentOfNext=parentOfNext.parent.right;
			
			while (parentOfNext.left!=null)
				parentOfNext=parentOfNext.left;

			// 해당 위치에 왼쪽 자식으로 삽입
			parentOfNext.left=new Node(c, null, null, parentOfNext);
			last=parentOfNext.left;
		}
		// 힙 조건 복구 (위로 올라가며)
		fixUpward(last);
	}

	// 위로 올라가며 부모와 우선순위 비교 및 교환
	private void fixUpward(Node node) {
		if (node ==null || node.parent==null)
			return;
		if (node.priority > node.parent.priority) {
			swap(node, node.parent);
			fixUpward(node.parent);
		}
	}

	// 두 노드의 우선순위 값 교환
	private void swap(Node a, Node b) {
		char temp = a.priority;
		a.priority=b.priority;
		b.priority=temp;
	}

	// 힙의 원소들을 레벨 순서대로 출력
	public void showHeap() {
		Node node = heapRoot;
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(node);
		while(!q.isEmpty()) {
			Node p = q.removeFirst();
			System.out.print(p.toString());
			if (p.left!=null) q.add(p.left);
			if (p.right!=null) q.add(p.right);
		}
//		showLevel();
	}

	// 트리 구조 출력
	public void showLevel() {
		showLevel(heapRoot);
	}
	private void showLevel(Node node) {
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(node);
		int curLevel =-1;
		while(!q.isEmpty()) {
			Node p = q.removeFirst();
			if (curLevel < level(p)) {
				curLevel++;
				System.out.print("\nLevel "+curLevel+" : ");
			}
			System.out.print(p.priority);
			if (p.left!=null) q.add(p.left);
			if (p.right!=null) q.add(p.right);
		}
	}

	// 특정 노드의 루트부터의 깊이 계산
	private int level(Node node) {
		if (node==heapRoot)
			return 0;
		else
			return 1+level(node.parent);
	}

	// 루트 삭제 (최대 힙 기준, 우선순위가 가장 큰 문자 반환)
	public Character delete() {
		char retVal;
		if (heapRoot==null)
			return null;
		retVal=heapRoot.priority;
		if (last==heapRoot) {
			// 마지막 노드가 루트 → 트리 비움
			last=null;
			heapRoot=null;
		}
		else {
			// 마지막 노드 값을 루트에 복사
			heapRoot.priority=last.priority;
			Node newLast;
			
			if (last==last.parent.right) {
				// 오른쪽 자식인 경우 → left를 newLast로 설정
				newLast=last.parent.left;
				last.parent.right=null;
				last = newLast;
			}
			else { // ie. last == left child !
				// 왼쪽 자식인 경우 → 새로운 마지막 노드 탐색
				newLast= last;
				while (newLast.parent != null && newLast == newLast.parent.left) {
					newLast=newLast.parent;
				}
				if (newLast.parent!=null) {
					newLast = newLast.parent.left;
				}
				while (newLast.right!=null) {
					newLast=newLast.right;
				}
				last.parent.left=null;
				last=newLast;
			}
		}
		// 힙 조건 복구 (아래로 내려가며)
		fixDownward(heapRoot);
		return retVal;
	}

	// 아래로 내려가며 자식 노드들과 비교해 힙 조건 복구
	private void fixDownward(Node node) {
		if (node==null || node.left==null)
			return;
		Node larger =node.left;
		if (node.right!=null && node.right.priority >node.left.priority)
			larger = node.right;
		if (larger.priority > node.priority)
			swap(larger, node);
		fixDownward(larger);
	}

	// 메인 함수: 삽입 → 힙 상태 출력 → 삭제를 통한 정렬
	public static void main(String[] args) {
		HeapInList heap = new HeapInList();

		// 알파벳 대문자 A~Z 삽입
		for (int i=0;i<26;i++) {
			heap.insert((char)('A'+i));
			System.out.println();

			heap.showHeap();
		}

		System.out.println("\n<< Tree Created >>");
		heap.showHeap();
		
		heap.showLevel();
		
		System.out.println("\n<< Sorted List >>\n");

		// 삭제 반복 → 힙 정렬 결과 출력
		for (int i=0;i<26;i++) {
			System.out.println();
			heap.showHeap();
			heap.delete();
		}
		
	}
}
