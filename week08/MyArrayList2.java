package week08;

public class MyArrayList2<T> {
	final int INITIAL_CAPACITY = 5;
	
	T [] array;
	int capacity;
	int size;
	
	public MyArrayList2() {
		capacity=INITIAL_CAPACITY;
		array=(T[]) new Object[capacity];
		size=0;
	}
	public boolean isEmpty() {
		return (size==0);
	}

	public int indexOf(T value) {
		for (int i=0;i<size;i++) {
			if (array[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	public void add(T value) {
		if (isFull()) {
			grow();
		}
		array[size++]=value;
	}

	private boolean isFull() {
		return (size==capacity) ;
	}
	public void add(int index, T value) {
		if(checkIndexRange(index)) {
			if (isFull()) {
				grow();
			}
				System.arraycopy(array, index, array, index+1, size-index);
				array[index]= value;
				size++;

		}
		else if (index==size) {
			add(value);
		}
	}

	private void grow() {
		T [] tempArray =(T[]) new Object [capacity*2];
		for(int i=0;i<capacity;i++)
			tempArray[i]=array[i];
		
		capacity *=2;
		array=tempArray;
	}
	public void clear() {
		for(int i=0;i<size;i++)
			array[i]=null ;
//		Arrays.fill(array,0);
		size=0;
	}

	public boolean contains(T value) {
		for (int i=0;i<size;i++) {
			if (array[i].equals(value)) {
				return true;
			}
		}
		return false;
	}

	public boolean remove(T value) {
		int index=indexOf(value);
		if(checkIndexRange(index)) {
			for(int i=index;i<size;i++) {
				array[i]=array[i+1];
			}
//			System.arraycopy(array, index+1, array, index, size-index-1);
			size--;
			return true;
		}
		else
			return false;
	}

	public T remove(int index) {
		if(checkIndexRange(index)) {
			T retValue=array[index];
			System.arraycopy(array, index+1, array, index, size-index-1);
			size--;
			return retValue;
		}
		else 
			return null;
	}

	public int size() {
		return size;
	}

	public T get(int index) {
		if(checkIndexRange(index)) {
			return array[index];
		}
		return null;
	}

	private boolean checkIndexRange(int index) {
		if (index>=0 && index<size)
			return true;
			else
				return false;
	}
	public void set(int index, T value) {
		if(checkIndexRange(index)) {
			array[index]= value;
		}
	}
	
	@Override
	public String toString() {
		String ret ="";
		for (int i=0;i<size;i++)
			ret = ret+array[i].toString()+"    ";
		return ret;
	}

	public void showList() {
		 System.out.print("\nCurrent List Status : ");
		 
		System.out.println(toString());
		System.out.println("\n  >>> Current Capacity = "+capacity+"  Size = "+size);
	}

}
