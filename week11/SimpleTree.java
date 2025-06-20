package week11;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleTree {
	// 노드를 나타내는 내부 클래스
	class Node {
		char data;
		Node leftchild; // 왼쪽 자식 노드
		Node rightchild; // 오른쪽 자식 노드
		Node parent;

		Node(char d) {
			data = d;
			leftchild = null;
			rightchild = null;
		}

		Node(Node lc, char d, Node rc) {
			data = d;
			leftchild = lc;
			rightchild = rc;
		}

		public String toString() {
			return "" + data;
		}
	}

	Node root; // 트리의 루트 노드
	char[] array; // 트리를 배열로 표현할 때 사용하는 배열

	SimpleTree() {
		root = null;
		array = new char[10]; // 최대 9개의 노드를 저장할 수 있도록 설정
	}

	// 단일 노드 트리 생성
	public Node makeTree(char ch) {
		root = new Node(ch);
		return root;
	}

	// 왼쪽, 오른쪽 서브트리를 포함한 새 트리 생성
	public Node makeTree(SimpleTree leftSubtree, char ch, SimpleTree rightSubtree) {
		root = new Node(leftSubtree.root, ch, rightSubtree.root);
		return root;
	}

	// 트리를 여러 방식으로 출력
	public void showTree() {
		System.out.println("\n<Tree Expression in Nodes>");
//		showTree(root);

		inorder(root);
		System.out.println();
		preorder(root);
		System.out.println();
		postorder(root);
		System.out.println();
		levelOrder(root);
		System.out.println();
	}

	private void showTree(Node p) {
		if (p != null) {
			System.out.print(p.data);
			showTree(p.leftchild);
			showTree(p.rightchild);
		}
	}


	// 중위 순회 (Left → Root → Right)
	public void inorder(Node p) {
		if (p != null) {
			inorder(p.leftchild);
			System.out.print(p.data);
			inorder(p.rightchild);
		}
	}

	// 전위 순회 (Root → Left → Right)
	public void preorder(Node p) {
		if (p != null) {
			System.out.print(p.data);
			preorder(p.leftchild);
			preorder(p.rightchild);
		}
	}

	// 후위 순회 (Left → Right → Root)
	public void postorder(Node p) {
		if (p != null) {
			postorder(p.leftchild);
			postorder(p.rightchild);
			System.out.print(p.data);
		}
	}

	// 레벨 순서 순회 (BFS 순회)
	public void levelOrder(Node p) {
		Deque<Node> queue = new ArrayDeque<>();

		queue.addLast(root);

		while (!queue.isEmpty()) {
			Node node = queue.removeFirst();
			if (node != null) {
				System.out.print(node.data);
				if (node.leftchild != null) queue.addLast(node.leftchild);
				if (node.rightchild != null) queue.addLast(node.rightchild);
			}
		}
	}


	// 트리를 배열로 표현 (배열 인덱스를 이진 트리 규칙대로 설정)
	public void toArray() {
		toArray(root, 1);
		System.out.println("\n<Tree Expression in Array>");
		for (int i = 1; i <= 7; i++)
			System.out.print("[" + i + "]" + array[i] + " ");
		System.out.println();
	}

	// 재귀적으로 배열에 트리 노드 채우기
	private void toArray(Node p, int index) {
		if (p != null) {
			array[index] = p.data;
			toArray(p.leftchild, index * 2);
			toArray(p.rightchild, index * 2 + 1);
		}
	}

	// 노드 수 반환
	public int getNodeCount() {
		return getNodeCount(root);
	}

	private int getNodeCount(Node node) {
		if (node == null)
			return 0;
		else
			return 1 + getNodeCount(node.leftchild) + getNodeCount(node.rightchild);
	}

	// 트리의 높이 반환
	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node node) {
		if (node == null)
			return 0;
		else
			return 1 + Math.max(getHeight(node.leftchild), getHeight(node.rightchild));
	}

	// 트리에 특정 값이 존재하는지 확인
	public boolean contains(char d) {
		return contains(root, d);
	}

	private boolean contains(Node node, char d) {
		if (node == null) {
			return false;
		}
		else {
			if (node.data == d) {
				return true;
			}
			else {
				return contains(node.leftchild, d) || contains(node.rightchild, d);
			}
		}
	}

	// delete 재귀 버전
	// 트리에서 특정 값의 노드를 삭제 (해당 노드와 서브트리 모두 삭제됨)
	public void delete(char d) {
		root = deleteRecursively(root, d);
	}

	private Node deleteRecursively(Node node, char d) {
		if (node == null)
			return null;

		if (node.data == d)
			return null; // 노드 및 서브트리 삭제

		node.leftchild = deleteRecursively(node.leftchild, d);
		node.rightchild = deleteRecursively(node.rightchild, d);
		return node;
	}

	// search 재귀 + public 래퍼
	// 재귀적으로 노드 검색
	public Node search(char d) {
		return search(root, d);
	}

	private Node search(Node node, char d) {
		if (node == null)
			return null;
		if (node.data == d)
			return node;

		Node temp = search(node.leftchild, d);
		if (temp != null)
			return temp;
		else
			return search(node.rightchild, d);
	}

	public static void main(String[] args) {
		// 수식: a*b + c%d
		SimpleTree t1 = new SimpleTree();
		t1.makeTree('a');
		SimpleTree t2 = new SimpleTree();
		t2.makeTree('b');
		SimpleTree t3 = new SimpleTree();
		t3.makeTree('c');
		SimpleTree t4 = new SimpleTree();
		t4.makeTree('d');

		SimpleTree t5 = new SimpleTree();
		t5.makeTree(t1, '*', t2);
		SimpleTree t6 = new SimpleTree();
		t6.makeTree(t3, '%', t4);

		SimpleTree t7 = new SimpleTree();
		t7.makeTree(t5, '+', t6);

		t7.showTree();
		t7.toArray();

		System.out.println("\nNumber of Nodes = " + t7.getNodeCount());
		System.out.println("Height of Tree = " + t7.getHeight());

		t7.inorder(t7.root);
		t7.preorder(t7.root);
		t7.postorder(t7.root);

		System.out.println("\nSearching for node 'c': " + t7.search('c'));
		System.out.println("Searching for node 'x': " + t7.search('x'));

		t7.delete('c');
		t7.inorder(t7.root);  // 삭제 후 중위 순회로 확인
	}
}