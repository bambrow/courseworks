package lines;

import sedgewick.StdDraw;

public class Lines {

	/**
	 * 
	 * @param x1 x coordinate of starting point
	 * @param y1 y coordinate of starting point
	 * @param x2 x coordinate of ending point
	 * @param y2 y coordinate of ending point
	 */
	public static void drawLine(double x1, double y1, double x2, double y2) {
		// FIXME -- fill in this method according to the instructions
		if (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) < .005) {
			StdDraw.setPenRadius(.005);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.point((x1 + x2) / 2, (y1 + y2) / 2);
		} else {
			drawLine(x1, y1, (x1 + x2) / 2, (y1 + y2) / 2);
			drawLine((x1 + x2) / 2, (y1 + y2) / 2, x2, y2);
		}
	}
	
	
	/**
	 * Code to test the drawLine method visually
	 */
	public static void main(String[] args) {
		
		//
		// Test the drawing program
		//
		drawLine(0,0,1,1); // lower left to upper right
		drawLine(0,1,1,0); // upper left to lower right
		
		//
		// Draw rectangles of decreasing width and height
		//
		for (double r = 0.25; r < 0.5; r = r+.005) {
			double s = 1-r;
			drawLine(r, r, r, s);
			drawLine(r, s, s, s);
			drawLine(s, s, s, r);
			drawLine(s, r, r, r);
		}
		System.out.println("done drawing");
	}

}
