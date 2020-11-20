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

fn sum_vec_i32(source: Vec<i32>, nr_items: usize) -> i32 {
	let mut ret: i32 = 0;
	// Vec<i32>에서 sum을 구하는 함수를 만들어주십시오.
	for i in 0..nr_items {
		ret += source[i];
	}
	// ret에 저장한 합계를 return합니다.
	ret
}

fn main(){
	let nr_cases = stdinln_i32() as usize;
	let inputs = stdinln_vec_i32();
	// sum_vec_i32 를 완성해주세요!
	let result = sum_vec_i32(inputs, nr_cases);
	println!("{}", result);
}