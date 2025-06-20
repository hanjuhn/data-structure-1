package week07;

import java.util.Arrays;

public class MyArrayList1 implements MyList {

	// 0은 용량 초과하면 요소 추가 실패하지만 1은 grow()로 자동 배열 확장

	// 리스트를 처음 만들 때 배열 크기를 5로 시작
	final int INITIAL_CAPACITY = 5;
	
	int[] array;
	int capacity;
	int size;
	
	public MyArrayList1() {
		capacity = INITIAL_CAPACITY; // 용량을 5로 설정
		array = new int[capacity]; // 길이 5짜리 배열 생성
		size = 0; // 처음엔 아무것도 없으니까 size는 0
	}

	
	

	@Override
	public boolean isEmpty() { 
		return (size==0);
	}

	@Override
	public int indexOf(Object value) {
		for(int i=0;i<size;i++) {
			if(array[i]==(int)value) {
				return i;
			}
		}
		return -1; // -1 means "not found!"
	}

    @Override
    public void add(Object value) {
        if (isFull()) grow();
        array[size++] = (int) value;
    }
    
    


	private boolean isFull() {
		return (size==capacity);
	}
    
    
	@Override
	public void add(int index, Object value) {
		if (checkIndexRange(index)) { // ? 0<= index <= size-1

			if (isFull()) {
				grow();
			}
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = (int) value;
			size++;
		} else if (index == size) {
			add(value);
		}
	}
	
    private void grow() {
		// 기존 배열보다 2배 큰 크기의 새 배열을 만든다
		// System.arraycopy(array, 0, tempArray, 0, capacity);
        int[] tempArray = new int[capacity * 2];
        for (int i = 0; i < capacity; i++) {
			// 기존 배열 array의 값을 새 배열 tempArray로 복사
            tempArray[i] = array[i];
        }
		// 원래 배열 참조를 새 배열로 변경
        array = tempArray;
		// 용량 변수도 새로운 크기로 업데이트
        capacity *= 2;
    }

		

	private boolean checkIndexRange(int index) {
		if(index>=0 && index<size) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public void clear() {
		Arrays.fill(array, 0);
		size = 0;
	}

	@Override
	public boolean contains(Object value) {
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
		int index = indexOf(value);	
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

		for (int i=0;i<4; i++)
			list.add(data[i]);  // cf :  list[i]=data[i]
		list.showList();
		
		
		for (int i=4;i<10; i++)
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
