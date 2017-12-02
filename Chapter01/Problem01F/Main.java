import java.util.*;

class Main {

    public static int findValue(int value, int[] data) {
        int index = -1;
        for (int i = 0; i < data.length; i++) {

            if (data[i] == value) {
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] data = new int[n + 1];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }
        int answer = findValue(m, data);
        System.out.println(answer);
    }
}
