package exam;

import java.util.ArrayList;

// class L<T extends Comparable<T>> extends ArrayList<T> {};
class A implements Comparable<A> {
	public int x;
	public A(int x) {
		this.x = x;
	}
	protected int comp() {
		return this.x;
	}
	@Override
	public int compareTo(A that) {
		if (this.comp() == that.comp()) return 0;
		return this.comp() > that.comp() ? 1 : -1;
	}
	@Override
	public String toString() {
		return "A<" + this.x + ">";
	}
}
class B extends A implements Comparable<A> {
	public int y;
	public B(int x, int y) {
		super(x);
		this.y = y;
	}
	@Override
	protected int comp() {
		return this.x + this.y;
	}
	@Override
	public String toString() {
		return "B<" + this.x + "," + this.y + ">";
	}
}
class C extends B implements Comparable<A> {
	public int z;
	public C(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
	@Override
	protected int comp() {
		return this.x + this.y + this.z;
	}
	@Override
	public String toString() {
		return "C<" + this.x + "," + this.y + "," + this.z + ">";
	}
}

public class ExamProblem {
	static <T extends Comparable<T>> boolean compare(ArrayList<? extends T> l1, ArrayList<? extends T> l2) {
		if (l1.size() != l2.size()) return false;
		for (int i = 0; i < l1.size(); i++) {
			T i1 = l1.get(i);
			T i2 = l2.get(i);
			if (i1.compareTo(i2) != 0) return false; 
		}
		return true;
	}
	public static void main(String[] args) {
		ArrayList<A> l1 = new ArrayList<>();
		ArrayList<B> l2 = new ArrayList<>();
		ArrayList<C> l3 = new ArrayList<>();
		l1.add(new A(10));
		l2.add(new B(5,5));
		l3.add(new C(2,4,4));
		l1.add(new B(6,6));
		l2.add(new B(5,7));
		l3.add(new C(4,4,4));
		l1.add(new C(5,5,5));
		l2.add(new C(4,6,5));
		l3.add(new C(-5,5,15));
		System.out.println(compare(l1, l2));
		System.out.println(compare(l2, l1));
		System.out.println(compare(l1, l3));
		System.out.println(compare(l3, l1));
		System.out.println(compare(l2, l3));
		System.out.println(compare(l3, l1));
	}
}
