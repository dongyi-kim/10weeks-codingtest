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

fn count_kinds(nr_case: usize, numbers: &Vec<i32>) -> u32 {
	// count_kinds 완성해주세요. 음수는 절대로 올 수 없기에 u32로 리턴해줍시다.
	// 종류 갯수가 만약 입력값이 비어있는 경우에는 1이 될수가 없다. 
	let mut ret: u32 = {
		if nr_case == 0 && numbers.len() == 0 {
			0
		}else {
			1
		}
	};
	
	for i in 1..nr_case {
		if numbers[i-1] != numbers[i] {
			ret += 1;
		}
	}
	ret
}

fn main(){
	let nr_case = stdinln_i32() as usize;
	let numbers = stdinln_vec_i32();
	// count_kinds를 완성해주세요!
	let result: u32= count_kinds(nr_case, &numbers);
	println!("{}", result)
}