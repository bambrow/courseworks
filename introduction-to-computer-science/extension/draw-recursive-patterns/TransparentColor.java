package recursivepatterns;

import java.awt.Color;

import sedgewick.StdDraw;

public class TransparentColor {

	/**
	 * A method to return a transparent version of the supplied color.
	 * To complete this method, you have to find a constructor for Color that takes in
	 *    red, green, and blue values, but also an alpha component for the transparency.
	 * Find this by search on the Internet for java.awt.Color, and look in the JavaDoc for the
	 *    constructor you need.
	 * Then, use the information in Color c (provided as an input parameter) to create and return
	 *    the color with the specified transparency.
	 *    
	 * @param c the color to be considered
	 * @param alpha the amount of transparency, 0 <= alpha <= 255.  
	 * At 255 the color is completely solid.  At 0 the is completely transparent.
	 * @return a Color object of the same RGB value as supplied, but with the alpha value applied.
	 */
	public static Color transparentColor(Color c, int alpha) {
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
	}


	/**
	 * A test of your transparentColor method
	 * @param args ignored
	 */
	public static void main(String[] args) {
		double dy = 0.1;
		for (Color c : new Color[] { Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK}) {
			for (int i=255; i >= 0; --i) {
				StdDraw.setPenColor(transparentColor(c, i));
				StdDraw.filledRectangle(0.75*i/255+.1, 0.75*i/255+dy, 0.05, 0.05);

			}
			dy = dy + .05;
		}

	}

}
