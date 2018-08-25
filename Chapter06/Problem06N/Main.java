import java.io.*;
import java.lang.*;
import java.util.*;

class MedianQueue<T extends Comparable<T>> {
	private PriorityQueue<T> minHeap;
	private PriorityQueue<T> maxHeap;
	
	// 생성자
	public MedianQueue() {
		this.minHeap = new PriorityQueue<>();
		this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	}
	
	public int size() {
		return minHeap.size() + maxHeap.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * 새 원소를 큐에 추가한다.
	 *
	 * @param value
	 */
	public void push(T value) {
		if (maxHeap.isEmpty() == false && value.compareTo(maxHeap.peek()) < 0) {
			// 현재 maxHeap의 최대값보다 작다면, maxHeap에 추가한다.
			maxHeap.add(value);
		} else {
			// 그렇지 않다면 minHeap에 추가한다.
			minHeap.add(value);
		}
		// 두 힙의 균형을 맞춘다.
		balancing();
	}
	
	/**
	 * 현재 중앙값인 원소를 반환한다.
	 *
	 * @return 중앙값 원소
	 * @throws NoSuchElementException 큐가 비어있는 경우
	 */
	public T peek() throws NoSuchElementException {
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		return maxHeap.peek();
	}
	
	/**
	 * 현재 중앙값인 원소를 pop하고 반환한다.
	 *
	 * @return 중앙값 원소
	 * @throws NoSuchElementException 큐가 비어있는 경우
	 */
	public T poll() throws NoSuchElementException {
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		
		T target = maxHeap.poll();
		balancing();
		return target;
	}
	
	/**
	 * 두 힙의 사이즈를 균형을 맞춘다.
	 * 항상 maxHeap의 사이즈가 같거나 하나 더 큰 상태로 만든다.
	 */
	private void balancing() {
		while (minHeap.size() > maxHeap.size()) {
			T value = minHeap.poll();
			maxHeap.add(value);
		}
		
		while (minHeap.size() < maxHeap.size() - 1) {
			T value = maxHeap.poll();
			minHeap.add(value);
		}
	}
}

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();
		
		MedianQueue<Integer> medianQueue = new MedianQueue<Integer>();
		
		for (int i = 0; i < N; i += 1) {
			String cmd = scanner.next();
			try {
				if (cmd.equals("I")) {
					int value = scanner.nextInt();
					medianQueue.push(value);
					
					int median = medianQueue.peek();
					writer.write(String.format("%d\n", median));
				} else if (cmd.equals("P")) {
					medianQueue.poll();
					
					int median = medianQueue.peek();
					writer.write(String.format("%d\n", median));
				} else if (cmd.equals("S")) {
					int size = medianQueue.size();
					writer.write(String.format("%d\n", size));
				}
			} catch (NoSuchElementException ex) {
				writer.write("ERROR\n");
			}
		}
		
		writer.close();
	}
	
}
