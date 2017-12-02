import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //데이터를 입력 받는다
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();

        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        int count = 0;

        for (int aData : data) {
            if (aData == m || aData == s) {
                count++;
            }
        }

        System.out.println(count);
    }
}
