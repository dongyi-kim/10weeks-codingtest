import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param data 각 사람들의 키를 저장한 배열
     * @param n     사람들의 수
     * @param m     미주의 키
     * @param s     지수의 키
     * @return      미주 혹은 지수와  키가 일치하는 사람의 수
     */
    public static int getCount(int[] data, int n, int m, int s)
    {
        int count = 0 ; //확인해보아야 할 후보의 수
        for(int i = 0 ; i < n ; i++)
        {
            if(data[i] == m || data[i] == s)
            {
                count += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        int answer = getCount(data, n, m, s);

        System.out.println(answer);
    }

}
