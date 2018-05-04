package lab8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Polynomial {

	final private LinkedList<Double> list;

	/**
	 * Constructs a Polynomial with no terms yet.
	 */
	public Polynomial() {
		//
		// Set the instance variable (list) to be a new linked list of Double type
		//
		list = new LinkedList<Double>();
	}

	@Override
	public String toString() {
		int h = list.size();
		if (h == 0) {
			return "null";
		} else {
			Iterator<Double> iter = list.descendingIterator();
			StringBuilder s = new StringBuilder();
			boolean hasNonZeroTerm = false;
			while (iter.hasNext()) {
				double i = iter.next();
				h--;
				if (h == list.size() - 1) {
					if (i != 0) {
						s.append(i + "x^" + h);
						hasNonZeroTerm = true;
					}
				} else if (h > 1) {
					if (i > 0) {
						s.append(" + " + i + "x^" + h);
						hasNonZeroTerm = true;
					} else if (i < 0) {
						s.append(" - " + -i + "x^" + h);
						hasNonZeroTerm = true;
					}
				} else if (h == 1) {
					if (i > 0) {
						s.append(" + " + i + "x");
						hasNonZeroTerm = true;
					} else if (i < 0) {
						s.append(" - " + -i + "x");
						hasNonZeroTerm = true;
					}
				} else {
					if (i > 0) {
						s.append(" + " + i);
					} else if (i < 0) {
						s.append(" - " + -i);
					} else if (!hasNonZeroTerm) {
						s.append("0");
					}
				}
			}
			return s.toString();
		}
	}

	/**
	 * add a new term to the polynomial.
	 * @param coeff		the new coefficient
	 * @return 			the new polynomial
	 */
	public Polynomial addTerm(double coeff) {
		list.add(coeff);
		return this;
	}

	/**
	 * calculate the double value of the polynomial at the given double value
	 * @param x		a double
	 * @return		the double value of the polynomial at point x
	 */
	public double evaluate(double x) {
		if (this.list.size() == 0) {
			return .0;
		}
		double eval = .0;
		ListIterator<Double> iter = this.list.listIterator();
		eval += iter.next();
		Polynomial sub = new Polynomial();
		while (iter.hasNext()) {
			sub.addTerm(iter.next());
		}
		return eval + x * sub.evaluate(x);
	}
	
	/**
	 * calculate the first derivative of the polynomial
	 * @return		the first derivative of the polynomial
	 */
	public Polynomial derivative() {
		Polynomial res = new Polynomial();
		if (list.size() == 0 || list.size() == 1) {
			return res;
		} else {
			ListIterator<Double> iter = list.listIterator();
			int h = 1;
			iter.next();
			while (iter.hasNext()) {
				res.addTerm(iter.next() * h);
				h++;
			}
			return res;
		}
	}

	/**
	 * sum two polynomials
	 * @param another	another polynomial
	 * @return			the sum of two polynomials
	 */
	public Polynomial sum(Polynomial another) {
		if (this.list.size() == 0) {
			return another;
		}
		if (another.list.size() == 0) {
			return this;
		}
		Polynomial res = new Polynomial();
		ListIterator<Double> thisIter = this.list.listIterator();
		ListIterator<Double> thatIter = another.list.listIterator();
		while (thisIter.hasNext() || thatIter.hasNext()) {
			if (thisIter.hasNext() && thatIter.hasNext()) {
				res.addTerm(thisIter.next() + thatIter.next());
			} else if (thisIter.hasNext()) {
				res.addTerm(thisIter.next());
			} else {
				res.addTerm(thatIter.next());
			}
		}
		return res;
	}

	/**
	 * This is the "equals" method that is called by
	 *    assertEquals(...) from your JUnit test code.
	 *    It must be prepared to compare this Polynomial
	 *    with any other kind of Object (obj).  Eclipse
	 *    automatically generated the code for this method,
	 *    after I told it to use the contained list as the basis
	 *    of equality testing.  I have annotated the code to show
	 *    what is going on.
	 */

	public boolean equals(Object obj) {
		// If the two objects are exactly the same object,
		//    we are required to return true.  The == operator
		//    tests whether they are exactly the same object.
		if (this == obj)
			return true;
		// "this" object cannot be null (or we would have
		//    experienced a null-pointer exception by now), but
		//    obj can be null.  We are required to say the two
		//    objects are not the same if obj is null.
		if (obj == null)
			return false;

		//  The two objects must be Polynomials (or better) to
		//     allow meaningful comparison.
		if (!(obj instanceof Polynomial))
			return false;

		// View the obj reference now as the Polynomial we know
		//   it to be.  This works even if obj is a BetterPolynomial.
		Polynomial other = (Polynomial) obj;

		//
		// If we get here, we have two Polynomial objects,
		//   this and other,
		//   and neither is null.  Eclipse generated code
		//   to make sure that the Polynomial's list references
		//   are non-null, but we can prove they are not null
		//   because the constructor sets them to some object.
		//   I cleaned up that code to make this read better.


		// A LinkedList object is programmed to compare itself
		//   against any other LinkedList object by checking
		//   that the elements in each list agree.

		return this.list.equals(other.list);
	}

}
