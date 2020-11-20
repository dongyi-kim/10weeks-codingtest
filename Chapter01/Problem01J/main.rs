use std::io;

fn stdinln_i32() -> i32 {
	// 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
	let mut buffer = String::new();
	std::io::stdin().read_line(&mut buffer).expect("Failed to read stdin.");
	buffer.trim().parse::<i32>().unwrap()
}

pub struct SumAndSum {
	seed: i32,
	result: Option<i32>,
}

impl SumAndSum {
	fn new(_seed: i32) -> SumAndSum {
		SumAndSum{seed:_seed, result:None}
	}

	fn calc(&mut self){
		// 여기서 문제를 풀어주세요!
		let mut sum = 0;
		// 그룸IDE 구버젼 rustc 사용으로 인해 ..=self.seed 사용못함.
		for i in 1..self.seed + 1{
			for j in 1..i+1{
				sum += j;
			}
		}
		self.result = Some(sum);
	}

	fn print(self) {
		match self.result {
			Some(x) => println!("{}", x),
			None => println!("Assert!"),
		}
	}
}

fn main(){
	let seed = stdinln_i32();
	let mut problem = SumAndSum::new(seed);
	// SumAndSum::calc() 를 완성해주세요!
	problem.calc();
	problem.print();
}