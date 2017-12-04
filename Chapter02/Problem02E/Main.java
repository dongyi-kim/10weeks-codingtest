import java.util.*;

class Main {

    public static Scanner scanner = new Scanner(System.in);

    private static boolean isPrime(int n) {


        if (n == 1) return false;
        int cnt = 0;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void testCase() {
        int n = scanner.nextInt();
        if (isPrime(n)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        int tc = scanner.nextInt();
        for (int i = 1; i <= tc; i++) {
            System.out.printf("Case #%d\n", i);
            testCase();
        }
    }
}
