import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * 배열에서 소속이 "AJOU"인 첫 원소와 마지막 원소를 출력하는 함수
     * @param school 각 사람들의 소속학교 정보 배열
     * @param n      사람들의 수
     */
    public static void printIndexes(String[] school, int n)
    {
        int first = -1; //존재하지 않으면 -1
        int last = -1;  //존재하지 않으면 -1

        for(int i = 0 ; i < n ; i++)
        {
            if( school[i].equals("AJOU") )
            {
                if(first == -1)
                {
                    first = i + 1;
                }

                last = i + 1;
            }
        }

        System.out.printf("%d %d\n", first, last );
    }

    public static void main(String[] args)
    {
        int n = scanner.nextInt();
        String[] schools = new String[n];
        for(int i = 0 ; i < n ; i++)
        {
            schools[i] = scanner.next();
        }

        printIndexes(schools, n);
    }

}
