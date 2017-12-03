import java.io.*;
import java.lang.*;
import java.util.*;

public class Main
{
    public static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        int n = scanner.nextInt();
        
        int total = 0;
        
        for (int j = 1; j <= n; j++)
        {
            int sum = 0 ;
            for (int i = 1; i <= j; i++)
            {
                sum += i;
            }
            total += sum;
        }
        
        System.out.println(total);
    }
}
