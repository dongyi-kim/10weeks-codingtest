import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * 배열의 N개의 원소가 연속적인 정수 수열로 표현될 수 있는지 판단하는 함수
     * @param data
     * @param n
     * @return
     */
    public static boolean isConsecutive(int[] data, int n)
    {
        int maximum = data[0];
        int minimum = data[0];

        for(int i = 0; i < n ; i++)
        {
            if(maximum < data[i])
            {
                maximum = data[i];
            }
            if(minimum > data[i])
            {
                minimum = data[i];
            }
        }

        if(maximum - minimum + 1 == n)
        {
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        int n = scanner.nextInt();
        int[] data = new int[n];
        for(int i = 0 ; i < n ; i ++)
        {
            data[i] = scanner.nextInt();
        }

        boolean result = isConsecutive(data, n);

        if(result)
        {
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

}
