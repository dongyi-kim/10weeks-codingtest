
import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {
		int M = scanner.nextInt();
		int N = scanner.nextInt();
		int x = scanner.nextInt();
		int y = scanner.nextInt();

		// <1,1> ~ <M, N> 연도를 표현하는 카잉 달력을 생성한다
		KaingCalendar calendar = new KaingCalendar(M, N);

		// 이 달력에서 <x, y>가 몇 번째 연도인지 계산한다
		int answer = calendar.getIndex(x, y);

		// 정답을 출력한다
		System.out.println(answer);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class KaingCalendar
{
	final int M;    // 왼쪽 번호의 최대 값
	final int N;    // 오른쪽 번호의 최대 값

	KaingCalendar(int M, int N)
	{
		this.M   = M;
		this.N   = N;
	}

	/**
	 * 'index'번째 날짜의 X(왼쪽 번호)를 반환하는 함수
	 *
	 * @param index
	 * @return
	 */
	public int getXbyIndex(int index)
	{
		return (index-1) % M + 1;
	}

	/**
	 * 'index'번째 날짜의 Y(오른쪽 번호)를 반환하는 함수
	 *
	 * @param index
	 * @return
	 */
	public int getYByIndex(int index)
	{
		return (index-1) % N + 1;
	}

	/**
	 *
	 *
	 * @param x
	 * @param y
	 * @return  이 달력에서 <x, y>라는 연도가 최초로 등장하는 순번
	 *          등장하지 않는 연도라면 -1을 반환한다
	 */
	public int getIndex(int x, int y)
	{
		for(int index = x; index <= M * N ; index += M)
		{
			if(getYByIndex(index) == y)
			{
				return index;
			}
		}
		return -1;
	}
}