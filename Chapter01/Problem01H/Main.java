import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);


    /**
    * 평균과의 차가 가장 작은 데이터의 번호를 반환하는 함수
    * 여러 가지라면 가장 빠른 번호를 반환한다.
    *
    * @param data 
    * @param n 
    * @return int  가장 평균과 가까운 데이터의 번호 (1번부터 시작)
    */
    public static int findIndex(int[] data, int n)
    {
        int x = 0; //존재하지 않는 경우는 없으므로, 일단 0이라고 가정
        int S = 0; //모든 데이터의 합

        for(int i = 0 ; i < n ; i++)
        {
            S += data[i];
        }

        for(int i = 0 ; i < n ; i ++)
        {
            int dx = Math.abs(n * data[x] - S); //i까지의 원소들 중 평균과의 최소거리
            int di = Math.abs(n * data[i] - S); //현재 원소와 평균과의 거리
            if( di < dx )
            {
                x = i;
            }
        }

        return x + 1; //실제 번호는 1부터 시작하므로 증가
    }

    public static void main(String[] args)
    {
        int n = scanner.nextInt();
        int[] data = new int[n];
        for(int i = 0 ; i < n ; i++)
        {
            data[i] = scanner.nextInt();
        }

        int answer = findIndex(data, n);
        int index = answer - 1;

        System.out.printf("%d %d\n", answer, data[index]);
    }

}
