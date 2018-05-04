package studio4;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;

import sedgewick.StdAudio;
import sedgewick.StdDraw;
import sedgewick.StdIn;

public class Flag {


	public static void main(String[] args) throws IOException {
		//
		//  Add code for your studio below here.
		//

		StdDraw.setCanvasSize(800, 400);
		StdDraw.setXscale(0, 2.0);
		StdDraw.setYscale(0, 1.0);

		final double HALF_LENGTH = 1.0;
		final double HALF_HEIGHT = 0.15;

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(HALF_LENGTH, 1 - HALF_HEIGHT, HALF_LENGTH, HALF_HEIGHT);

		StdDraw.setPenColor(new Color(175, 0, 0));
		StdDraw.filledRectangle(HALF_LENGTH, 0.5, HALF_LENGTH, HALF_HEIGHT);

		StdDraw.setPenColor(new Color(0, 175, 0));
		StdDraw.filledRectangle(HALF_LENGTH, HALF_HEIGHT, HALF_LENGTH, HALF_HEIGHT);

		double[] triangleX = { 0.0, 0.0, Math.sqrt(3) / 2 };
		double[] triangleY = { 0.0, 1.0, 0.5 };

		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledPolygon(triangleX, triangleY);

		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledCircle(triangleX[2] / 3, 0.5, 0.15);

		StdDraw.setPenColor(StdDraw.WHITE);
		for (double i = 0.2; i < 1.8; i += 0.2) {
			StdDraw.text(i, (2.0 - i) / 2, "South Sudan");
		}

		FileInputStream fileInputStream = new FileInputStream("music/National_Anthem.txt");
		System.setIn(fileInputStream);

		// repeat as long as there are more integers to read in
		while (!StdIn.isEmpty()) {

			// read in the pitch, where 0 = Concert A (A4)
			int pitch = StdIn.readInt();

			// read in duration in seconds
			double duration = StdIn.readDouble();

			// build sine wave with desired frequency
			double hz = 440 * Math.pow(2, pitch / 12.0);
			int N = (int) (StdAudio.SAMPLE_RATE * duration);
			double[] a = new double[N + 1];
			for (int i = 0; i <= N; i++) {
				a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
			}

			// play it using standard audio
			StdAudio.play(a);
		}

		fileInputStream.close();

	}
}
