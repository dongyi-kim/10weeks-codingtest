import java.util.*;
import java.io.*;


public class Main{

	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase()
	{
		int n = scanner.nextInt();
		int m = scanner.nextInt();


		int[] out = new int[n+1]; //out[i] := i가 해체되었을 때 폭발하는 폭탄의 수 
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();    //adj.get(i) := i번 폭탄이 해체되었을 때 연쇄관계가 줄어드는 폭탄의 리스트
			

		//adj 초기화 
		//0 ~ N 까지 차례로 새로운 리스트를 만들어 추가해준다.
		//실제 사용은 1~N만 사용한다
		for(int i = 0 ; i <= n ; i ++){
			adj.add(new ArrayList<>());
		}

		for(int i = 0 ; i < m ; i++)
		{
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			//u가 해체되면 v가 폭발한다

			//반대로 v가 해체되면 u는 연쇄관계가 줄어든다 
			adj.get(v).add(u);
		}

		boolean flag = true; //모두 안전하게 제거 가능하면 true
		for(int i = 0 ;  i < n ; i ++)
		{   //총 n번 반복하며 폭탄을 하나씩 제거해나간다

			int target = -1;    //이번에 폭탄을 제거할 타겟 
			for(int j = 1; j <= n ; j++)
			{
				if(out[j] == 0)
				{   //out[j]가 0인 j가 제거 가능한 폭탄이다 
					target = j;
					break;
				}
			}

			if(target == -1)
			{   //target이 -1로 남아있다는 건 해체 가능한 폭탄이 없다는 것이다
				//불가능으로 체크하고 종료
				flag = false;
				break;
			}
			//target를 해체하고 out[target]를 -1로 변경하자
			out[target] = -1; 
			
			//기존에 target때문에 해체되지 못하던 연쇄관계의 폭탄들을 조회하며 
			for(int linked : adj.get(target))
			{
				//target이 제거되었으므로 out을 1감소시켜 준다
				// => linked 기준에서는 해체를 막던 폭탄이 하나 제거된 것 이므로
				out[linked] --;	
			}
		}
		
		//모든 폭탄을 제거했으면 flag가 true일 것이다 
		if(flag){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}

	public static void main(String[] args)
	{
		int caseNum = scanner.nextInt();
		for(int caseIndex = 0; caseIndex < caseNum; caseIndex ++)
		{
			testCase();
		}	
	}
}
