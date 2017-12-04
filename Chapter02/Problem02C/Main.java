import java.util.Scanner;

public class Main {
    static int N;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        int[] data = new int[N + 1];
        for (int i = 0; i < N; i++) {
            data[i] = scanner.nextInt();
        }

        int count = 1;
        for (int i = 1; i < N; i++) {
            if (data[i] == data[i - 1]) {
                count++;
            }
        }
        System.out.println(count);
    }
}
