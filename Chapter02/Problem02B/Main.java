import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = scanner.nextInt();
        }

        int MIN = input[0];
        for (int i = 0; i < N; i++) {
            if (input[i] >= MIN) {
                MIN = input[i];
            } else {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
