use std::io;

fn stdinln_i32() -> i32 {
	// 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
	let mut buffer = String::new();
	std::io::stdin().read_line(&mut buffer).expect("Failed to read stdin.");
	buffer.trim().parse::<i32>().unwrap()
}

fn stdinln_vec_i32() -> Vec<i32> {
	// 이 함수는 하나의 줄을 stdin으로 받아 여러개의 integer 을 vector로 리턴합니다.
	let mut buffer = String::new();
	std::io::stdin().read_line(&mut buffer).expect("Failed to read line");
	let ret: Vec<i32> = buffer.split(" ")
		.map(|x| x.trim().parse().expect("Unexpected Integer Pattern"))
		.collect();
	ret
}

fn main(){
	// 이 문제는 match 로 풀수 있을거라고 생각 할 수 있다.
	// 하지만 rust에서는 state safe를 위해서
	// match에서 변수를 match케이스로 사용할 수 없다!
	// 주의하자!
	let case_flag = stdinln_vec_i32();
	let person_data = stdinln_vec_i32();
	// let N = case_flag[0];
	let miju = case_flag[1];
	let suji = case_flag[2];
	let mut answer = 0;
	for person in person_data.into_iter() {
		if(person == miju){
			answer += 1;
		}else if(person == suji){
			answer += 1;
		}
	}
	println!("{}", answer);
}