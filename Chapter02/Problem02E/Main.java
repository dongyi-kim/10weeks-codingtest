import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    /**
    * 주어진 숫자가 소수인지 판별하는 함수 
    *
    * @param N 
    * @return true   소수라면 
    * @return false  소수가 아니라면
    */
    public static boolean isPrime(int N)
    {
        if( N == 1) return false;   //1은 소수가 아니다
        else if( N == 2 ) return true; //2는 소수다
        else if( N % 2 == 0) return false; //나머지 짝수는 소수가 아니다

        for(int i = 3; i*i <= N; i+=2)
        {   //모든 N미만의 홀수 자연수 i에 대해
            if( N % i == 0 )
            {   //이 범위에 약수가 존재한다면 소수일 수 없다.
                return false;
            }
        }
        //약수가 하나도 존재하지 않았다면.
        return true;
    }

    public static void testCase(int caseIndex)
    {
        int n = scanner.nextInt();
        boolean result = isPrime(n);

        System.out.printf("Case #%d\n", caseIndex);
        if(result)
        {
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    public static void main(String[] args) throws Exception {
        int caseSize = scanner.nextInt();

        for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
            testCase(caseIndex);
        }
    }

}
