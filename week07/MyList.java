package week07;

public interface MyList {
	
	public boolean isEmpty() ;
	
	public int indexOf(Object value) ;

	public void add(Object value) ;
	
	public void add(int index, Object value) ;
	
	public void clear() ;
	
	public boolean contains(Object  value) ;
	
	public boolean remove(Object value) ;

	public Object remove(int index) ;

	public int size() ;

	public Object get(int index) ;

	public void set(int index, Object value) ;
	
	public String toString();

}
