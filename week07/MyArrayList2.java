package week07;

import java.util.Arrays;

public class MyArrayList2<T> {

	final int INITIAL_CAPACITY = 5;
	T[] array;
	int capacity;
	int size;

	@SuppressWarnings("unchecked")
	public MyArrayList2() {
		capacity = INITIAL_CAPACITY;

		// 제네릭 배열은 직접적으로 만들 수 없어서 우회적으로 Object 배열을 만들고 (T[])로 형변환함
		array = (T[]) (new Object[capacity]); // 크기를 예상할 수 없어서
		size = 0;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public int indexOf(T value) {
		for (int i = 0; i < size; i++) {
			// == 대신 equals()를 사용해서 값 비교
			if (array[i].equals(value)) {
				return i;
			}
		}
		return -1; // -1 means "not found"
	}

//	public int indexOf(T value) {
//		return indexOfRecursive(value, 0);
//	}
//
//	private int indexOfRecursive(T value, int index) {
//		if (index >= size) {
//			return -1; // base case: 찾지 못함
//		}
//		if (array[index].equals(value)) {
//			return index; // base case: 찾음
//		}
//		return indexOfRecursive(value, index + 1); // 다음 인덱스로 재귀 호출
//	}

	public void add(T value) {
		if (isFull()) {
			grow();
		}
		array[size++] = value;
	}

	private boolean isFull() {
		return (size == capacity);
	}

	public void add(int index, T value) {
		if (checkIndexRange(index)) { // ? 0<= index <= size-1

			if (isFull()) {
				grow();
			}
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = value;
			size++;
		} else if (index == size) {
			add(value);
		}
	}

	@SuppressWarnings("unchecked")
	private void grow() {
		T[] tempArray = (T[]) new Object[capacity * 2];
		for (int i = 0; i < capacity; i++) {
			tempArray[i] = array[i];
		}
		array = tempArray;
		capacity *= 2;
	}

	private boolean checkIndexRange(int index) {
		if (index >= 0 && index < size) {
			return true;
		} else
			return false;
	}

	public void clear() {
		Arrays.fill(array, null);
		size = 0;
	}

	public boolean contains(T value) {
		return !(indexOf(value) == -1);
	}

	public boolean remove(T value) {
		int index = indexOf(value);
		if (checkIndexRange(index)) {
			System.arraycopy(array, index + 1, array, index, size - index - 1);
			size--;
			return true;
		} else
			return false;
	}

	public T remove(int index) {
		if (checkIndexRange(index)) {
			T ret = array[index];
			System.arraycopy(array, index + 1, array, index, size - index - 1);
			size--;
			return ret;
		} else
			return null;
	}

//	public T remove(int index) {
//		if (!checkIndexRange(index)) return null;
//
//		T ret = array[index]; // 제거할 요소 저장
//		shiftLeftRecursive(index); // index부터 끝까지 한 칸씩 앞으로 당김
//		size--; // 크기 감소
//		return ret;
//	}
//	// index 위치부터 뒤의 모든 요소를 한 칸씩 앞으로 이동
//	private void shiftLeftRecursive(int index) {
//		if (index < size - 1) {
//			array[index] = array[index + 1];
//			shiftLeftRecursive(index + 1);
//		}
//	}


	public int size() {
		return size;
	}

	public T get(int index) {
		if (checkIndexRange(index)) {
			return array[index];
		}
		return null; // null은 메모리가 할당되지 않은 것
	}

	public void set(int index, T value) {
		if (checkIndexRange(index)) {
			array[index] = value;
		}
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < size; i++) {
			ret = ret + array[i].toString() + "  ";
		}
		return ret;
	}

//	public String toString() {
//		return toStringRecursive(0);
//	}
//	private String toStringRecursive(int index) {
//		if (index >= size) return "";
//		return array[index].toString() + "  " + toStringRecursive(index + 1);
//	}

	public void showList() {
		System.out.print("Current List Status: ");
		System.out.println(toString());
		System.out.println(">>> Current Capacity = " + capacity + "  Size = " + size);
	}

	public static void main(String[] args) {

		int[] data = { 113, 336, 74, 71, 86, 176, 313, 80, 225, 342, 170, 292, 275, 266, 79, 16, 109, 175, 245, 156, 50,
				61, 277, 167, 81, 24, 76, 186, 78, 101, 301, 62, 152, 219, 294 };

		MyArrayList2<Integer> list = new MyArrayList2<>();

		for (int i = 0; i < 4; i++)
			list.add(data[i]); // cf : list[i]=data[i]
		list.showList();
		for (int i = 4; i < 10; i++)
			list.add(data[i]); // cf : list[i]=data[i]
		list.showList();

		list.set(5, 999); // list[5]=999
		int x = (int) list.get(5); // cf : x = list[5]
		System.out.println("\nx = " + x);

		list.remove((Integer) 336);
		list.showList();

		list.add(3, 111);
		list.showList();
		list.add(list.size(), 222);
		list.showList();

		list.clear();
		list.showList();

		MyArrayList2<MyData> list2 = new MyArrayList2<>();

		MyData a = new MyData(1, "aaa");
		MyData b = new MyData(2, "bbb");
		MyData c = new MyData(3, "ccc");

		list2.add(a);
		list2.add(b);
		list2.add(c);

		list2.showList();

		System.out.println(list2.contains(a));
		System.out.println(list2.contains(new MyData(1, "aaa"))); // value는 같지만 주소가 다름 그럼 다르다고 생각함

	}

}
