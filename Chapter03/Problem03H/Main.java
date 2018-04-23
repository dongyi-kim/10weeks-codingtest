import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * 중복을 포함해 두 카드의 합으로 만들 수 있는 당첨번호의 수를 계산하는 함수
	 * @param n     카드의 수
	 * @param m     검사하려는 당첨번호의 수
	 * @param cards   각 카드에 적힌 숫자들
	 * @param target  검사하려는 각 당첨번호 리스트
	 * @return
	 */
	public static int getPossibleTargetNumber(int n, int m, int[] cards, int[] target)
	{
		int answer = 0; //만들 수 있는 당첨번호의 수

		Arrays.sort(cards);

		for(int k : target)
		{ // 모든 타겟 k에 대하여

			boolean possible = false; // 두 수의 합으로 만들 수 있는지 여부

			for(int x : cards)
			{ 	// 모든 카드 중 하나인 x에 대하여

				// x와 k가 상수이므로 y를 계산할 수 있다.
				int y = k - x;

				if(Arrays.binarySearch(cards, y) >= 0 )
				{  //배열에 y가 존재한다 <==> x+y=k가 되는 <x, y>가 존재한다.
					//이를 표시하고 나간다.
					possible = true;
					break;
				}
			}

			if(possible)
			{ 	// z가 두 수의 조합으로 표현될 수 있다면,
				// 가능한 숫자의 갯수를 증가시킨다.
				answer += 1;
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int n = scanner.nextInt();  // 카드의 수
		int m = scanner.nextInt();  // 검사 할 후보 당첨번호의 수

		int[] cards = new int[n];
		int[] targets = new int[m];

		// 각 카드 정보를 입력받는다
		for(int i = 0 ; i < n ; i ++)
		{
			cards[i] = scanner.nextInt();
		}

		// 각 당첨번호를 입력받는다
		for(int i = 0 ; i < m ; i ++)
		{
			targets[i] = scanner.nextInt();
		}

		int answer = getPossibleTargetNumber(n, m, cards, targets);

		System.out.println(answer);
	}

}
