package bounded;

import java.util.ArrayList;
import java.util.List;

class A {
	public int x = 4;
}

class B extends A {
	public int y = 7;
}

public class Bounded {
	
	static <T extends A> List<T> id(List<T> l) {
		return l;
	}
	
	static <E> void insert(List<E> l, E ele) {
		l.add(ele);
	}
	
	static void insertB(List<? super B> l, B ele) {
		l.add(ele);
	}
	
	static <E extends A> void insertListA(List<A> l, E ele) {
		l.add(ele);
	}

	public static void main(String[] args) {
		
		List<A> la = new ArrayList<>();
		A a = new A();
		insert(la, a);
		B b = new B();
		insert(la, b); // B is a A
		List<B> lb = new ArrayList<>();
		// insert(lb, a); 
		insert(lb, b);
		
		la = id(la);
		lb = id(lb);
		// lb = id(la);
		// la = id(lb);
		
		insertB(lb, b);
		insertB(la, b);
		
		insertListA(la, a);
		insertListA(la, b);
		
		System.out.println("Complete!");
		
	}

}
