package tests;

import java.util.List;

public class GenericsExample {
	
	public static void printList(List<Integer> intList) {
		for (Integer integer : intList) {
			System.out.print(integer + " ");
		}
		System.out.println();
	}
	
	/*
	public static void printList(List<String> strList) {
		for (String string : strList) {
			System.out.println(string + " ");
		}
		System.out.println();
	}
	*/
	
	// Won't allowed. Cannot have two overloaded methods that will have the same signature after type erasure.

}
