import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * 배열에서 특정 원소의 위치를 찾는 함수
     * @param data 중복 없는 정수 배열 data[0] ~ data[n-1]
     * @param n    배열의 크기 n
     * @param m    배열에서 찾고자 하는 원소
     * @return     원소가 존재한다면 인덱스를, 존재하지 않으면 -1을 반환한다.
     */
    public static int findIndex(int[] data, int n, int m)
    {
        int index = -1;

        for(int i = 0 ; i < n ; i ++)
        {
            if(data[i] == m)
            {
                index = i;
            }
        }

        return index;
    }

    public static void main(String[] args)
    {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] data = new int[n];
        for(int i = 0 ; i < n ; i  ++)
        {
            data[i] = scanner.nextInt();
        }

        int answer = findIndex(data, n, m);

        System.out.println(answer);
    }
}
