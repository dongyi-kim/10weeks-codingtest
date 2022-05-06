import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * @brief   (r행 c열)의 집으로부터 연결 된 '아직 방문하지 않은 집'의 총 수를 계산하는 함수
	 *          이 과정에서 방문하는 집은 모두 visited[r][c]를 true로 갱신한다
	 *
	 * @param r         현재 기준 칸의 행 번호
	 * @param c         현재 기준 칸의 열 번호
	 * @param N         지도의 크기
	 * @param isHouse   isHouse[r][c] := r행 c열의 칸에 집이 존재하는지 여부
	 * @param visited   visited[r][c] := r행 c열의 칸의 집이 이미 고려 되었는지 여부
	 * @return
	 */
	public static int getLinkedHousesNumber(int r, int c, int N, boolean[][] isHouse, boolean[][] visited) {
		if (r <= 0 || c <= 0 || r > N || c > N) {
			// 지도를 벗어나는 집은 존재하지 않으므로 0
			return 0;
		} else if (!isHouse[r][c] || visited[r][c]) {
			// 집이 없는 칸 이나 이미 단지에 속한 집은 고려하지 않음
			return 0;
		}

		// r행 c열에 존재하는 집의 수를 가산한다
		// 고려되었으므로 visited 값을 갱신한다
		int count = 1;
		visited[r][c] = true;

		// 상하좌우로 인접한 집으로부터 연결된 집의 수를 재귀적으로 계산하여 가산한다
		count += getLinkedHousesNumber(r + 1, c, N, isHouse, visited);
		count += getLinkedHousesNumber(r - 1, c, N, isHouse, visited);
		count += getLinkedHousesNumber(r, c + 1, N, isHouse, visited);
		count += getLinkedHousesNumber(r, c - 1, N, isHouse, visited);

		return count;
	}

	/**
	 * @brief   문제의 조건에 따라 지도에 존재하는 모든 단지의 크기들을 반환하는 함수
	 *
	 * @param isHouse   isHouse[r][c] := r행 c열에 집이 존재하는지 여부 (1행 1열 ~ N행 N열)
	 * @param N         지도의 크기
	 * @return          모든 단지들의 크기의 리스트, 오름차순으로 정렬해야 한다
	 */
	public static ArrayList<Integer> getAllGroupSizes(boolean[][] isHouse, int N) {
		boolean[][] visited = new boolean[N][N];

		// 각 단지들의 크기를 저장할 그룹
		ArrayList<Integer> groups = new ArrayList<>();

		for (int r = 1; r <= N; r += 1) {
			for (int c = 1; c <= N; c += 1) {
				// 모든 칸 (r행 c열)에 대하여

				// 집이 존재하지만 아직 방문되지 않은 칸이라면?
				if (isHouse[r][c] && !visited[r][c]) {
					// 연결된 모든 집의 수를 세어 그룹의 크기로 사용한다
					// 이 때 세어진 모든 집들은 visited값이 true로 갱신 된다.
					int groupSize = getLinkedHousesNumber(r, c, N, isHouse, visited);

					// 단지의 크기를 추가한다
					groups.add(groupSize);
				}
			}
		}


		// 정렬 후 반환한다
		Collections.sort(groups);

		return groups;
	}

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();
		boolean[][] isHouse = new boolean[N + 2][N + 2];

		// 2차원 격자의 경우 가장자리에 한 칸을 두고 고려하면 처리가 편해진다
		for (int r = 1; r <= N; r += 1) {
			Arrays.fill(isHouse[r], false);
			for (int c = 1; c <= N; c += 1) {
				int value = scanner.nextInt();
				if (value == 1) {
					isHouse[r][c] = true;
				}
			}
		}

		ArrayList<Integer> groupSizes = getAllGroupSizes(isHouse, N);

		System.out.println(groupSizes.size());
		for (int i = 0; i < groupSizes.size(); i += 1) {
			int size = groupSizes.get(i);
			System.out.println(size);
		}
	}

}

