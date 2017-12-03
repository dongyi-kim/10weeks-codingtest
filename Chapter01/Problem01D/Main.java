import java.util.*;
import java.lang.*;
import java.io.*;


class Main
{
    public static final Scanner scanner = new Scanner(System.in);

    public static int getSum(int[] data)
    {
        int sum = 0;

        for(int i = 0 ; i < data.length ; i++)
        {
            sum += data[i];
        }
        return sum;
    }
    
    public static void main(String[] args)
    {
        int n = scanner.nextInt();
        int[] data = new int[n];
        
        for(int i = 0 ; i < n ; i ++)
        {
            data[i] = scanner.nextInt();
        }
        
        int answer = getSum(data);
        System.out.println(answer);
    }
}
