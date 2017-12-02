import java.util.Scanner;


public class Main {
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int sum = 0;
        int total = 0;
        for (int j = 1; j <= N; j++) {
            for (int i = 1; i <= j; i++) {
                sum += i;
            }
            total += sum;
            sum = 0;
        }
        System.out.println(total);
    }
}
