import java.util.Scanner;

class D1 {

    public static int getSum(int[] data)
    {
        int sum = 0;

        for(int i = 0 ; i < data.length ; i++)
        {
            sum += data[i];

        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] data = new int[n];
        for(int i = 0 ; i < n ; i ++)
        {
            data[i] = scanner.nextInt();
        }
        int answer = getSum(data);
        System.out.println(answer);
    }
}
