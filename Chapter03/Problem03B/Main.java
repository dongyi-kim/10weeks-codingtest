import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAX_SEAT_NUMBER = 1000;
	public static final int MAX_COLOR_NUMBER = 100;


	public static void fillFrequencyTable(int[] data, int n, int[] table) {
		for (int i = 0; i < table.length; i += 1) {
			table[i] = 0;
		}
		for (int i = 0; i < n; i += 1) {
			int index = data[i];
			table[index] += 1;
		}
	}

	/**
	 *
	 * @param n : 좌석의 수. 좌석은 0~(n-1)번의 번호를 가진다.
	 * @param m : 좌석을 칠한 횟수.
	 * @param paintings  : 좌석들을 색칠한 이벤트들의 정보
	 */
	public static void solve(int n, int m, Painting[] paintings)
	{
		int[] seats = new int[n];
//		for(int i = 0 ; i < n ; i ++)
//		{
//			seats[i] = 0;
//		}

		//색칠 정보들이 주어진 순서대로 각 좌석을 색칠(색상 대입)한다.
		for(int i = 0 ; i < m ; i ++)
		{
			//모든 색칠 정보 p에 대하여 차례로
			Painting p = paintings[i];
			for(int index = p.left ; index <= p.right; index += 1)
			{ //색칠한 영역에 대해 해당 색상을 대입한다.
				seats[index] = p.color;
			}
		}

		int minColor = seats[0]; //가장 적게 등장한 색상
		int maxColor = seats[0]; //가장 많이 등장한 색상

		//모든 색칠을 완료한 이후의 색상 정보를 사용하여
		//모든 색상에 대한 빈도수 테이블을 계산한다
		int[] table = new int[MAX_COLOR_NUMBER];
		fillFrequencyTable(seats, n, table);

		for(int color = 0 ; color <= 99; color += 1)
		{
			if(table[color] == 0) {
				continue;
			}
			//한 번 이상 등장한 모든 색깔 color에 대하여

			if(minColor == -1 || table[minColor] > table[color])
			{ //가장 적게 등장한 색이 아직 없거나, color가 더 적게 등장한 경우
				minColor = color;
			}
			if(maxColor == -1 || table[maxColor] < table[color])
			{ //가장 많이 등장한 색이 아직 없거나, color가 더 많이 등장한 경우
				maxColor = color;
			}

		}

		System.out.println(maxColor);
		System.out.println(minColor);
	}

	public static void main(String[] args) throws Exception {
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		//paintings[i] := i번째에 일어난 색칠 이벤트의 정보
		Painting[] paintings = new Painting[m];


		for(int i = 0 ; i < m ; i ++)
		{
			//좌석의 번호는 1번부터 시작하므로, 0 ~ (n-1)범위로 맞추기 위하여 1씩 빼준다
			int left = scanner.nextInt()  -1;
			int right = scanner.nextInt() -1;
			int color = scanner.nextInt();
			paintings[i] = new Painting(left, right, color);
		}

		//문제의 정답을 구한다
		solve(n, m, paintings);
	}

}

//좌석들을 한 번 색칠하는 이벤트에 대한 정보
class Painting{
	int left;
	int right;
	int color;
	Painting(int left, int right, int color)
	{
		this.left = left;
		this.right = right;
		this.color = color;
	}
}