package tests;

import java.util.ArrayList;

public class ErasureExample {

	public static void main(String[] args) {
		
		ArrayList<Integer> li = new ArrayList<>();
		ArrayList<Float> lf = new ArrayList<>();
		
		System.out.println(li.getClass().toString());
		System.out.println(lf.getClass().toString());
		
		if (li.getClass() == lf.getClass()) {
			System.out.println("equal"); // this will run
		} else {
			System.out.println("not equal");
		}
		
	}

}
