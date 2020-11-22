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


fn is_sorted(nr_case: usize, heights: &Vec<i32>) -> bool {
	// is_sorted를 완성해주세요. 오름차순이면 true, 아닌 경우 false를 리턴해주세요.
	let mut ret: bool = true;
	for i in 1..nr_case {
		if heights[i] < heights[i-1] {
			ret = false;
			break;
		}
	}
	ret
}

fn main(){
	let nr_case = stdinln_i32() as usize;
	let heights = stdinln_vec_i32();
	// is_sorted를 완성해주세요!
	let result: bool = is_sorted(nr_case, &heights);
	match result {
		true	=> println!("YES"),
		false	=> println!("NO"),
	}
}