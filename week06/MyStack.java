package week06;



public class MyStack<T> {
	// int [] stack;
	// 실제 데이터를 저장하는 배열
	Object [] stack;
	// 스택의 최상단 위치 인덱스
	int top;
	// 스택의 최대 크기
	int stackSize;

	
	public MyStack(int n) {
		// n 크기의 스택 생성
		stackSize = n;
		stack = new Object[stackSize]; // Object 배열로 생성
		top = 0;
	}
	
	public boolean isEmpty() {
		// 스택이 비었는지 확인 (top이 0이면 비어 있음)
		return (top == 0);
	}
	
	private boolean isFull() {
		// 스택이 가득 찼는지 확인 (top이 최대 크기에 도달했는지)
		return (top==stackSize);
	}
	
	public void push(String string) {
		if (isFull()) {
			System.out.println(">>> Stack Full...");
			return;
		} else {
			// 데이터를 stack[top]에 저장하고 top을 1 증가
			stack[top++] = string;
		}
	}
	
	public void push(T value) { // 오버 로딩  
		if (isFull()) {
			System.out.println(">>> Stack Full...");
			return;
		} else {
			// 데이터를 stack[top]에 저장하고 top을 1 증가
			stack[top++] = value;
		}
	}
	
	
//	public int pop() {
//		if (isEmpty() ) {
//			System.out.println(">>> Stack Empty...");
//			return -999;
//		} else {
//			return (int) stack[--top];
//			
//		}
//		
//	}

	// top 포인터를 하나 줄임으로써 다음 push에서 그 위치를 덮어쓰게 되는 방식
	
	public T pop() { // 오버로딩은 아규먼트가 달라야 오류 안 남 -> 그래서 스트링 pop은 아직은 보류 
		if (isEmpty() ) {
			System.out.println(">>> Stack Empty...");
			return null;
		} else {
			// 스택이 비어있지 않으면 top을 먼저 줄이고 해당 위치의 값을 꺼냄
			// Object 타입에서 T로 형변환(casting) 필요
			return (T) stack[--top];
		}
	}
	

	public void showStack() {
		for (int i=0; i<top; i++) {
			System.out.print("  "+stack[i].toString());
		}
		System.out.println();
	}



}
