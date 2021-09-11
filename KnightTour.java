class KnightTour {
	private int count;
    private int BOARD_SIZE;
	private int[][] visited;
	private int[] xMoves = { 2, 1, -1, -2, -2, -1, 1, 2 };
	private int[] yMoves = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public KnightTour(int chessBoardSize) {
		this.BOARD_SIZE = chessBoardSize;
		this.visited = new int[BOARD_SIZE][BOARD_SIZE];
		this.initializeBoard();
	}
	
	private void initializeBoard() {
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				this.visited[i][j] = Integer.MIN_VALUE;
	}

	public void solveKnightTourProblem() {
		visited[0][0] = 0;
		// bắt đầu từ ô cờ vị trí (0,0)
		if( solveProblem(1, 0, 0) == false) {
			System.out.println("Không có cách giải nào được tìm thấy...");
		}
	}

	public boolean solveProblem(int moveCount, int x, int y) {
		if (moveCount == BOARD_SIZE * BOARD_SIZE) {
			printSolution();
			return true;
		}
		
		boolean res = false;
		for (int i = 0; i < xMoves.length; ++i) {

			int nextX = x + xMoves[i];
			int nextY = y + yMoves[i];

			// kiểm tra nếu vị trí mới hợp lệ và chưa được đi qua
			if ( isValidMove(nextX, nextY) && visited[nextX][nextY] == Integer.MIN_VALUE) {

				visited[nextX][nextY] = moveCount;
				res = solveProblem(moveCount + 1, nextX, nextY) || res;
				
                // quay lui
				visited[nextX][nextY] = Integer.MIN_VALUE; 
			}
		}
		return res;
	}

	public boolean isValidMove(int x, int y) {
		if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
			return false;
		} else {
			return true;	
		}
	}

	public void printSolution() {
		System.out.println("======================================================");
		System.out.println("Cách giải " + (++count));
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.format("%3d ", visited[i][j]);
			}

			System.out.println();
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		final int chess_board_size = 5;
		KnightTour knightTour = new KnightTour(chess_board_size);
		knightTour.solveKnightTourProblem();	
	}
}