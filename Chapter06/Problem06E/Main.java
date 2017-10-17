import java.util.*;
import java.io.*;
import java.util.Queue;


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
            out[u] ++;
        }

        Queue<Integer> q = new LinkedList<>(); //현재 해체할 수 있는 폭탄의 큐
        for(int i = 1 ; i <=  n ; i ++)
        {   //초기에 바로 제거할 수 있는 폭탄들은 q에 추가해둔다
            if(out[i] == 0){
                q.add(i);
            }
        }



        int removed = 0; //제거 된 폭탄의 수
        while(q.size() > 0 )
        {   //제거 가능한 폭탄이 남아있다면

            int target = q.poll();   //이번에 폭탄을 제거할 타겟을 규에서 하나 꺼낸다
            removed ++; //target을 제거하므로 removed 카운트업

            //기존에 target때문에 해체되지 못하던 연쇄관계의 폭탄들을 조회하며
            for(int linked : adj.get(target))
            {
                //target이 제거되었으므로 out을 1감소시켜 준다
                // => linked 기준에서는 해체를 막던 폭탄이 하나 제거된 것 이므로
                out[linked] --;

                //그 결과 linked번 노드가
                // 자신이 해체 될 시 폭발할 수 있는 모든 폭탄이 제거되었다면
                // 제거 가능대상에 추가한다
                if(out[linked] == 0)
                {
                    q.add(linked);
                }
            }
        }

        //모든 폭탄을 제거했으면 removed == n 이다
        if(removed == n){
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
