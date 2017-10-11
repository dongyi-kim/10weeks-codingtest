import java.util.*;
import java.io.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase()
	{
		int n = scanner.nextInt();
		String fullName = scanner.next();
		String[] words = new String[n];

		for(int i = 0 ; i < n ; i++)
		{
			words[i] = scanner.next();	
		}

		boolean flag = false;

		for(int i = 0 ; i < n ; i++)
		{   //가장 첫 마디 후보 words[i]

			//fullName이 words[i]로 시작하지 않으면 말이 안되므로 가지치기 가능 
			if(!fullName.startsWith(words[i])){
				continue;	
			}

			for(int k = 0 ; k < n ; k ++)
			{   //세 번째 마디 후보  words[k]

				//같은 두 단어를 두 번 사용할 수 없으므로 가지치기 가능 
				if ( i == k ) 
					continue;

				//fullName이 words[k]로 끝나지 않으면 말이 안되므로 가지치기 가능
				if(!fullName.endsWith(words[k])){
					continue;
				}

				for(int j = 0 ; j < n ; j ++)
				{   //두 번째 마디 후보 words[j]

					//같은 두 단어를 두 번 사용할 수 없으므로 가지치기 가능
					if(j == i || j == k)
						continue;

					//세 마디를 차례로 이어붙여서 단어를 만들어보고 
					String result = words[i] + words[j] + words[k];

					//fullName과 같은 조합인지 확인 
					if( result.equals(fullName) ){
						flag = true;
						break;
					}
				}
				//이미 찾았다면 break
				if(flag)
					break;
			}
			//이미 찾았다면 break
			if(flag)
				break;
		}

		//정답을 출력
		if(flag){
			System.out.println("YES");		
		}else{
			System.out.println("NO");
		}

	}
	public static void main(String[] args) {
		int caseNum = scanner.nextInt();
		for(int i = 1 ; i <= caseNum ; i++)
		{
			testCase();
		}
	}
}
