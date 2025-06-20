package week07;

public class MyData {
	int num;
	String name;

	// 예 ) MyData a = new MyData(1, "apple");
	public MyData(int n, String s) {
		num = n;
		name = s;
	}

	//
	public String toString() {
		return name+"("+num+")";
	}
	
	public boolean equals(Object that) {
		// this는 지금 호출한 객체, that은 비교 대상 객체
		return (this.num == ((MyData)that).num);
	}

}
