package lab10;

import java.awt.event.KeyEvent;

import sedgewick.StdDraw;

public class ArcadeKeys {
	public static final int KEY_UP = 0, KEY_LEFT = 1, KEY_DOWN = 2,
			KEY_RIGHT = 3, KEY_A = 4, KEY_B = 5, KEY_C = 6, KEY_D = 7,
			KEY_E = 8, KEY_F = 9, KEY_G = 10, KEY_WHITE = 11;
	
	public static final int NUM_CONTROLS = 4;

	private static final int[][] KEYS = {
			{ KeyEvent.VK_W, KeyEvent.VK_UP, KeyEvent.VK_Z, KeyEvent.VK_0 },
			{ KeyEvent.VK_A, KeyEvent.VK_LEFT, KeyEvent.VK_C, KeyEvent.VK_9 },
			{ KeyEvent.VK_S, KeyEvent.VK_DOWN, KeyEvent.VK_X,
					KeyEvent.VK_EQUALS },
			{ KeyEvent.VK_D, KeyEvent.VK_RIGHT, KeyEvent.VK_B,
					KeyEvent.VK_MINUS },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA, KeyEvent.VK_PERIOD,
					KeyEvent.VK_PAGE_DOWN },
			{ KeyEvent.VK_F, KeyEvent.VK_K, KeyEvent.VK_M, KeyEvent.VK_DELETE },
			{ KeyEvent.VK_G, KeyEvent.VK_L, KeyEvent.VK_U, KeyEvent.VK_HOME },
			{ KeyEvent.VK_H, KeyEvent.VK_SEMICOLON, KeyEvent.VK_J,
					KeyEvent.VK_PAGE_UP },
			{ KeyEvent.VK_R, KeyEvent.VK_I, KeyEvent.VK_Q,
					KeyEvent.VK_BACK_QUOTE },
			{ KeyEvent.VK_T, KeyEvent.VK_O, KeyEvent.VK_E,
					KeyEvent.VK_OPEN_BRACKET },
			{ KeyEvent.VK_Y, KeyEvent.VK_P, KeyEvent.VK_N,
					KeyEvent.VK_CLOSE_BRACKET },
			{ KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4 } };

	public static boolean isKeyPressed(int player, int key) {
		return StdDraw.isKeyPressed(getKeyCode(player, key));
	}

	public static int getKeyCode(int player, int key) {
		return KEYS[key][player];
	}

}
