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

fn max_vec_i32(source: Vec<i32>) -> i32 {
	// Vec<i32>에서 Maximum을 구하는 함수 예시.
	let max = source.iter().max().unwrap();
	*(max)
}

fn main(){
	let nr_case = stdinln_i32();
	let biggest = max_vec_i32(stdinln_vec_i32());
	println!("{}", biggest);
}