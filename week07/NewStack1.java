package week07;

public class NewStack1<T> {

	//  제네릭 스택 클래스인 NewStack1<T>를 정의한 것
	//  내부적으로는 앞에서 만든 MyArrayList2<T> 리스트를 이용해서 스택(stack) 을 구현
	//  내부 구조는 리스트지만 스택처럼 동작하도록 메서드 제한
	
	MyArrayList2<T> stack;
	
	public NewStack1() {
		stack = new MyArrayList2<>();
	}
	
	public void push(T value) {
		stack.add(value);
	}
	
	public T pop() {
		return stack.remove(stack.size()-1);
	}

	// 스택 맨 위 값을 제거하지 않고 조회함
	public T peek() {
		return stack.get(stack.size()-1);
	}
	
	public void showStack() {
		stack.showList();
	}
	
	
	public static void main(String[] args) {

		int [] data= {3,5,4,1,7,2,9,8,6,0};
		
		
		NewStack1<Integer> stack = new NewStack1<>();
		
		for (int i=0; i<data.length;i++) {
			stack.push(data[i]);
			stack.showStack();
		}
		
		for (int i=0; i<data.length;i++) {
			stack.pop();
			stack.showStack();
		}
		
		NewStack1<String> stack2 = new NewStack1<>();
		
		stack2.push("AAA");
		stack2.push("BBB");
		stack2.showStack();
		
	}
}
