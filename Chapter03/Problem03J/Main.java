import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * 중복을 포함해 네 카드의 합으로 만들 수 있는 당첨번호들의 리스트를 반환하는 함수
	 * @param n     카드의 수
	 * @param m     검사하려는 당첨번호의 수
	 * @param cards   각 카드에 적힌 숫자들
	 * @param target  검사하려는 각 당첨번호 리스트
	 * @return      네 카드의 합으로 표현될 수 있는 당첨번호들의 오름차순 리스트
	 */
	public static ArrayList<Integer> getPossibleTargets(int n, int m, int[] cards, int[] target)
	{
		ArrayList<Integer> possibleTargets = new ArrayList<>(); //만들 수 있는 당첨번호들

		// 두 카드의 합을 모두 저장한다.
		ArrayList<Integer> twoSums = new ArrayList<>();
		for(int i = 0; i < n ; i ++)
		{
			for(int j = 0 ; j <= i; j++)
			{
				int sum = cards[i] + cards[j];
				twoSums.add(sum);
			}
		}

		// 바이너리 서치를 할 수 있도록 정렬한다.
		Collections.sort(twoSums);

		for(int k : target)
		{ // 검사해 볼 모든 k에 대하여

			boolean possible = false;
			for(int x : twoSums)
			{ // k = ( p + q ) + ( r + s )라고할 때
				// 두 수의 합 x = ( p + q )에 대하여
				int y = k - x;  // y = (r + s) 라고 하자

				// 두 수의 합으로 y = (r + s) 또한 표현할 수 있다면
 				if(Collections.binarySearch(twoSums, y) >= 0)
				{ //k는 네 카드의 합으로 표현 가능하다
					possible = true;
					break;
				}
			}

			if(possible)
			{
				possibleTargets.add(k);
			}
		}

		Collections.sort(possibleTargets);
		return possibleTargets;
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

		ArrayList<Integer> answers = getPossibleTargets(n, m, cards, targets);


		if(answers.size() == 0)
		{ // 가능한 당첨번호가 없다면 NO를 출력한다
			System.out.print("NO");
		}else
		{ //가능한 당첨번호가 존재한다면 그 목록을 출력한다.
			for(int i = 0 ; i < answers.size() ; i++)
			{
				if( i > 0 )
				{
					System.out.print(" ");
				}
				System.out.print(answers.get(i));
			}
		}
	}

}
