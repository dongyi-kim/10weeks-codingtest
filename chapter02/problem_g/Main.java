import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void bubbleSort(int[] data, int n)
    {
        for(int i = 0 ; i < n ; i++)
        {
            int negativeCount = 0;
            for(int j = 0 ; j < n - 1-  i; j ++)
            {
                //오름차순을 부정하는 쌍이 등장하며면
                if( data[j] > data[j+1] )
                {   //두 쌍의 자리를 변경한다
                    int temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;

                    //그리고 그런 쌍의 수를 기록한다
                    negativeCount += 1;
                }
            }

            //이 값이 0이라는 건? 이미 모두 정렬이 되었다는 것
            if(negativeCount == 0)
            {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int n = scanner.nextInt();
        int[] data = new int[n];
        for(int i = 0 ; i < n ; i++)
        {
            data[i] = scanner.nextInt();
        }

        bubbleSort(data, n);

        for(int i = 0 ; i < n ; i++)
        {
            if( i > 0 )
            {
                System.out.print(" ");
            }
            System.out.print(data[i]);
        }
    }

}
