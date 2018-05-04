package rabinkarp;

public class RK {
	
	//
	// Be sure to look at the write up for this assignment
	//  so that you get full credit by satisfying all
	//  of its requirements
	//
	
	private int m; 			// the length of query string
	private final int k;	// the small prime used for multiplication
	private final int p; 	// the large prime used for mod
	private int km; 		// k^m mod p 
	private int h; 			// current hash value
	private int l; 			// current hash location
	private char[] ch; 		// the character array used for rolling hash

	/**
	 * Rabin-Karp string matching for a window of the specified size
	 * @param m size of the window
	 */
	public RK(int m) {
		this.m = m;	
		this.k = 31;		// small prime is 31
		this.p = 511;		// large prime is 511
		
		this.km = 1;
		// calculate k^m mod p
		for (int i = 0; i < m; ++i) {
			this.km = (k * this.km) % p;
		}
		
		this.h = 0;			// initial hash value is 0
		this.l = 0;			// initial hash location is 0
		this.ch = new char[m];		// char array size is equal to m
	}
	

	/**
	 * Compute the rolling hash for the previous m-1 characters with d appended.
	 * @param d the next character in the target string
	 * @return
	 */
	public int nextCh(char d) {
		
		// when rolling hash is not needed
		if (l < m) {
			h = (h * k + d) % p; 	// compute h
			ch[l] = d;				// put the current character in the array
			l++;					// increment l
			return h;
		}
		
		// when rolling is needed
		h = (h * k + (p - km) * ch[l%m] + d) % p; 	// compute h
		ch[l%m] = d;		// update character array
		l++;				// increment l
		return h;
		
	}

}
