package conway;

/**
 * 
 *
 */
public class Conway {

	private int rows;
	private int cols;
	private boolean[][] liveness;

	public Conway(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.liveness = new boolean[rows][cols];
	}


	public int getRows(){
		return this.rows;
	}

	public int getColumns(){
		return this.cols;
	}

	/**
	 * Sets the current status of the cell at (row, col)
	 * @param b if true, the cell is alive; if false, the cell is dead 
	 * @param row
	 * @param col
	 */
	public void setLiveness(boolean b, int row, int col){
		if (row < 0 || col < 0 || row >= this.rows || col >= this.cols) {
			return;
		}
		this.liveness[row][col] = b;
	}


	/**
	 *  
	 * @param row
	 * @param col
	 * @return whether or not a cell at a specific row and column is alive. 
	 *    If row or col is out of bounds, you must return false.
	 */
	public boolean isAlive(int row, int col){
		if (row < 0 || col < 0 || row >= this.rows || col >= this.cols) {
			return false;
		}
		return this.liveness[row][col];
	}


	/**
	 * Make every cell not alive
	 */
	public void clear(){
		this.liveness = new boolean[rows][cols];
	}


	/**
	 * Consider the 3x3 cell array that has the cell at (row, col) at its center.
	 * Let's call all cells but that center cell the neighbors of that center cell.
	 * This method returns the number of neighbors that are alive.
	 * 
	 *   n  n  n
	 *   n  c  n
	 *   n  n  n
	 *   
	 *  Above, if c represents the cell at (row, col), each n is
	 *  a neighbor of c, according to the above definition.
	 *  The result is at most 8 and at least 0.
	 * 
	 * @param row
	 * @param col
	 * @return the number of living neighbors
	 */
	public int countLivingNeighbors(int row, int col){
		int livingNeighbors = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (i == row && j == col) {
					continue;
				} else if (this.isAlive(i, j)) {
					livingNeighbors++;
				}
			}
		}
		return livingNeighbors;
	}

	/**
	 * Executes a generation of life.  Be sure to read the specification
	 * for this assignment, because it points out a common mistake students
	 * make when implementing this method.
	 * 
	 */
	public void step(){
		Conway next = new Conway(this.rows, this.cols);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				if (this.isAlive(i, j)) {
					if (this.countLivingNeighbors(i, j) < 2 || this.countLivingNeighbors(i, j) > 3) {
						next.setLiveness(false, i, j);
					} else {
						next.setLiveness(true, i, j);
					}
				} else {
					if (this.countLivingNeighbors(i, j) == 3) {
						next.setLiveness(true, i, j);
					}
				}
			}
		}
		this.liveness = next.liveness.clone();
	}

	/**
	 * creates a blinker
	 */
	public void blinker() {

		if (this.getRows() < 3 || this.getColumns() < 3) {
			System.out.println("Grid is too small for premade pattern Blinker. " +
					"Conway must be at least 3x3");
		}
		else {
			this.setLiveness(true, 1, 0);
			this.setLiveness(true, 1, 1);
			this.setLiveness(true, 1, 2);
		}
	}

	public void fourBlinkers() {
		clear();
		if (this.getRows() < 9 || this.getColumns() < 9) {
			System.out.println("Grid is too small for premade pattern Four Blinkers. " +
					"Conway must be at least 9x9");
		}
		else {
			for (int i = 3; i < 6; i++) {
				for (int j = 3; j < 6; j++) {
					this.setLiveness(true, i, j);
				}
			}
		}
	}


	public void gosperGliderGun() {
		clear();
		if (this.getRows() < 50 || this.getColumns() < 50) {
			System.out.println("Grid is too small for premade pattern Gosper Glider Gun. " +
					"Conway must be at least 50x50");
		}
		else {
			this.setLiveness(true,0,27);
			this.setLiveness(true,1,25);
			this.setLiveness(true,1,27);
			this.setLiveness(true,2,15);
			this.setLiveness(true,2,16);
			this.setLiveness(true,2,23);
			this.setLiveness(true,2,24);
			this.setLiveness(true,2,37);
			this.setLiveness(true,2,38);
			this.setLiveness(true,3,14);
			this.setLiveness(true,3,18);
			this.setLiveness(true,3,23);
			this.setLiveness(true,3,24);
			this.setLiveness(true,3,37);
			this.setLiveness(true,3,38);
			this.setLiveness(true,4,3);
			this.setLiveness(true,4,4);
			this.setLiveness(true,4,13);
			this.setLiveness(true,4,19);
			this.setLiveness(true,4,23);
			this.setLiveness(true,4,24);
			this.setLiveness(true,5,3);
			this.setLiveness(true,5,4);
			this.setLiveness(true,5,13);
			this.setLiveness(true,5,17);
			this.setLiveness(true,5,19);
			this.setLiveness(true,5,20);
			this.setLiveness(true,5,25);
			this.setLiveness(true,5,27);
			this.setLiveness(true,6,13);
			this.setLiveness(true,6,19);
			this.setLiveness(true,6,27);
			this.setLiveness(true,7,14);
			this.setLiveness(true,7,18);
			this.setLiveness(true,8,15);
			this.setLiveness(true,8,16);


		}
	}

	public void glider() {
		clear();
		if (this.getRows() < 10 || this.getColumns() < 10) {
			System.out.println("Grid is too small for premade pattern Four Blinkers. " +
					"Conway must be at least 10x10");
		}
		else {

			this.setLiveness(true,1,1);
			this.setLiveness(true,1,3);
			this.setLiveness(true,2,2);
			this.setLiveness(true,2,3);
			this.setLiveness(true,3,2);

		}
	}

	public void yourDesignOne() {
		clear();
		if (this.getRows() < 50 || this.getColumns() < 50) {
			System.out.println("Grid is too small. Must be at least 50x50.");
		} else {
			setLiveness(true, 0, 0);
			setLiveness(true, 0, 49);
			setLiveness(true, 1, 1);
			setLiveness(true, 1, 48);
			setLiveness(true, 2, 2);
			setLiveness(true, 2, 47);
			setLiveness(true, 3, 3);
			setLiveness(true, 3, 46);
			setLiveness(true, 4, 4);
			setLiveness(true, 4, 45);
			setLiveness(true, 5, 5);
			setLiveness(true, 5, 44);
			setLiveness(true, 6, 6);
			setLiveness(true, 6, 43);
			setLiveness(true, 7, 7);
			setLiveness(true, 7, 42);
			setLiveness(true, 8, 8);
			setLiveness(true, 8, 41);
			setLiveness(true, 9, 9);
			setLiveness(true, 9, 40);
			setLiveness(true, 10, 10);
			setLiveness(true, 10, 39);
			setLiveness(true, 11, 11);
			setLiveness(true, 11, 38);
			setLiveness(true, 12, 12);
			setLiveness(true, 12, 37);
			setLiveness(true, 13, 13);
			setLiveness(true, 13, 36);
			setLiveness(true, 14, 14);
			setLiveness(true, 14, 35);
			setLiveness(true, 15, 15);
			setLiveness(true, 15, 34);
			setLiveness(true, 16, 16);
			setLiveness(true, 16, 33);
			setLiveness(true, 17, 17);
			setLiveness(true, 17, 32);
			setLiveness(true, 18, 18);
			setLiveness(true, 18, 31);
			setLiveness(true, 19, 19);
			setLiveness(true, 19, 30);
			setLiveness(true, 20, 20);
			setLiveness(true, 20, 29);
			setLiveness(true, 21, 21);
			setLiveness(true, 21, 22);
			setLiveness(true, 21, 23);
			setLiveness(true, 21, 24);
			setLiveness(true, 21, 25);
			setLiveness(true, 21, 26);
			setLiveness(true, 21, 27);
			setLiveness(true, 21, 28);
			setLiveness(true, 22, 21);
			setLiveness(true, 22, 22);
			setLiveness(true, 22, 23);
			setLiveness(true, 22, 24);
			setLiveness(true, 22, 25);
			setLiveness(true, 22, 26);
			setLiveness(true, 22, 27);
			setLiveness(true, 22, 28);
			setLiveness(true, 23, 21);
			setLiveness(true, 23, 22);
			setLiveness(true, 23, 27);
			setLiveness(true, 23, 28);
			setLiveness(true, 24, 21);
			setLiveness(true, 24, 22);
			setLiveness(true, 24, 24);
			setLiveness(true, 24, 25);
			setLiveness(true, 24, 27);
			setLiveness(true, 24, 28);
			setLiveness(true, 25, 21);
			setLiveness(true, 25, 22);
			setLiveness(true, 25, 24);
			setLiveness(true, 25, 25);
			setLiveness(true, 25, 27);
			setLiveness(true, 25, 28);
			setLiveness(true, 26, 21);
			setLiveness(true, 26, 22);
			setLiveness(true, 26, 27);
			setLiveness(true, 26, 28);
			setLiveness(true, 27, 21);
			setLiveness(true, 27, 22);
			setLiveness(true, 27, 23);
			setLiveness(true, 27, 24);
			setLiveness(true, 27, 25);
			setLiveness(true, 27, 26);
			setLiveness(true, 27, 27);
			setLiveness(true, 27, 28);
			setLiveness(true, 28, 21);
			setLiveness(true, 28, 22);
			setLiveness(true, 28, 23);
			setLiveness(true, 28, 24);
			setLiveness(true, 28, 25);
			setLiveness(true, 28, 26);
			setLiveness(true, 28, 27);
			setLiveness(true, 28, 28);
			setLiveness(true, 29, 20);
			setLiveness(true, 29, 29);
			setLiveness(true, 30, 19);
			setLiveness(true, 30, 30);
			setLiveness(true, 31, 18);
			setLiveness(true, 31, 31);
			setLiveness(true, 32, 17);
			setLiveness(true, 32, 32);
			setLiveness(true, 33, 16);
			setLiveness(true, 33, 33);
			setLiveness(true, 34, 15);
			setLiveness(true, 34, 34);
			setLiveness(true, 35, 14);
			setLiveness(true, 35, 35);
			setLiveness(true, 36, 13);
			setLiveness(true, 36, 36);
			setLiveness(true, 37, 12);
			setLiveness(true, 37, 37);
			setLiveness(true, 38, 11);
			setLiveness(true, 38, 38);
			setLiveness(true, 39, 10);
			setLiveness(true, 39, 39);
			setLiveness(true, 40, 9);
			setLiveness(true, 40, 40);
			setLiveness(true, 41, 8);
			setLiveness(true, 41, 41);
			setLiveness(true, 42, 7);
			setLiveness(true, 42, 42);
			setLiveness(true, 43, 6);
			setLiveness(true, 43, 43);
			setLiveness(true, 44, 5);
			setLiveness(true, 44, 44);
			setLiveness(true, 45, 4);
			setLiveness(true, 45, 45);
			setLiveness(true, 46, 3);
			setLiveness(true, 46, 46);
			setLiveness(true, 47, 2);
			setLiveness(true, 47, 47);
			setLiveness(true, 48, 1);
			setLiveness(true, 48, 48);
			setLiveness(true, 49, 0);
			setLiveness(true, 49, 49);
		}
	}

	public void yourDesignTwo() {
		clear();
		if (this.getRows() < 50 || this.getColumns() < 50) {
			System.out.println("Grid is too small. Must be at least 50x50.");
		} else {
			setLiveness(true, 0, 0);
			setLiveness(true, 0, 49);
			setLiveness(true, 1, 1);
			setLiveness(true, 1, 48);
			setLiveness(true, 2, 2);
			setLiveness(true, 2, 47);
			setLiveness(true, 3, 3);
			setLiveness(true, 3, 46);
			setLiveness(true, 4, 4);
			setLiveness(true, 4, 45);
			setLiveness(true, 5, 5);
			setLiveness(true, 5, 44);
			setLiveness(true, 6, 6);
			setLiveness(true, 6, 43);
			setLiveness(true, 7, 7);
			setLiveness(true, 7, 42);
			setLiveness(true, 8, 8);
			setLiveness(true, 8, 19);
			setLiveness(true, 8, 20);
			setLiveness(true, 8, 29);
			setLiveness(true, 8, 30);
			setLiveness(true, 8, 41);
			setLiveness(true, 9, 9);
			setLiveness(true, 9, 18);
			setLiveness(true, 9, 21);
			setLiveness(true, 9, 28);
			setLiveness(true, 9, 31);
			setLiveness(true, 9, 40);
			setLiveness(true, 10, 10);
			setLiveness(true, 10, 19);
			setLiveness(true, 10, 20);
			setLiveness(true, 10, 29);
			setLiveness(true, 10, 30);
			setLiveness(true, 10, 39);
			setLiveness(true, 11, 11);
			setLiveness(true, 11, 18);
			setLiveness(true, 11, 31);
			setLiveness(true, 11, 38);
			setLiveness(true, 12, 12);
			setLiveness(true, 12, 17);
			setLiveness(true, 12, 32);
			setLiveness(true, 12, 37);
			setLiveness(true, 13, 13);
			setLiveness(true, 13, 16);
			setLiveness(true, 13, 33);
			setLiveness(true, 13, 36);
			setLiveness(true, 14, 14);
			setLiveness(true, 14, 15);
			setLiveness(true, 14, 34);
			setLiveness(true, 14, 35);
			setLiveness(true, 15, 0);
			setLiveness(true, 15, 14);
			setLiveness(true, 15, 15);
			setLiveness(true, 15, 34);
			setLiveness(true, 15, 35);
			setLiveness(true, 15, 49);
			setLiveness(true, 16, 0);
			setLiveness(true, 16, 13);
			setLiveness(true, 16, 16);
			setLiveness(true, 16, 33);
			setLiveness(true, 16, 36);
			setLiveness(true, 16, 49);
			setLiveness(true, 17, 0);
			setLiveness(true, 17, 12);
			setLiveness(true, 17, 17);
			setLiveness(true, 17, 32);
			setLiveness(true, 17, 37);
			setLiveness(true, 17, 49);
			setLiveness(true, 18, 0);
			setLiveness(true, 18, 9);
			setLiveness(true, 18, 11);
			setLiveness(true, 18, 18);
			setLiveness(true, 18, 31);
			setLiveness(true, 18, 38);
			setLiveness(true, 18, 40);
			setLiveness(true, 18, 49);
			setLiveness(true, 19, 0);
			setLiveness(true, 19, 8);
			setLiveness(true, 19, 10);
			setLiveness(true, 19, 19);
			setLiveness(true, 19, 30);
			setLiveness(true, 19, 39);
			setLiveness(true, 19, 41);
			setLiveness(true, 19, 49);
			setLiveness(true, 20, 0);
			setLiveness(true, 20, 8);
			setLiveness(true, 20, 10);
			setLiveness(true, 20, 20);
			setLiveness(true, 20, 29);
			setLiveness(true, 20, 39);
			setLiveness(true, 20, 41);
			setLiveness(true, 20, 49);
			setLiveness(true, 21, 0);
			setLiveness(true, 21, 9);
			setLiveness(true, 21, 21);
			setLiveness(true, 21, 28);
			setLiveness(true, 21, 40);
			setLiveness(true, 21, 49);
			setLiveness(true, 22, 0);
			setLiveness(true, 22, 22);
			setLiveness(true, 22, 27);
			setLiveness(true, 22, 49);
			setLiveness(true, 23, 1);
			setLiveness(true, 23, 23);
			setLiveness(true, 23, 26);
			setLiveness(true, 23, 48);
			setLiveness(true, 24, 2);
			setLiveness(true, 24, 3);
			setLiveness(true, 24, 4);
			setLiveness(true, 24, 5);
			setLiveness(true, 24, 6);
			setLiveness(true, 24, 24);
			setLiveness(true, 24, 25);
			setLiveness(true, 24, 43);
			setLiveness(true, 24, 44);
			setLiveness(true, 24, 45);
			setLiveness(true, 24, 46);
			setLiveness(true, 24, 47);
			setLiveness(true, 25, 5);
			setLiveness(true, 25, 6);
			setLiveness(true, 25, 24);
			setLiveness(true, 25, 25);
			setLiveness(true, 25, 43);
			setLiveness(true, 25, 44);
			setLiveness(true, 26, 23);
			setLiveness(true, 26, 26);
			setLiveness(true, 27, 22);
			setLiveness(true, 27, 27);
			setLiveness(true, 28, 9);
			setLiveness(true, 28, 21);
			setLiveness(true, 28, 28);
			setLiveness(true, 28, 40);
			setLiveness(true, 29, 8);
			setLiveness(true, 29, 10);
			setLiveness(true, 29, 20);
			setLiveness(true, 29, 29);
			setLiveness(true, 29, 39);
			setLiveness(true, 29, 41);
			setLiveness(true, 30, 8);
			setLiveness(true, 30, 10);
			setLiveness(true, 30, 19);
			setLiveness(true, 30, 30);
			setLiveness(true, 30, 39);
			setLiveness(true, 30, 41);
			setLiveness(true, 31, 9);
			setLiveness(true, 31, 11);
			setLiveness(true, 31, 18);
			setLiveness(true, 31, 31);
			setLiveness(true, 31, 38);
			setLiveness(true, 31, 40);
			setLiveness(true, 32, 12);
			setLiveness(true, 32, 17);
			setLiveness(true, 32, 32);
			setLiveness(true, 32, 37);
			setLiveness(true, 33, 13);
			setLiveness(true, 33, 16);
			setLiveness(true, 33, 33);
			setLiveness(true, 33, 36);
			setLiveness(true, 34, 14);
			setLiveness(true, 34, 15);
			setLiveness(true, 34, 34);
			setLiveness(true, 34, 35);
			setLiveness(true, 35, 14);
			setLiveness(true, 35, 15);
			setLiveness(true, 35, 34);
			setLiveness(true, 35, 35);
			setLiveness(true, 36, 16);
			setLiveness(true, 36, 33);
			setLiveness(true, 37, 17);
			setLiveness(true, 37, 32);
			setLiveness(true, 38, 18);
			setLiveness(true, 38, 31);
			setLiveness(true, 39, 19);
			setLiveness(true, 39, 20);
			setLiveness(true, 39, 29);
			setLiveness(true, 39, 30);
			setLiveness(true, 40, 18);
			setLiveness(true, 40, 21);
			setLiveness(true, 40, 28);
			setLiveness(true, 40, 31);
			setLiveness(true, 41, 19);
			setLiveness(true, 41, 20);
			setLiveness(true, 41, 29);
			setLiveness(true, 41, 30);
			setLiveness(true, 42, 18);
			setLiveness(true, 42, 31);
			setLiveness(true, 43, 18);
			setLiveness(true, 43, 31);
			setLiveness(true, 44, 18);
			setLiveness(true, 44, 31);
			setLiveness(true, 45, 18);
			setLiveness(true, 45, 31);
			setLiveness(true, 46, 18);
			setLiveness(true, 46, 31);
			setLiveness(true, 47, 18);
			setLiveness(true, 47, 31);
			setLiveness(true, 48, 17);
			setLiveness(true, 48, 32);
			setLiveness(true, 49, 0);
			setLiveness(true, 49, 1);
			setLiveness(true, 49, 2);
			setLiveness(true, 49, 3);
			setLiveness(true, 49, 4);
			setLiveness(true, 49, 5);
			setLiveness(true, 49, 6);
			setLiveness(true, 49, 7);
			setLiveness(true, 49, 8);
			setLiveness(true, 49, 9);
			setLiveness(true, 49, 10);
			setLiveness(true, 49, 11);
			setLiveness(true, 49, 12);
			setLiveness(true, 49, 13);
			setLiveness(true, 49, 14);
			setLiveness(true, 49, 15);
			setLiveness(true, 49, 16);
			setLiveness(true, 49, 33);
			setLiveness(true, 49, 34);
			setLiveness(true, 49, 35);
			setLiveness(true, 49, 36);
			setLiveness(true, 49, 37);
			setLiveness(true, 49, 38);
			setLiveness(true, 49, 39);
			setLiveness(true, 49, 40);
			setLiveness(true, 49, 41);
			setLiveness(true, 49, 42);
			setLiveness(true, 49, 43);
			setLiveness(true, 49, 44);
			setLiveness(true, 49, 45);
			setLiveness(true, 49, 46);
			setLiveness(true, 49, 47);
			setLiveness(true, 49, 48);
			setLiveness(true, 49, 49);
		}
	}


	public void logAndCapture() {
		System.out.println("Beginning of Log and Capture");
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				if (this.isAlive(i, j)) {
					System.out.println("setLiveness(true, " + i + ", " + j + ");");
				}
			}
		}
		System.out.println("End of Log and Capture");
	}

}