package studio1;

import cse131.ArgsProcessor;

public class HiOne {

	/**
	 * Says hello to the supplied argument
	 */
	public static void main(String[] args) {
		//
		// The following two lines process the input supplied when
		//    this program is run.  We don't know anything about arrays
		//    yet so you are not supposed to understand how the code
		//    works.
		// The important thing is to realize that when these two lines
		//    have done their job, the variable "name" holds the supplied
		//    input String.
		ArgsProcessor ap = new ArgsProcessor(args);
		String name = ap.nextString("Enter value for arg 0");
		System.out.println("Hi "+ name + ". How are you?");
		//
		// Below this line, enter code so that this program's output says
		//      Hi, Pat.  How are you?
		// if the value of name is "Pat"
		//

	}

}
