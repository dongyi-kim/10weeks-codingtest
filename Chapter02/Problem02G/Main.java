import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int input[] = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = scanner.nextInt();
        }
        bubbleSorting(input);

        for (int i = 0; i < N; i++) {

            if (i != N)
                System.out.print(input[i] + " ");
            else
                System.out.print(input[i]);

        }
    }

    private static void bubbleSorting(int[] a) {
        int size = a.length;
        for (int i = 0; i < size; i++) {
            int cnt = 0;
            for (int j = 0; j < size-i-1; j++) {
                if(a[j] > a[j+1]){
                    swap(a, j,j+1);
                    cnt++;
                }
            }
            if(cnt == 0){
                break;
            }
        }
    }

    private static void swap(int a[], int fromIndex, int toIndex) {
        int temp = a[fromIndex];
        a[fromIndex] = a[toIndex];
        a[toIndex] = temp;
    }
}
