package week13;

import java.util.ArrayDeque;
import java.util.Deque;

// 이진 탐색 트리(BST)에 정수를 삽입한 후 왼쪽 회전(Left Rotation)을 수행하여 트리의 균형을 조정

public class BSTBalanceTest {

	// 이진 탐색 트리의 노드 클래스
	class Node {
		int key;
		Node left, right, parent;
		
		Node(int d){
			key = d;
			left=null;
			right=null;
			parent=null;
		}

		Node(int d, Node p){
			key = d;
			left=null;
			right=null;
			parent=p;
		}
		
		public String toString() {
			return ""+key+" ";
		}
	}
	
	Node root;

	// 트리 생성자
	public BSTBalanceTest() {
		root=null;
	}

	// 노드를 삽입하는 메서드 (공개용)
	public void insert(int d) {
		if (root == null) {
			root = new Node(d); // 루트가 없으면 새 노드를 루트로 설정
		} else {
//			Node parent = null;
//			Node node = root;
//			while (node != null) {
//				parent = node;
//				if (d < node.key) {
//					node = node.left;
//				} else {
//					node = node.right;
//				}
//			}
//			Node newNode = new Node(d);
//			if (d < parent.key) {
//				parent.left = newNode;
//			}
//			else {
//				parent.right = newNode;
//			}

			// advanced code ...
			Node prt = null;
			insert(root, d, prt); // 재귀 삽입 호출

		}
	}

	// 노드를 삽입하는 메서드 (재귀, 부모 연결 포함)
	private Node insert(Node node, int d, Node p) {
		if (node == null) {
			return new Node(d, p); // 새 노드 생성 시 부모도 설정
		}
		if (d < node.key) {
			node.left = insert(node.left, d, node);
			return node;
		}
		else {
			node.right= insert(node.right, d, node);
			return node;
		}
	}

	// 루트 노드를 기준으로 왼쪽 회전을 수행
	public void rotateLeft() {
		rotateLeft(root);
	}

	// 주어진 노드(pp)를 기준으로 왼쪽 회전 수행
	private void rotateLeft(Node pp) {
		if (pp == root) {
			// 루트 노드가 회전 대상일 경우
			root = pp.right; // 새로운 루트로 오른쪽 자식 설정
			pp.right.parent = null;  // 부모 제거
			pp.right.left = pp; // 기존 루트를 왼쪽 자식으로
			pp.parent = pp.right; // 루트의 부모 갱신
			pp.right = null;  // 오른쪽 자식 변경
		}
		else {
			// 자식 노드가 회전 대상일 경우
			if (pp == pp.parent.left) {
				pp.parent.left = pp.right;
				pp.right.parent = pp.parent;
				pp.right.left = pp;
				pp.parent = pp.right;
				pp.right = null;
			}
			else {  // pp == pp.parent.right
				pp.parent.right = pp.right;
				pp.right.parent = pp.parent;
				pp.right.left = pp;
				pp.parent = pp.right;
				pp.right = null;
			}
		}
	}

	// 중위 순회로 트리 출력
	public void showTree() {
		System.out.println();
		showTree(root);
		System.out.println();
	}

	private void showTree(Node p) {
		if (p == null) return;
		showTree(p.left);
		System.out.print(p.toString());
		showTree(p.right);
	}

	// 레벨 순서대로 트리 출력 (BFS)
	public void showLevel() {
		if (root != null) {
			showLevel(root);
		}
	}

	private void showLevel(Node node) {
		Deque<Node> q = new ArrayDeque<>();
		q.add(node);
		int curLevel = -1;
		while (!q.isEmpty()) {
			Node p = q.removeFirst();
			if (curLevel < level(p)) {
				curLevel++;
				System.out.print("\nLevel  "+curLevel+" : ");
			}
			System.out.print(p.key);
			if (p.left != null) {
				q.add(p.left);
			}
			if (p.right != null) {
				q.add(p.right);
			}
		}
	}

	// 루트부터 현재 노드까지의 깊이 반환
	private int level(Node node) {
		if (node == root) {
			return 0;
		}
		else {
			return 1 + level(node.parent);
		}
	}


	// 메인 함수: 노드 삽입 및 회전 테스트
	public static void main(String[] args) {
		

		BSTBalanceTest t = new BSTBalanceTest();

		// 0~14까지 삽입 (오른쪽으로 편향된 트리 생성됨)
		for (int i=0; i<15; i++) {
			t.insert(i);
			t.showTree();
		}
		
		
		System.out.println("\nBefore Rotate");
		t.showLevel();

		// 왼쪽 회전 5회 수행 (균형 조정 시도)
		t.rotateLeft();
		t.rotateLeft();
		t.rotateLeft();
		t.rotateLeft();
		t.rotateLeft();
		System.out.println("\nAfter Rotate");
		t.showLevel();

	}

}
