package lab6;

import sedgewick.StdDraw;

public class Triangles {

	/**
	 * Draw the Sierpinski triangle.
	 * 
	 * @param lx
	 *            the x of lower left of the graph
	 * @param ly
	 *            the y of lower left of the graph
	 * @param size
	 *            the size of the graph
	 */

	public static void drawTriangles(double lx, double ly, double size) {
		if (size < 0.05) {
			double[] x1 = { lx, lx + size / 2, lx + size / 4 };
			double[] y1 = { ly, ly, ly + size * Math.sqrt(3) / 4 };
			double[] x2 = { lx + size / 2, lx + size, lx + 3 * size / 4 };
			double[] y2 = { ly, ly, ly + size * Math.sqrt(3) / 4 };
			double[] x3 = { lx + size / 4, lx + 3 * size / 4, lx + size / 2 };
			double[] y3 = { ly + size * Math.sqrt(3) / 4, ly + size * Math.sqrt(3) / 4, ly + size * Math.sqrt(3) / 2 };
			StdDraw.filledPolygon(x1, y1);
			StdDraw.filledPolygon(x2, y2);
			StdDraw.filledPolygon(x3, y3);
			return;
		} else {
			drawTriangles(lx, ly, size / 2);
			drawTriangles(lx + size / 2, ly, size / 2);
			drawTriangles(lx + size / 4, ly + size * Math.sqrt(3) / 4, size / 2);
		}
	}
	
	public static void main(String[] args) {
		
		StdDraw.setPenColor(StdDraw.BLACK);
		drawTriangles(0, 0, 1);

	}

}
