package subtype;

import java.util.LinkedList;

class C<T> extends LinkedList<T> { }
class A { public int x; }
class B extends A { public int y; }

public class Test {
	public static void insertB(C<? super B> cb) {
		cb.add(new B());
	}
	public static void main(String[] args) {
		C<A> ca = new C<A>();
		C<B> cb = new C<B>();
		insertB(ca);
		insertB(cb);
		B b1 = (B) ca.get(0);
		B b2 = cb.get(0);
		System.out.println(b1.y);
		System.out.println(b2.y);
	}
}
