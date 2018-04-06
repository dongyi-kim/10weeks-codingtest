package Chapter02.Problem02E;
/**
 * 코드를 제출하기 전에 꼭!!! 위의 패키지 임포트 명령어를 제거해주세요!
 **/

import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);

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
