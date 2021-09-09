import java.util.Scanner;

public class HorseTour {
	private int n;
	private int x, y;
	
	private int hang[];
	private int cot[];
	private boolean cx[][];
	private int a[][];
	private int dem;
	
	public void Nhap() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Nhap N = "); n = scan.nextInt();
		System.out.print("Nhap x = "); x = scan.nextInt();
		System.out.print("Nhap y = "); y = scan.nextInt();
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
				
		cx = new boolean[n+4][n+4];
		a = new int[n+4][n+4];
		
		int i, j;
		
		for (i=0; i<n+4; i++)
			for (j=0; j<n+4; j++)
				cx[i][j] = false;
		
		for (i=2; i<n+2; i++)
			for (j=2; j<n+2; j++)
				cx[i][j] = true;
		
		cx[x+1][y+1] = false;
		a[x+1][y+1] = 1;
		dem = 0;
	}
	
	public void InKQ() {
		dem++;
		System.out.println("Cach thu " + dem + ": ");
		int i, j;
		for (i=2; i<n+2; i++) {
			for (j=2; j<n+2; j++) System.out.format("%3d ", a[i][j]);
			System.out.println();
		}
	}
	
	public void Try2(int x0, int y0, int i) {
		int j;
		for (j=0; j<8; j++)
			if (cx[x0+hang[j]][y0+cot[j]] == true) {
				a[x0+hang[j]][y0+cot[j]] = i;
				cx[x0+hang[j]][y0+cot[j]] = false;
				if (i == n*n) InKQ();
				else Try2(x0+hang[j], y0+cot[j], i+1);
				cx[x0+hang[j]][y0+cot[j]] = true;
			}
	}
	
	
	
	public static void main(String[] args) {
		HorseTour t = new HorseTour();
		t.Nhap();
		t.Init();
		t.Try2(t.x+1, t.y+1, 2);
		if (t.dem == 0) System.out.println("Bai toan khong co nghiem");
	}

}