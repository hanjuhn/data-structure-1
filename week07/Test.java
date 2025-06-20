package week07;

public class Test {

	// 제네릭 리스트 클래스 MyArrayList2를 활용해서 리스트 안에 리스트를 저장하고 조작하는 예제
	// 이중 리스트(2차원 구조처럼)
	
	public static void main(String[] args) {
		// MyArrayList2의 요소 타입으로 MyArrayList1을 사용
		// 즉 list는 MyArrayList1 객체들을 담는 리스트임
		// 결과적으로 list는 리스트들의 리스트 역할을 함
		// 리스트가 MyArrayList1 객체들을 여러 개 저장하고 있는 구조
		MyArrayList2<MyArrayList1> list = new MyArrayList2<>();
		
		MyArrayList1 a = new MyArrayList1();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);

		MyArrayList1 b = new MyArrayList1();
		b.add(11);
		b.add(21);
		b.add(31);
		
		list.add(a);
		list.add(b);
		
		list.showList();
		
		list.get(0).set(2, 30);
		list.showList();

	}



	// Test Stack
//	// 스택의 요소로 MyArrayList1 사용 → 2차원 배열처럼 됨
//	NewStack1<MyArrayList1> stack2D = new NewStack1<>();
//
//	// 첫 번째 행 (1차원 배열)
//	MyArrayList1 row1 = new MyArrayList1();
//        row1.add(1);
//        row1.add(2);
//        row1.add(3);
//
//	// 두 번째 행
//	MyArrayList1 row2 = new MyArrayList1();
//        row2.add(4);
//        row2.add(5);
//        row2.add(6);
//
//	// 세 번째 행
//	MyArrayList1 row3 = new MyArrayList1();
//        row3.add(7);
//        row3.add(8);
//        row3.add(9);
//
//	// 스택에 각 행 추가 (2차원 배열처럼 저장됨)
//        stack2D.push(row1);
//        stack2D.push(row2);
//        stack2D.push(row3);


}
