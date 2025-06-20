package week06.lib;

import week06.MyStack;

public class StackApp {
	
	public static void main(String[] args) {
		int [] data= {3,5,4,1,7,2,9,8,6,0};
		
		
		MyStack<Integer> stack = new MyStack<>(10);
		
		for (int i=0; i<data.length;i++) {
			stack.push(data[i]);
			stack.showStack();
		}
		
		for (int i=0; i<data.length;i++) {
			stack.pop();
			stack.showStack();
		}
		
		MyStack<String> stack2 = new MyStack<>(5);
		
		stack.push("AAA");
		stack.push("AAA");
		stack.showStack();
		
	}

}
