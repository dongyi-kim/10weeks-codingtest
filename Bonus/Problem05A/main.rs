use std::io;
use std::cmp;

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

fn solve() -> usize {
	let mut space = Vec::new();
	let case_info = stdinln_vec_i32();
	let full_width = case_info[0] as usize;
	let toy_width = case_info[1] as usize;
	for i in 0..full_width {
		space.push(stdinln_vec_i32());
	}
	
	let mut smallest: usize = toy_width * toy_width;
	for seek_y in 0..=full_width - toy_width {	// 5-3=2 0,1
		for seek_x in 0..=full_width - toy_width{
			let mut count: usize = 0;
			for y in seek_y..seek_y + toy_width {
				for x in seek_x..seek_x + toy_width {
					if space[y][x] != 0 {
						count += 1;
					}
				}
			}
			smallest = cmp::min(smallest, count);
		}
	}
	smallest
}


fn main() {
	let nr_case = stdinln_i32();
	let mut answer_vec :Vec<usize> = Vec::new();
	for i in 0..nr_case {
		answer_vec.push(solve());
	}
	for ans in answer_vec.iter() {
		println!("{}", ans);
	}
}