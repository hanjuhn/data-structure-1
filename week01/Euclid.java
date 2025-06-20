package week01;

public class Euclid {

	// 유클리드 호제법이란 두 수의 최대공약수를 구하는 것
	// 큰 수를 작은 수로 나눈 나머지를 구한다
	// 1112(a) mod 695(b) = 417(r)
	// 그 다음, 나눴던 수와 나머지로 또 mod 연산을 한다
	// 695(a) mod 417(b) = 278(r)
	// 이 과정을 계속 반복한다 -> 나머지가 0이 됐을 때 마지막 계산에서 나누는 수로 사용된 139
	// 278 mod 139(b) = 0
	
	public static void main(String[] args) {
		int a = 424;
		int b = 36;
		
		System.out.println("GCD in Recursion =" + GCDRec(a, b));
		
		while(true) {
			if(a<b) {
				int temp = a;
				a=b;
				b=temp; // a가 b보다 작으면 두 값을 서로 교환함
						// 항상 큰 수를 작은 수로 나누는 구조이기 때문에 무조건 a>=b
			}
			int r = a%b;
			if (r==0) {
				System.out.println("GCD=" + b);
				break;
			}
			else {
				a=b;
				b=r;
			}
		}
	}

		private static int GCDRec(int a, int b) {
			if(a<b)
				return GCDRec(b,a);
				else {
					int r = a%b;
					if (r==0)
						return b;
					else
						return GCDRec(b,r);

					}
			}
}
