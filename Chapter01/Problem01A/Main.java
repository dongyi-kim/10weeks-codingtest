import java.io.*;
import java.util.*;


public class Main{
    public static final Scanner scanner = new Scanner(System.in);

    //a와 b중 더 큰 수를 반환하는 함수
    public static int getMax(int a, int b)
    {
        if( a >= b ){
            return a;
        }else{
            return b;
        }
    }
    
    public static void main(String[] args)
    {
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        int answer = getMax(p, q);
        System.out.printf("%d\n", answer);
    }

}