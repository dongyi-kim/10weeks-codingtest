import java.util.*;
import java.lang.*
import java.io.*;

class  Main {
    public static final Scanner scanner = new Scanner(System.in);
    
    public static int getMax(int[] data) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            if (max < data[i])
                max = data[i];
        }
        return max;
    }

    public static void main(String[] args)
    {

        int n = scanner.nextInt();
        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        int answer = getMax(data);

        System.out.println(answer);
    }
}
