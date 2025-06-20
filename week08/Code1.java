package week08;


import java.util.Arrays;

public class Code1 {

	public int sum(int n) {
		int sum=0;
		for (int i=1;i<=n;i++)
			sum=sum+i;
		return sum;
	}

	//     Q1 : Complete sumRecursion		
	public int sumRecursion(int n) {
		if (n==1)
			return 1;
		else
			return n+sumRecursion(n-1);
	}

	//	     Q2 : Complete merge in recursion				
	public int[] merge(int[] d1, int[] d2) {
		int [] result = new int[d1.length+d2.length];
		return merge(result, 0, d1, 0, d2, 0);
	}

	private int[] merge(int[] result, int k, int[] d1, int i, int[] d2, int j) {
//		int [] temp = new int[result.length]; // 데이터를 임시로 저장할 배열 생성
//
//		while(i<d1.length && j<d2.length) {
//			if(d1[i]<d2[j]) {
//				temp[k++] = d1[i++];
//			} else {
//				temp[k++] = d2[j++];
//			}
//		}
//		while(i<d1.length) {
//			temp[k++] = d1[i++];
//		}
//		while(j<d2.length) {
//			temp[k++] = d2[j++];
//		}
//
//		for (int l=0; l<result.length; l++) {
//			result[l] = temp[l];
//		}
//		return result;

		if (k == d1.length + d2.length) {
			return result;
		}
		if (i == d1.length) {
//			if(j!=d2.length)
				System.arraycopy(d2, j, result, k, d2.length-j);
				return result;
			}

		if (j == d2.length) {
//			if(j!=d2.length)
			System.arraycopy(d1, i, result, k, d1.length-i);
				return result;
		}
		else {
			if (d1[i] <= d2[j]) {
				result[k++] = d1[i++];
			}
			else {
				result[k++] = d2[j++];
			}
			return merge(result, k, d1, i, d2, j);
		}
	}


	//	     Q3 : Complete the methods of NewQueue class
	public static class NewQueue {

		MyArrayList2<Integer> queue = new MyArrayList2<>();

		public void enqueue(int data) {
			queue.add(data);
		}

		public int dequeue() {
			if (isEmpty())
				return -999;
			Integer front = queue.get(0);
			queue.remove(0);
			return front;
        }

		public boolean isEmpty() {
			return queue.size() == 0;
		}

		public int peek() {
			if (isEmpty()) return -999;
			return queue.get(0);
		}

		public void showQueue() {
			System.out.println("Queue : "+queue.toString());
		}
	}

	public static void main(String[] args) {
		Code1 q1 = new Code1();
		System.out.println("\n<Q1> ");
		System.out.println("Sum Iteration : "+q1.sum(15));
		System.out.println("Sum Recursion : "+q1.sumRecursion(15));


		int [] d1= {10, 20, 55, 60, 80};
		int [] d2= {15, 30, 40, 75, 90};
		System.out.println("\n<Q2> ");
		System.out.println("Merge Result : "+ Arrays.toString(q1.merge(d1, d2)));

		int [] data= {3,5,4,1,7,2,9,8,0,6};
		NewQueue queue = new NewQueue();
		System.out.println("\n<Q3> ");

		for (int i=0; i<data.length;i++) {
			queue.enqueue(data[i]);
			queue.showQueue();
		}
		System.out.println("Peek Result : "+queue.peek());
		for (int i=0; i<data.length;i++) {
			queue.dequeue();
			queue.showQueue();
		}
	}
}
