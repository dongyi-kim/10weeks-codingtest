package Chapter11.Problem11E;

import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int[] populations = new int[N + 1];

		for (int i = 1; i <= N; i += 1) {
			populations[i] = scanner.nextInt();
		}

		int blockSize = (int) Math.sqrt(N + 1);
		Range[] blocks = splitIntoBlocks(N+1, populations, blockSize);

		for (int i = 0; i < M; i += 1) {
			String command = scanner.next();
			int op1 = scanner.nextInt();
			int op2 = scanner.nextInt();

			if (command.equals("UPDATE")) {
				updatePopulation(blocks, op1, op2);
			} else if (command.equals("GET_MAX")) {
				int maxValue = getMaximumPopulation(blocks, op1, op2);
				System.out.println(maxValue);
			} else if (command.equals("GET_SUM")) {
				int sumValue = getSumOfPopulations(blocks, op1, op2);
				System.out.println(sumValue);
			}
		}
	}

	/**
	 * N개의 배열을 여러개의 부분 배열로 쪼갠 후 반환해주는 함수
	 *
	 * @param N         배열의 전체 데이터의 수
	 * @param data      배열의 데이터
	 * @param blockSize 각 부분 범위의 길이
	 * @return 배열 전체를 blockSize단위로 나눈 범위들의 배열
	 */
	public static Range[] splitIntoBlocks(int N, int[] data, int blockSize) {
		int length = 1 + N / blockSize;
		Range[] blocks = new Range[length];

		for (int i = 0; i < blocks.length; i += 1) {
			blocks[i] = new Range(blockSize);
		}

		for (int i = 0; i < N; i += 1) {
			int value = data[i];
			int blockIndex = i / blockSize;
			int unitIndex = i % blockSize;
			blocks[blockIndex].update(unitIndex, value);
		}

		return blocks;
	}

	/**
	 * 하나의 가구에 대한 인구 정보를 갱신해주는 함수
	 *
	 * @param blocks   데이터 블럭들의 배열
	 * @param index    갱신할 가구의 인덱스
	 * @param newValue 갱신할 가구의 구성원 수
	 */
	public static void updatePopulation(Range[] blocks, int index, int newValue) {
		int blockSize = blocks[0].size;

		int blockIndex = index / blockSize;
		int unitIndex = index % blockSize;

		blocks[blockIndex].update(unitIndex, newValue);
	}

	/**
	 * 주어진 범위의 모든 가구에 대한 최대 구성원 수를 계산하는 함수
	 *
	 * @param blocks   데이터 블럭들의 배열
	 * @param leftEnd  가장 왼쪽 끝 가구의 번호
	 * @param rightEnd 가장 오른쪽 끝 가구의 번호
	 * @return 해당 범위에서 가장 큰 구성원의 값
	 */
	public static int getMaximumPopulation(Range[] blocks, int leftEnd, int rightEnd) {
		int blockSize = blocks[0].size;

		int leftBlockIndex = leftEnd / blockSize;
		int rightBlockIndex = rightEnd / blockSize;

		if (leftBlockIndex == rightBlockIndex) {
			int leftIndex = leftEnd % blockSize;
			int rightIndex = rightEnd % blockSize;
			return blocks[leftBlockIndex].getMax(leftIndex, rightIndex);
		}

		int maxValue = Math.max(
				blocks[leftBlockIndex].getMax(leftEnd % blockSize, blockSize - 1),
				blocks[rightBlockIndex].getMax(0, rightEnd % blockSize)
		);

		for (int blockIndex = leftBlockIndex + 1; blockIndex <= rightBlockIndex - 1; blockIndex += 1) {
			maxValue = Math.max(
					maxValue,
					blocks[blockIndex].getMax(0, blockSize - 1)
			);
		}

		return maxValue;
	}

	/**
	 * 주어진 범위의 모든 가구에 대한 구성원 수의 합을 계산하는 함수
	 *
	 * @param blocks   데이터 블럭들의 배열
	 * @param leftEnd  가장 왼쪽 끝 가구의 번호
	 * @param rightEnd 가장 오른쪽 끝 가구의 번호
	 * @return 해당 범위에 존재하는 구성원 수들의 합
	 */
	public static int getSumOfPopulations(Range[] blocks, int leftEnd, int rightEnd) {
		int blockSize = blocks[0].size;

		int leftBlockIndex = leftEnd / blockSize;
		int rightBlockIndex = rightEnd / blockSize;

		if (leftBlockIndex == rightBlockIndex) {
			int leftIndex = leftEnd % blockSize;
			int rightIndex = rightEnd % blockSize;
			return blocks[leftBlockIndex].getSum(leftIndex, rightIndex);
		}

		int sumValue = blocks[leftBlockIndex].getMax(leftEnd % blockSize, blockSize - 1)
				+ blocks[rightBlockIndex].getMax(0, rightEnd % blockSize);


		for (int blockIndex = leftBlockIndex + 1; blockIndex <= rightBlockIndex - 1; blockIndex += 1) {
			sumValue += blocks[blockIndex].getMax(0, blockSize - 1);
		}

		return sumValue;
	}
}

class Range {
	public final int size;		// 해당 범위에 속한 가구들의 수
	private int[] population;	// 각 가구들의 구성원 수

	private int globalSum;		// 전체 범위에 대한 합
	private int globalMax;		// 전체 범위에 대한 최대값

	public Range(int size) {
		this.size = size;
		this.population = new int[size];
		this.globalMax = 0;
		this.globalSum = 0;
	}

	// 한 데이터를 수정하는 메소드
	public void update(int index, int newValue) {
		int oldValue = this.population[index];
		this.population[index] = newValue;

		// 변한 데이터에 따라 전체의 합을 갱신해준다.
		this.globalSum += newValue - oldValue;

		// 변한 데이터에 따라 전체의; 최대값을 갱신해준다
		if (newValue > this.globalMax) {
			// 새로 들어온 데이터가 새로운 최대값이 되는 경우에는 대체한다
			this.globalMax = newValue;
		} else if (this.globalMax == oldValue) {
			// 제거된 값이 기존의 최대값이었던 경우 전체를 조회하여 다시 계산한다
			this.globalMax = population[0];
			for (int i = 0; i < this.size - 1; i += 1) {
				this.globalMax = Math.max(this.globalMax, population[i]);
			}
		}
	}

	// 한 데이터의 값을 가져오는 메소드
	public int select(int index) {
		return this.population[index];
	}

	// 주어진 범위의 최대값을 계산하는 메소드
	public int getMax(int leftEnd, int rightEnd) {
		// 전체 범위에 대해 조회하는 경우 globalMax를 반환한다
		if (leftEnd == 0 && rightEnd == this.size - 1) {
			return this.globalMax;
		}

		int maxValue = this.population[leftEnd];
		for (int i = leftEnd; i <= rightEnd; i += 1) {
			maxValue = Math.max(maxValue, this.population[i]);
		}
		return maxValue;
	}


	// 주어진 범위의 누적합을 계산하는 함수
	public int getSum(int leftEnd, int rightEnd) {
		// 전체 범위에 대해 조회하는 경우 globalSum를 반환한다
		if (leftEnd == 0 && rightEnd == this.size - 1) {
			return this.globalSum;
		}

		int sumValue = 0;
		for (int i = leftEnd; i <= rightEnd; i += 1) {
			sumValue += this.population[i];
		}
		return sumValue;
	}

}