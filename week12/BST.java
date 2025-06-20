package week12;

// 이진 탐색 트리 (Binary Search Tree)

public class BST {

	// 내부 클래스: 노드 정의
	class Node {
		int key;
		Node left, right;
		
		Node(int d){
			key = d;
			left=null;
			right=null;
		}
		
		public String toString() {
			return ""+key+" ";
		}
	}
	
	Node root;

	// 생성자: 빈 트리 생성
	public BST() {
		root=null;
	}

	// 외부에서 삽입 호출 시 사용
	public void insert(int d) {
		if (root == null) {
			root = new Node(d); // 루트가 없으면 새로 생성
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
			// 루트가 있으면 재귀적으로 삽입
			insert(root, d);

		}
	}

	// 재귀적으로 삽입 수행
	private Node insert(Node node, int d) {
		if (node == null) {
			return new Node(d);
		}
		if (d < node.key) {
			node.left = insert(node.left, d);
			return node;
		}
		else {
			node.right= insert(node.right, d);
			return node;
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

	// 외부에서 검색 호출
	public boolean search(int d) {
		return search(root, d);
	}

	// 재귀적 검색
	private boolean search(Node node, int d) {
		if (node == null) {
			return false;
		}
		if (d == node.key) {
			return true;
		}
		if (d <node.key) {
			return search(node.left, d);
		}
		else {
			return search(node.right, d);
		}
	}

	// 삭제 함수
	public void delete(int d) {
		Node parent = null;
		Node node = root;

		// 삭제할 노드 탐색
		while(node!=null && node.key!=d) {
			parent = node;
			if (d < node.key) {
				node = node.left;
			}
			else {
				node = node.right;
			}
		}
		// 삭제할 노드를 찾은 경우
		if (node!=null) {  // node.key == d 이거랑 같은 말이다
			if (node == root) {
				root = deleteANode(root); // 루트 노드를 삭제할 경우
			}
			else {
				// 부모 노드에서 자식 링크 변경
				if (node.key < parent.key) {
					parent.left = deleteANode(node);
				}
				else {
					parent.right = deleteANode(node);
				}
			}
		}
	}


	// successor 사용하여 구현
//	private Node deleteANode(Node node) {
//		// case 1 : no child
//		if (node.left == null && node.right == null) {
//			return null;
//		}
//		// case 2 : 1 child
//		else if (node.left == null && node.right != null) {  // node.right는 null이 아니야
//			return node.right;
//		}
//		else if (node.left != null && node.right == null) {
//			return node.left;
//		}
//		// case 3 : 2 children
//		else {
//			// successor or predecessor 중 하나를 선택 -> 여기서는 successor를 선택하여 구현
//			// predecessor 구현은 한 번 너네들이 해봐라 -> 숙제다 !
//			Node s = node.right;
//			Node parent = node;
//			while (s.left != null) {
//				parent = s;
//				s = s.left;
//			}
//
//			// 빠져나온 현재 s는 successor를 의미한다
//			node.key = s.key;
//			if (s == node.right) {
//				node.right = s.right;  // s가 지워졌다는 뜻
//			}
//			else {
//				parent.left = s.right;
//			}
//			return node;
//		}
//	}


	// predecessor 사용하여 구현
	// 노드 삭제 로직: predecessor(전임자) 방식
	private Node deleteANode(Node node) {
		// case 1 : no child (자식이 없음)
		if (node.left == null && node.right == null) {
			return null;
		}
		// case 2 : 1 child (한쪽 자식만 있음)
		else if (node.left == null && node.right != null) {
			return node.right;
		}
		else if (node.left != null && node.right == null) {
			return node.left;
		}
		// case 3 : 2 children (양쪽 자식 모두 있음)
		else {
			// 왼쪽 서브트리에서 가장 큰 값 찾기 (predecessor)
			Node p = node.left;
			Node parent = node;
			while (p.right != null) {
				parent = p;
				p = p.right;
			}

			// predecessor의 값을 현재 노드로 복사
			node.key = p.key;

			// predecessor 노드 제거
			if (p == node.left) {
				node.left = p.left;  // p의 오른쪽은 null이므로 왼쪽만 고려
			} else {
				parent.right = p.left;
			}

			return node;
		}
	}




	public static void main(String[] args) {
		int [] keys = {4,7,5,1,0,3,9,2,6,8};
		

		BST t = new BST();
		
		for (int i=0; i<keys.length;i++) {
			t.insert(keys[i]);
			t.showTree();
		}
		
		
		System.out.println(t.search(3));
		System.out.println(t.search(11));
		
		t.delete(3);
		System.out.println("\nAfter delete 3 (case2 : only left child)");
		t.showTree();
		t.delete(4);
		System.out.println("\nAfter delete 4 (case3) ");
		t.showTree();
		t.delete(2);
		System.out.println("\nAfter delete 2 (case1 : right end) :");
		t.showTree();
		t.delete(10);
		System.out.println("\nAfter delete 10 (Not Found) :");
		t.showTree();

	}

}
