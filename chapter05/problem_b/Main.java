import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


	/**
	 * 파라미터로 주어지는 각 타워들에 대해 타겟 타워를 계산하는 함수
	 *
	 * @param n         타워의 수
	 * @param towers    왼쪽~오른쪽 순서로 저장된 타워 배열
	 */
	public static void findTargetTowers(int n, Tower[] towers){
		// 현재 다른 타워의 신호를 수신할 가능성이 있는 타워들
		Stack<Tower> touchableTowers = new Stack<>();

		for(int i = 0 ; i < n ; i ++) {
			Tower t = towers[i];    // 각 타워 t에 대해 차례로 고려
			Tower target = null;    // 타워 t의 신호를 수신할 후보 ( 초기값 null )

			while(touchableTowers.isEmpty() == false
					&& touchableTowers.peek().height < t.height){
				// t보다 높이가 낮은 타워들은 이후에도 수신 가능성이 없으므로 제거
				touchableTowers.pop();
			}

			// t 이상의 높이를 가진 타워가 남아있다면?
			if(touchableTowers.size() > 0){
				// t는 해당 타워를 타겟으로 정하게 된다.
				target = touchableTowers.peek();
			}

			// 계산한 타겟 정보를 저장한다.
			t.setTargetTower(target);

			// t는 마지막에 등장했으므로 다른 타워의 신호를 수신할 수 있다. 등록.
			touchableTowers.push(t);
		}
	}

	public static void main(String[] args) throws Exception {
		int n = scanner.nextInt();

		Tower[] towers = new Tower[n];
		for(int i = 0 ; i < n ; i++) {
			int hi = scanner.nextInt();
			towers[i] = new Tower(i + 1, hi );  // 인덱스 1부터 저장
		}

		// 각 타워가 송신하는 레이저에 대해 타겟을 모두 계산한다
		findTargetTowers(n, towers);

		for(int i = 0 ; i < n; i ++) {
			if(i > 0 ){
				writer.write(" ");
			}

			Tower t = towers[i];
			if(t.getTargetTower() == null){
				writer.write("0");
			}else{
				int targetIndex = t.getTargetTower().index;
				writer.write(String.valueOf(targetIndex));
			}
		}
		writer.flush();
		writer.close();
	}

}

class Tower{
	public final int index;     // 타워의 인덱스
	public final int height;    // 타워의 높이

	private Tower targetTower;  // 이 타워의 레이저를 수신하는 대상 타워

	public Tower(int index, int height){
		this.index = index;
		this.height = height;
		this.targetTower = null;
	}

	public void setTargetTower(Tower targetTower) {
		this.targetTower = targetTower;
	}

	public Tower getTargetTower() {
		return targetTower;
	}
}