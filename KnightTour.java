public class KnightTour {
	private static int N = 8;
	// thiết lập các ô vuông hợp lý để con mã có thể đặt trên bàn cờ
	private static boolean isValid (int x, int y, int[][] board) {
		return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
	}

	private static void printBoard(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.format("%3d ", board[i][j]);
			}
			System.out.println();
		}
	}

	private static boolean knightsTour() {
		// khởi tạo ma trận
		int[][] board = new int[N][N];
		// thiết lập ma trận với giá trị -1 là các ô vuông trên ma trận để đánh dấu là chưa sử dụng
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = -1;
			}
		}
		// bắt đầu từ ô (0,0)
		board[0][0] = 0;
	   
		// định nghĩa sẵn các bước đi của con mã (đi với hình chữ L)
		int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
		int[] yMove = {-1, -2, -2, -1, 1, 2, 2, 1};
	  
		// nếu sau khi chạy hàm đệ quy, không có nghiệm thì
		if (!tourUtility(0, 0, 1, board, xMove, yMove)) {
			System.out.println("No solutions");
			return false;
		}
		else {
			printBoard(board);
			return true;
		}

	}

	// đệ quy
	private static boolean tourUtility(int x, int y, int move, int[][] board, int[] xMove, int[] yMove) {
	// nếu số bước đi là n^2 thì trả về true
		if (move == N * N) {
			return true;
		}

		int next_x, next_y;

		for (int k = 0; k < N; k++) {
			// di chuyển
			next_x = x + xMove[k];
			next_y = y + yMove[k];
			if (isValid(next_x, next_y, board)) {
			   // nếu ô cờ chưa từng được đi thì thêm số bước 
				board[next_x][next_y] = move;
				if (tourUtility(next_x, next_y, move+1, board, xMove, yMove)) {
					return true;
				} else {
				   // quay lui nếu gọi hàm đệ quy lỗi và bắt đầu lại từ đầu
					board[next_x][next_y] = -1;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		knightsTour();
	}
}