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
		ArrayList<CardPair> pairs = new ArrayList<>();
		for(int i = 0; i < n ; i ++)
		{
			for(int j = 0 ; j <= i; j++)
			{   // 모든 카드의 조합 <i, j>에 대하여, 두 카드를 짝지은 정보를 모두 저장한다
				CardPair pair = new CardPair( cards[i], cards[j] );
				pairs.add(pair);
			}
		}

		// 바이너리 서치를 할 수 있도록 정렬한다.
		// 클래스 내에서 비교 연산자를 정의했기 때문에, 정렬할 수 있다.
		Collections.sort(pairs);

		for(int k : target)
		{ // 검사해 볼 모든 당첨 후보번호 k에 대하여
			boolean possible = false;
			for(CardPair knownPair : pairs)
			{   // 임의의 두 카드 < p, q >를 나타내는 짝 knownPair에 대하여
				int x = knownPair.sumOfCards; // x = ( p + q ) 라고 하자.

				// 남은 두 카드를 r, s라고 한다면
				// y = r + s라고 할 때 아래가 성립한다.
				int y = k - x;
				CardPair targetPair = new CardPair(y); //두 카드의 합이 y가 되는 짝이 있다고 가정하자

				//그런 짝이 pairs에 존재한다는 말은?
				//기존에 존재하던 cards[]의 두 카드의 합으로, y를 만들어낼 수 있다!
				int pairIndex = Collections.binarySearch(pairs, targetPair);
 				if( pairIndex >= 0)
				{   // 그러므로, k는 네 카드의 합으로 표현 가능하다
					possible = true;

					// 아래와 같이 어떤 네 카드가 선택되었는지도 알 수 있다!
					//CardPair pair1 = knownPair;
					//CardPair pair2 = pairs.get( pairIndex );
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


class CardPair implements Comparable<CardPair>
{   // 두 개의 카드 조합을 나타내는 클래스

	int card1;
	int card2;
	int sumOfCards; //두 카드의 합

	//두 카드의 정보를 알 때
	CardPair(int card1, int card2)
	{
		this.card1 = card1;
		this.card2 = card2;
		this.sumOfCards = this.card1 + this.card2;
	}

	// 두 카드의 정보를 모르고 합만 알 때
	CardPair(int sumOfCards)
	{
		this.sumOfCards = sumOfCards;
		this.card1 = -1;
		this.card2 = -1;
	}

	// 두 카드의 합으로 짝들의 대소 관계를 정의한다.
	@Override
	public int compareTo(CardPair o) {
		return this.sumOfCards - o.sumOfCards;
	}
}


