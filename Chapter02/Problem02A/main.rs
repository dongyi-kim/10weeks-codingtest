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

fn solve(nr_case: usize, heights: &Vec<i32>, births: &Vec<i32>, match_birth: i32) -> Option<i32> {
	let mut tallest :i32 = -1;
	for i in 0..nr_case{
		if births[i] == match_birth {
			if heights[i] > tallest {
				tallest = heights[i];
			}
		}
	}
	
	if tallest >= 0 {
		Some(tallest)
	}else {
		None
	}
}

fn main(){
	let nr_case = stdinln_i32() as usize;
	let heights = stdinln_vec_i32();
	let births = stdinln_vec_i32();
	let match_birth = stdinln_i32();
	let result = solve(nr_case, &heights, &births, match_birth);
	match result {
		Some(x)	=> println!("{}", x),
		None		=> println!("{}", -1),
	}
}