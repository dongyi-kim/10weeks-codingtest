import java.io.*;
import java.util.*;
import java.lang.*;

public class DynamicArrayTest {
	
	private final int TEST_SIZE;
	
	public DynamicArrayTest(int TEST_SIZE) {
		this.TEST_SIZE = TEST_SIZE;
	}
	
	/**
	 * 임의의 랜덤한 정수 배열을 생성하는 메소드
	 *
	 * @param size 배열의 크기
	 * @return 수행 시간 (초)
	 */
	public static Integer[] getRandomArray(int size) {
		Integer[] arr = new Integer[size];
		Random random = new Random();
		for (int i = 0; i < size; i += 1) {
			arr[i] = random.nextInt();
		}
		return arr;
	}
	
	/**
	 * 연속된 삽입 연산의 성능을 측성하는 테스트 메소드
	 *
	 * @return 수행 시간 (초)
	 */
	public double testInsertion() {
		Integer[] arr = getRandomArray(TEST_SIZE);
		
		double begin = System.currentTimeMillis();
		
		DynamicArray<Integer> dynamicArray = new DynamicArray<>();
		
		for (int i = 0; i < arr.length; i += 1) {
			dynamicArray.add(arr[i]);
			
			assert dynamicArray.size() == (i + 1);
			assert (int) dynamicArray.get(i) == (int) arr[i];
		}
		
		double end = System.currentTimeMillis();
		return (end - begin) / 1000.;
	}
	
	/**
	 * 뒤에서부터 순차적으로 진행되는 삭제 연산의 성능을 측정하는 테스트 메소드
	 *
	 * @return 수행 시간 (초)
	 */
	public double testBackwardRemoval() {
		Integer[] arr = getRandomArray(TEST_SIZE);
		
		double begin = System.currentTimeMillis();
		
		DynamicArray<Integer> dynamicArray = new DynamicArray<Integer>(arr);
		
		for (int lastIndex = arr.length - 1; lastIndex >= 0; lastIndex -= 1) {
			
			assert (int) dynamicArray.get(lastIndex) == (int) arr[lastIndex];
			
			dynamicArray.remove(lastIndex);
			
			assert dynamicArray.size() == (lastIndex);
		}
		
		double end = System.currentTimeMillis();
		return (end - begin) / 1000.;
	}
	
	/**
	 * 임의의 순서로 진행되는 삭제 연산의 성능을 측정하는 테스트 메소드
	 *
	 * @return 수행 시간(초)
	 */
	public double testRandomRemoval() {
		Integer[] arr = getRandomArray(TEST_SIZE);
		Random random = new Random(0);
		
		double begin = System.currentTimeMillis();
		
		DynamicArray<Integer> dynamicArray = new DynamicArray<Integer>(arr);
		
		for (int expectedSize = arr.length; expectedSize >= 1; expectedSize -= 1) {
			int targetIndex = Math.abs(random.nextInt()) % expectedSize;
			
			dynamicArray.remove(targetIndex);
			
			assert dynamicArray.size() == (expectedSize - 1);
		}
		
		double end = System.currentTimeMillis();
		return (end - begin) / 1000.;
	}
	
	public void runTest() {
		System.out.printf("Test Size : %d\n", this.TEST_SIZE);
		System.out.printf("  [1] Time cost for Insertion        : %.5f sec\n", testInsertion());
		System.out.printf("  [2] Time cost for Backward Removal : %.5f sec\n", testBackwardRemoval());
		System.out.printf("  [3] Time cost for Random Removal   : %.5f sec\n", testRandomRemoval());
	}
	
	public static void main(String[] args) {
		for (int N = 1; N <= 1_000_000_000; N *= 10) {
			DynamicArrayTest test = new DynamicArrayTest(N);
			test.runTest();
		}
	}
}
