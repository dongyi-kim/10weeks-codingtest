import java.io.*;
import java.lang.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		int nNumbers = scanner.nextInt();   // 배열의 원소의 수
		int nCommands = scanner.nextInt();  // 처리할 명령어의 수

		// Shift 연산이 가능한 배열을 선언하고 원소를 차례로 입력한다
		ShiftingArray<Integer> array = new ShiftingArray<>(nNumbers);
		for(int i = 0 ; i < nNumbers ; i ++)
		{
			array.set(i, scanner.nextInt());
		}

		// 각 명령어를 처리한다
		for(int i = 0 ; i < nCommands; i+= 1)
		{
			// 명령어 정보를 입력받는다
			int cmd = scanner.nextInt();
			if( cmd == 0 )
			{   // 현재 배열에 p번 인덱스에 있는 원소를 출력하는 명령
				int p = scanner.nextInt();
				int value = array.get(p);
				System.out.println(value);
			}else if(cmd == 1)
			{   // 현재 배열을 왼쪽으로 k번 shift하는 명령
				int k = scanner.nextInt();
				array.shiftLeft(k);
			}else if(cmd == 2)
			{   // 현재 배열을 오른쪽으로 k번 shift하는 명령
				int k = scanner.nextInt();
				array.shiftRight(k);
			}else if(cmd == 3)
			{   // 현재 배열을 최초의 위치로 복원하는 명령
				array.initializePosition();
			}
		}
	}

	/**
	 * 정수 a의 나머지 m에 대한 곱셈의 역원을 반환하는 함수
	 * gcd(a, m) == 1 인 경우에만 사용해야 한다.
	 *
	 * @param a
	 * @param m
	 * @return
	 */
	public static long getInverse(long a, long m)
	{
		BigInteger bigA = new BigInteger(String.valueOf(a));    // 정수 a에 대한 BigInteger
		BigInteger bigM = new BigInteger(String.valueOf(m));    // 나머지 m에 대한 BigInteger
		BigInteger bigInv = bigA.modInverse(bigM);              // 곱셈의 역원 BigInteger
		return Long.parseLong( bigInv.toString() );             // 다시 long 타입으로 변환 후 반환
	}
}



class ShiftingArray <T> {

	private final T[] array;        // 내부에 데이터를 저장할 배열
	public final int length;       // 배열의 길이

	private int leftIndex;

	public ShiftingArray(int length)
	{
		this.length = length;
		this.array = (T[])(new Object[length]);
		this.leftIndex = 0;
	}

	/**
	 * 현재 배열에서 'index'번째에 존재하는 원소를 반환하는 함수
	 *
	 * @param index
	 * @return
	 */
	public T get(int index)
	{
		int realIndex = (index + leftIndex) % length;
		return array[realIndex];
	}

	/**
	 * 현재 배열에서 'index'번째에 존재하는 원소를 'value'로 변경하는 함수
	 *
	 * @param index
	 * @param value
	 */
	public void set(int index, T value)
	{
		int realIndex = (index + leftIndex) % length;
		array[realIndex] = value;
	}

	/**
	 * 현재 배열의 모든 원소를 왼쪽으로 'times'번 이동하는 함수
	 * @param times
	 */
	public void shiftLeft(int times){
		times = times % length;
		leftIndex = (leftIndex + times) % length;
	}

	/**
	 * 현재 배열의 모든 원소를 오른쪽으로 'times'번 이동하는 함수
	 *
	 * @param times
	 */
	public void shiftRight(int times){
		times = times % length;
		leftIndex = (leftIndex - times + length)%length;
	}

	/**
	 * 현재 배열의 모든 원소를 가장 초기 위치로 되돌리는 함수
	 */
	public void initializePosition()
	{
		leftIndex = 0;
	}

}