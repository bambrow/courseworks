import java.util.*;

class SortedList<E extends Comparable<E>> extends ArrayList<E> implements List<E>, Comparable<SortedList<E>> {

	public SortedList() {
		super();
	}
	
	@Override
	public boolean add(E e) {
		int index = Collections.binarySearch(this, e);
		if (index < 0) index = -index - 1;
		super.add(index, e);
		return true;
	}
	
	@Override
	public int compareTo(SortedList<E> other) {
		int smaller = this.size() < other.size() ? this.size() : other.size();
		for (int i = 0; i < smaller; i++) {
			int comp = this.get(i).compareTo(other.get(i));
			if (comp == 0) continue;
			if (comp > 0) return 1;
			else return -1;
		}
		if (this.size() == other.size()) return 0;
		return this.size() < other.size() ? -1 : 1;
	}
	
}

class A implements Comparable<A> {
	
	protected Integer x;
	
	public A(Integer x) {
		this.x = x;
	}
	
	protected int valueForComparison() {
		return this.x;
	}

	@Override
	public int compareTo(A other) {
		if (this.valueForComparison() == other.valueForComparison()) return 0;
		return this.valueForComparison() < other.valueForComparison() ? -1 : 1;
	}
	
	@Override
	public String toString() {
		return "A<" + this.x + ">";
	}
	
}

class B extends A implements Comparable<A> {
	
	protected Integer y;
	
	public B(Integer x, Integer y) {
		super(x);
		this.y = y;
	}
	
	@Override
	protected int valueForComparison() {
		return this.x + this.y;
	}
	
	@Override
	public String toString() {
		return "B<" + this.x + "," + this.y + ">";
	}
	
}

public class Part1 {
	
	public static <T extends Comparable<T>> void addToSortedList(SortedList<T> L, T z) {
		L.add(z);
	}
	
	static void test() {
		SortedList<A> c1 = new SortedList<A>();
		SortedList<A> c2 = new SortedList<A>();
		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			int j = r.nextInt(100);
			A a = new A(j);
			int k = r.nextInt(100);
			B b = new B(j-k, k);
			addToSortedList(c1, a);
			addToSortedList(c2, b);
		}
		System.out.print("c1: ");
		System.out.println(c1);
		System.out.print("c2: ");
		System.out.println(c2);
		System.out.println(c1.compareTo(c2));
		addToSortedList(c2, new B(100,1));
		System.out.print("c1: ");
		System.out.println(c1);
		System.out.print("c2: ");
		System.out.println(c2);
		System.out.println(c1.compareTo(c2));
		c2.remove(c2.size() - 1);
		c2.remove(c2.size() - 1);
		System.out.print("c1: ");
		System.out.println(c1);
		System.out.print("c2: ");
		System.out.println(c2);
		System.out.println(c1.compareTo(c2));
		
		SortedList<String> c3 = new SortedList<String>();
		SortedList<String> c4 = new SortedList<String>();
		String[] strNormal = { "NYU", "is", "so", "great", "!" };
		String[] strYoda = { "so", "great", "NYU", "is", "!" };
		for (int i = 0; i < strNormal.length; i++) {
			addToSortedList(c3, strNormal[i]);
			addToSortedList(c4, strYoda[i]);
			System.out.print("c3: ");
			System.out.println(c3);
			System.out.print("c4: ");
			System.out.println(c4);
			System.out.println(c3.compareTo(c4));
		}
		
		c1.clear();
		c2.clear();
		for(int i = 0; i < 10; i++) {
		    addToSortedList(c1, new A(2*i));
		    addToSortedList(c2, new B(i-1,i+1));
		}
		System.out.print("c1: ");
		System.out.println(c1);
		System.out.print("c2: ");
		System.out.println(c2);
		System.out.println(c1.compareTo(c2));
		c2.remove(4);
		c2.remove(6);
		System.out.print("c1: ");
		System.out.println(c1);
		System.out.print("c2: ");
		System.out.println(c2);
		System.out.println(c1.compareTo(c2));
		addToSortedList(c2, new B(3,4));
		System.out.print("c1: ");
		System.out.println(c1);
		System.out.print("c2: ");
		System.out.println(c2);
		System.out.println(c1.compareTo(c2));
		
		c1.clear();
		c2.clear();
		for(int i = 35; i >= 0; i-=5) {
		    addToSortedList(c1, new A(i));
		    addToSortedList(c2, new B(i+2,i+3));
		}
		System.out.print("c1: ");
		System.out.println(c1);
		System.out.print("c2: ");
		System.out.println(c2);
		switch (c1.compareTo(c2)) {
		case -1: 
			System.out.println("c1 < c2");
			break;
		case 0:
			System.out.println("c1 = c2");
			break;
		case 1:
			System.out.println("c1 > c2");
			break;
		default:
			System.out.println("Uh Oh");
			break;
		}
	}

	public static void main(String[] args) {
		test();
	}

}
