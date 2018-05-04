package studio1;

import cse131.ArgsProcessor;

public class Hello {
	
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		
		String mood = ap.nextString("How are you feeling today?");
		
		System.out.println("Hello and welcome to CSE 131!");
		System.out.println("You seem to be feeling " + mood);
	}

}
