import java.util.*;
import java.io.*;


public class Main{

	public static final Scanner scanner = new Scanner(System.in);
	
	//출력 횟수가 많으므로 BufferedWriter를 사용한다 
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	//각 테스트케이스에 대하여 
	public static void testCase() throws Exception
	{
		int nPizza = scanner.nextInt(); //피자의 수 

		//모든 피자에서 한 번 이상 등장한 재료들의 이름을 언어별로 모두 저장해둔다 
		TreeSet<String> english = new TreeSet<>(); //모든 영어표기 재료
		TreeSet<String> foreign = new TreeSet<>(); //모든 외국어 표기 재료

		//각 피자별/언어별 사용된 재료의 목록을 저장한다 
		//englishPizza.get(i) := i번째 피자에 사용된 영어 재료명 집합 
		ArrayList<TreeSet<String> > englishPizza = new ArrayList<>();
		ArrayList<TreeSet<String> > foreignPizza = new ArrayList<>();
		
		//각 피자에 대하여 재료를 입력받는다
		for(int i = 0 ; i < nPizza; i++)
		{	
			//피자 이름
			String pizzaName = scanner.next();

			//이 피자에서 사용되는 영어와 외국어 재료 명들을 저장할 집합 
			TreeSet<String> englishIngredients = new TreeSet<>();
			TreeSet<String> foreignIngredients = new TreeSet<>();

			int na = scanner.nextInt(); //영어 표기 재료의 수 
			for(int j = 0 ; j < na; j++)
			{	//각 영어 재료명에 대하여 
				String ingredient = scanner.next();
				//이 피자에 대한 영어 재료명 집합에 추가하고 
				englishIngredients.add(ingredient);
				//전체 피자에 대한 영어 재료명 집합에 추가 
				english.add(ingredient);
			}

			int nb = scanner.nextInt(); //외국어 표기 재료의 수 
			for(int j = 0 ; j < nb; j++)
			{	//각 외국어 재료명에 대하여 
				String ingredient = scanner.next();
				//이 피자에 대한 외국어 재료명 집합에 추가하고 
				foreignIngredients.add(ingredient);
				//전체 피자에 대한 외국어 재료명 집합에 추가 
				foreign.add(ingredient);
			}

			//현재 이 피자의 재료명 집합을 피자 리스트에 추가한다 
			englishPizza.add(englishIngredients);
			foreignPizza.add(foreignIngredients);
		}

		for(String ei : english){ //모든 한 번 이상 등장한 영어표기 재료명에 대하여 사전순으로 조회 
			for(String fi : foreign){ //모든 한 번 이상 등장한 외국어 표기 재료명에 대하여 사전순으로 조회
				//영어 표기 ei가 외국어 표기 fi와 같은 재료라고 할 수 있을까?
				
				boolean flag = true; //그렇다면 true, 아니라면 false
				
				//일단 true라고 가정하고 이에 대한 반례를 찾자
				for(int i = 0 ; i < nPizza ; i++)
				{	//각 피자에 대하여
					
					//해당 피자 영어표기 재료명에 ei가 존재하는지 
					boolean existInEnglish = englishPizza.get(i).contains(ei);
					//해당 피자 외국어표기 재료명에 fi가 존재하는지 
					boolean existInForeign = foreignPizza.get(i).contains(fi);
					
					//둘이 같은 재료라면 둘 다 존재하거나 둘 다 존재하지 않아야 한다
					if(existInEnglish != existInForeign)
					{	//존재 여부가 서로 다르다면 이 쌍에 대한 반례가 되므로 flag를 false로 하고 종료 
						flag = false;
						break;
					}
				}

				//이 쌍에 대하여 반례가 없다면 올바른 쌍이므로 출력
				if(flag)
				{
					writer.write(String.format("(%s, %s)\n", ei, fi));
				}

			}
		}
	}

	public static void main(String[] args) throws Exception {
		int caseNum = scanner.nextInt();
		for(int caseIndex = 0; caseIndex < caseNum; caseIndex++)
		{
			testCase();
		}
		writer.close();
	}

}
