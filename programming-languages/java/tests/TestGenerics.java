package tests;

import java.util.ArrayList;
import java.util.Collection;

class A {
	int v;
	public A(int v) {
		this.v = v;
	}
	public A() {
		this(0);
	}
	public void add1() {
		this.v++;
	}
	@Override
	public String toString() {
		return "A:" + this.v;
	}
}

class B extends A {
	public B(int v) {
		super(v);
	}
	public B() {
		super();
	}
	@Override
	public String toString() {
		return "B:" + this.v;
	}
}

class C extends B {
	public C(int v) {
		super(v);
	}
	public C() {
		super();
	}
	@Override
	public String toString() {
		return "C:" + this.v;
	}
}

public class TestGenerics {
	
	static <E> void printCollection(Collection<E> c) {
		for (E e : c) {
			System.out.print(e + " ");
		}
		System.out.println();
	}
	
	static <E> void copyCollection(Collection<E> c1, Collection<E> c2) {
		for (E e : c2) {
			c1.add(e);
		}
	}
	
	static void add1Collection(Collection<A> c) {
		for (A a : c) {
			a.add1();
		}
	}
	
	static <E extends A> void newAdd1Collection(Collection<E> c) {
		for (E e : c) {
			e.add1();
		}
	}
	
	static void addB(Collection<B> c) {
		B b = new B(100);
		c.add(b);
	}
	
	static void newAddB(Collection<? super B> c) {
		B b = new B(100);
		c.add(b);
	}
	
	static void copyBCollection(Collection<? super B> c1, Collection<? extends B> c2) {
		for (B b : c2) {
			c1.add(b);
		}
	}
	
	static <T> void copyAnyCollection(Collection<T> c1, Collection<? extends T> c2) {
		for (T t : c2) {
			c1.add(t);
		}
	}
	
	static <T> void copyAnyCollectionEquivalent(Collection<? super T> c1, Collection<? extends T> c2) {
		for (T t : c2) {
			c1.add(t);
		}
	}

	public static void main(String[] args) {
		
		ArrayList<A> la = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			la.add(new A(i));
		}
		printCollection(la);
		
		ArrayList<B> lb = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			lb.add(new B(i));
		}
		printCollection(lb);
		
		add1Collection(la);
		printCollection(la);
		
		// add1Collection(lb);
		newAdd1Collection(lb);
		printCollection(lb);
		
		ArrayList<C> lc = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			lc.add(new C(i));
		}
		printCollection(lc);
		
		newAddB(la);
		addB(lb);
		// newAddB(lc);
		
		ArrayList<A> aaa = new ArrayList<>();
		ArrayList<B> bbb = new ArrayList<>();
		ArrayList<C> ccc = new ArrayList<>();
		
		copyAnyCollection(aaa, la);
		copyBCollection(aaa, lb);
		// copyBCollection(bbb, la);
		copyBCollection(bbb, lb);
		copyBCollection(bbb, lc);
		// copyBCollection(ccc, lb);
		copyBCollection(aaa, lc);
		
		System.out.println(aaa);
		System.out.println(bbb);
		System.out.println(ccc);
		
		ArrayList<?> c1 = new ArrayList<>();
		ArrayList<? super A> c2 = new ArrayList<>();
		ArrayList<? extends A> c3 = new ArrayList<>();
		
		// c1.add(new A());
		// c2.add(new A());
		// c3.add(new A());
		c1.add(null);
		// You can only add null to c1
		c3.add(null);
		// and c3
		
		c1 = new ArrayList<A>();
		c2 = new ArrayList<A>();
		c3 = new ArrayList<A>();
		
		// c1.add(new A());
		c2.add(new A());
		c2.add(new B());
		c2.add(new C());
		// c3.add(new A());
		// c3.add(new B());
		
		// Remember PECS: Producer Extends, Consumer Super.
		// Since c2 is a consumer (you're adding things to it), super is valid but extends is not
		// Therefore, you cannot do a "put" with extends
		
		ArrayList<? super B> c4 = new ArrayList<A>();
		c4.add(new C());
		c4.add(new B());
		// c4.add(new A());
		
		// You cannot add A because it is a list of B
		
		ArrayList<? super B> c5 = new ArrayList<A>();
		c5.add(new C());
		ArrayList<? super C> c6 = new ArrayList<B>();
		c6.add(new C());
		// c6.add(new A());
		ArrayList<A> c7 = new ArrayList<A>();
		c7.add(new C());
		ArrayList<C> c8 = new ArrayList<C>();
		c8.add(new C());
		ArrayList<? extends B> c9;
		// c9 = c5;
		// c9 = c6;
		// c9 = c7;
		c9 = c8;
		System.out.println(c9);
		
		ArrayList<? super B> c11 = new ArrayList<B>();
		c11.add(new B(20));
		// ArrayList<? extends B> c12 = c11;
		// This won't work because ? super B can be A, but ? extends B can be C: type mismatch
		ArrayList<? extends Object> c12 = c11;
		System.out.println(c12);
		// This works because everything extends Object

	}

}
