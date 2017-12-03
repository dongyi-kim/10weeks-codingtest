import java.util.Scanner;

class Main {

    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        int n = scanner.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += data[i];
        }

        int closest  = 0;
        for (int i = 0; i < n; i++)
        {
            int md = Math.abs(data[closest] * n - sum);
            int nd = Math.abs(data[i] * n - sum );
            if( md > nd)
            {
                closest = i;
            }
        }
        
        System.out.printf("%d %d", closest + 1, data[closest]);
    }

}
