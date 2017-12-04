import java.util.Scanner;

public class J2_승부조작 {

    static int N;
    static int K;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();

        int[] input = new int[N + 1];

        for (int i = 0; i < N; i++) {
            input[i] = scanner.nextInt();
        }

        long sum = 0;
        boolean flag = false;
        for (int i = K; i <= N; i++) {
            for (int j = i; j > i - K; j--) {
                sum += input[j - 1];
            }
            if (sum % 2 == 0) {
                flag = true;
                break;
            }
            sum = 0;
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
