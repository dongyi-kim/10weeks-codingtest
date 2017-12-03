import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int sum = 0;
        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int q = scanner.nextInt();

        int[] weight = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
            if (weight[i] > p) {
                count += 1;
                sum += weight[i];
            }
        }
        System.out.println(count + " " + sum);
    }
}
