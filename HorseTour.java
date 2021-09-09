import java.util.Scanner;

public class HorseTour {
	private int n;
	private int x, y;
	
	private int hang[];
	private int cot[];
	private boolean check[][];
	private int matrix[][];
	private int dem;
	
	public void input() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Nhap kich co ban co n = "); n = scan.nextInt();
		System.out.print("Nhap toa do x = "); x = scan.nextInt();
		System.out.print("Nhap toa do y = "); y = scan.nextInt();
		scan.close();
	}
	
	public void Init() {
		hang = new int[8];
		cot = new int[8];
		
		hang[0] = -2; cot[0] = +1;
		hang[1] = -1; cot[1] = +2;
		hang[2] = +1; cot[2] = +2;
		hang[3] = +2; cot[3] = +1;
		hang[4] = +2; cot[4] = -1;
		hang[5] = +1; cot[5] = -2;
		hang[6] = -1; cot[6] = -2;
		hang[7] = -2; cot[7] = -1;
				
		check = new boolean[n+4][n+4];
		matrix = new int[n+4][n+4];
		
		int i, j;
		
		for (i=0; i<n+4; i++)
			for (j=0; j<n+4; j++)
				check[i][j] = false;
		
		for (i=2; i<n+2; i++)
			for (j=2; j<n+2; j++)
				check[i][j] = true;
		
		check[x+1][y+1] = false;
		matrix[x+1][y+1] = 1;
		dem = 0;
	}
	
	public void output() {
		int i, j;
		for (i=2; i<n+2; i++) {
			for (j=2; j<n+2; j++) {
				System.out.format("%3d ", matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public void Try(int x0, int y0, int i) {
		int x_first = x0;
		int y_first = y0;
		int j;
		for (j=0; j<8; j++)
			if (check[x0+hang[j]][y0+cot[j]] == true) {
				matrix[x0+hang[j]][y0+cot[j]] = i;
				check[x0+hang[j]][y0+cot[j]] = false;
				if (i == n*n && x0==x_first && y0==y_first) {
					output();
					System.exit(0);
				}
				else Try(x0+hang[j], y0+cot[j], i+1);
				check[x0+hang[j]][y0+cot[j]] = true;
			}
	}
	
	
	
	public static void main(String[] args) {
		HorseTour object = new HorseTour();
		object.input();
		object.Init();
		object.Try(object.x+1, object.y+1, 2);
		if (object.dem == 0) 
			System.out.println("Bai toan khong co nghiem");
	}

}