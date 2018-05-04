package lines;

import sedgewick.StdDraw;

public class InteractiveLines {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) {
			//
			// Wait for the mousebutton to be pressed
			//
			while (!StdDraw.mousePressed()) {
				// wait, do nothing
			}
			//
			// Capture the mouse's location is now that is has been pressed
			//
			double x1 = StdDraw.mouseX();
			double y1 = StdDraw.mouseY();
			//
			// Now wait for the button to be released
			//
			while (StdDraw.mousePressed()) {
				// wait, do nothing
			}
			//
			// Capture the mouse's location now that it has been released
			//
			double x2 = StdDraw.mouseX();
			double y2 = StdDraw.mouseY();
			
			//
			// Run the student's code to draw the line
			//
			Lines.drawLine(x1, y1, x2, y2);

		}

	}

}
