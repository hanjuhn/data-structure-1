package week07;

import java.util.Arrays;

public class MyArrayList0 implements MyList {
	
	
	int[] array;  // 실제 데이터를 저장할 배열
	int capacity;  // 배열의 총 용량
	int size;  // 현재 저장된 요소의 개수
	
	public MyArrayList0(int n) {
		// n을 받아 배열의 용량을 설정하고 array를 초기화
		// 아직 아무 데이터도 없기 때문에 size는 0으로 초기화
		capacity = n;
		array = new int[capacity];
		size = 0;
	}


	@Override
	public boolean isEmpty() {
		// 리스트가 비었는지 확인
		return (size==0);
	}

	@Override
	public int indexOf(Object value) {
		// 리스트에서 value와 같은 값을 갖는 첫 번째 요소의 인덱스를 반환
		for(int i=0;i<size;i++) {
			if(array[i]==(int)value) {
				return i;
			}
		}
		return -1; // -1 means "not found!"
	}

	@Override
	public void add(Object value) {
		// 리스트 맨 뒤에 값을 추가할 때 사용
		if (!isFull()) {
			array[size++] = (int)value;
		}
	}


	private boolean isFull() {
		// 배열이 꽉 찼는지 확인하는 메서드
		return (size==capacity);
	}


	@Override
	public void add(int index, Object value) {
		// 특정 위치 index에 값을 삽입하는 메서드
		// list.add(2, 10); → 인덱스 2 자리에 10 삽입, 그 뒤 값들은 한 칸 밀림
		if(checkIndexRange(index)) { // ? 0<= index <= size-1
			if(!isFull()) {
				// index부터 뒤쪽 요소들을 한 칸씩 뒤로 밀어냄 -> 이렇게 해야 index 자리가 비어 새로운 값을 넣을 수 있음
				for (int i=size-1; i>=index; i--) {
					array[i+1] = array[i];
				
//				System.arraycopy(array, index, array, index+1, size-index); // 위에 for문 두줄이랑 기능이 같음 
				}
				// 자리가 비워졌으면 해당 위치에 값을 넣고 size를 증가
				array[index] = (int) value;
				size++;
			}
		}
		// 만약 index가 현재 리스트의 마지막 위치라면 (즉 맨 끝에 넣는 것이라면), 그냥 add(value) 호출하여 끝에 삽입
		else if(index == size) { // add to last position
			add(value);
		}
	}

	// 인덱스가 유효한 범위인지 (0 <= index < size) 확인하는 도우미 메서드
	private boolean checkIndexRange(int index) {
		if(index>=0 && index<size) {
			return true;
		} else {
			return false;
		}
	}



	@Override
	public void clear() {
		// 배열의 모든 값을 0으로 채우고 (Arrays.fill) size를 0으로 만들어 리스트를 초기화
		Arrays.fill(array, 0);
		size = 0;
	}



	@Override
	public boolean contains(Object value) {
		// 특정 값이 리스트에 존재하는지 확인
//		for (int i=0; i<size;i++) {
//			if (array[i] == (int)value) {
//				return true;
//			}
//		}
//		return false;
		
		// 위에 indexOf 함수 호출해서 정의해도 됨  
		return !(indexOf(value) == -1);
	}

	@Override
	public boolean remove(Object value) {
		// 리스트에서 해당 값을 찾아서 첫 번째 등장하는 값을 제거
		int index = indexOf(value);
		// index >= 0 && index < size 조건 검사
		if (checkIndexRange(index)) {
//			for (int i=index-1; i<size; i++) {
//				array[i-1] = array[i];
				System.arraycopy(array, index+1, array, index, size-index-1); // 위에 for문 두줄이랑 기능이 같음 
//		}
		size--;
		return true;
		}
		else {
		return false;
		}
	}

	@Override
	public Object remove(int index) {
		// 특정 인덱스의 요소를 제거
		if (checkIndexRange(index)) {
//			for (int i=index-1; i<size; i++) {
//				array[i-1] = array[i];
//				System.arraycopy(array, index+1, array, index, size-index-1); // 위에 for문 두줄이랑 기능이 같음
//		}
		size--;
		return true;
		}
		else {
		return false;
		}
	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public Object get(int index) {
		if(checkIndexRange(index)) {
			return array[index];
		}
		return null;
	}

	@Override
	public void set(int index, Object value) {
		if(checkIndexRange(index)) {
			array[index] = (int)value;
		}
	}
	
	@Override
	public String toString() {
		String ret = "";
		for (int i=0; i<size; i++) {
			ret = ret + array[i] + "  ";
		}
		return ret;
	}
	
	public void showList() {
		System.out.print("Current List Status : ");
		System.out.println(toString());
	}
	
	
	public static void main(String[] args) {
		
		int [] data = {113,  336,  74,  71,  86,  176,  313,  80,  225,  342,
				  170,  292,  275,  266 , 79,  16,  109,  175 , 245,  156,
				  50,  61,  277,  167,  81,  24,  76,  186,  78,  101,
				  301,  62,  152,  219,  294};
		
		MyArrayList0 list = new MyArrayList0(20);

		for (int i=0;i<10; i++)
			list.add(data[i]);  // cf :  list[i]=data[i]
		list.showList();
		
		list.set(5, 999);     // list[5]=999
		int x =(int) list.get(5);  // cf : x = list[5]
		System.out.println("\nx = "+x);

		list.remove((Integer)336);
		list.showList();
		
		list.add(3, 111);
		list.showList();
		list.add(list.size(), 222);
		list.showList();
		
		list.clear();
		list.showList();

	}

}
