import java.util.*;
import java.lang.*;
import java.io.*;


class Main
{
    public static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        int n = scanner.nextInt();
        int first_ajou = -1;
        int last_ajou = -1;
        
        for (int i = 1; i <= n; i++)
        {
            String name = scanner.next();
            if (name.equals("AJOU"))
            {
                if (first_ajou == -1)
                {
                    first_ajou = i;
                }
                last_ajou = i;
            }
        }
        
        System.out.printf("%d %d\n", first_ajou, last_ajou);
    }
}
