import java.io.*;
import java.util.*;
import java.lang.*;

public class DynamicArray<T> {
	public static final int INITIAL_CAPACITY = 16;
	
	private T[] array;      // 내부 저장 공간 배열
	private int capacity;   // 내부 저장 공간의 실제 크기를 나타내는 변수
	private int size;       // 실제로 저장된 원소의 수를 나타내는 변수
	
	public DynamicArray() {
		this.array = (T[]) (new Object[INITIAL_CAPACITY]);
		this.capacity = INITIAL_CAPACITY;
		this.size = 0;
	}
	
	/**
	 * @TODO    배열에 존재하는 원소들을 사용해 초기화하는 생성자 구현
	 *
	 * @param array
	 */
	public DynamicArray(T[] array) {

	}

	
	/**
	 * @TODO    가장 뒤에 새로운 원소를 추가하는 메소드 구현
	 *
	 * @param element
	 */
	public void add(T element) {

	}
	
	/**
	 * @TODO    특정 인덱스에 위차한 원소를 삭제하는 메소드 구현. 삭제 후 뒤의 원소들은 모두 앞으로 한 칸씩 당긴다.
	 *
	 * @param index
	 */
	public void remove(int index) {

	}
	
	/**
	 * @TODO    특정 인덱스에 위치한 원소를 반환하는 메소드 구현
	 *
	 * @param index
	 * @return
	 */
	public T get(int index) {
		return null;
	}
	
	/**
	 * @TODO    특정 인덱스에 위치한 원소를 수정하는 메소드 구현
	 *
	 * @param index
	 * @param value
	 */
	public void set(int index, T value) {

	}
	
	
	/**
	 * @TODO    현재 저장된 원소의 수를 반환하는 메소드 구현
	 *
	 * @return
	 */
	public int size() {
		return -1;
	}
	
}