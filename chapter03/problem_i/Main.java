import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * 중복을 포함해 세 카드의 합으로 만들 수 있는 당첨번호들의 리스트를 반환하는 함수
	 * @param n     카드의 수
	 * @param m     검사하려는 당첨번호의 수
	 * @param cards   각 카드에 적힌 숫자들
	 * @param target  검사하려는 각 당첨번호 리스트
	 * @return      세 카드의 합으로 표현될 수 있는 당첨번호들의 오름차순 리스트
	 */
	public static ArrayList<Integer> getPossibleTargets(int n, int m, int[] cards, int[] target)
	{
		ArrayList<Integer> possibleTargets = new ArrayList<>(); //만들 수 있는 당첨번호들

		Arrays.sort(cards);
		
		for(int k : target)
		{ //검사 할 모든 당첨 번호 k에 대하여

			boolean possible = false; // k를 세 숫자의 합으로 표현할 수 있는지 여부
			for(int i = 0 ; i < n ; i+= 1)
			{   // 카드 중 하나 x를 선택한다.
				int x = cards[i];
				for(int j = 0 ; j <= i ; j += 1)
				{   // 카드 중 하나 y를 선택한다
					int y = cards[j];
					int z = k - (x + y); // k = (x +  y )+ z 가 되는 z를 계산한다
					
					// z가 cards[]에 존재한다면?
					if( Arrays.binarySearch(cards, z) >= 0 ) {
						// k = x + y + z 가 가능한 <x, y, z>가 존재한다.
						possible = true;
						break;
					}
				}
				if(possible)
				{   // 이미 찾았다면 탈출한다
					break;
				}
			}
			if(possible)
			{	// 세 카드의 합으로 k를 만들 수 있다면, 추가한다.
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
		{
			System.out.print("NO");
		}else{
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
