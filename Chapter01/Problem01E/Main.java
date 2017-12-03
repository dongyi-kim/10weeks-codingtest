import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        int count = 0;
        int sum = 0;

        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int q = scanner.nextInt();

        int[] weight = new int[n];
        for (int i = 0; i < n; i++)
        {
            weight[i] = scanner.nextInt();
        }

        for(int i = 0 ; i < n ; i ++)
        {
            if (weight[i] <= p)
            {
                count += 1;
                sum += weight[i];
            }
        }

        System.out.printf("%d %d\n", count, sum);

        if(sum <= q)
        {
            System.out.println("YES");
        }else
        {
            System.out.println("NO");
        }
    }
}
