import java.util.*;

class Main {
    public static void selectionSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int mini = i;
            for (int j = i; j < data.length; j++) {
                if (data[mini] > data[j]) {
                    mini = j;
                }
            }

            int temp = data[i];
            data[i] = data[mini];
            data[mini] = temp;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }
        selectionSort(data);
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(data[i]);
        }
    }
}
