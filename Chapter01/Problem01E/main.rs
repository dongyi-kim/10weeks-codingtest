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

fn sum_vec_i32_customized(source: Vec<i32>, nr_items: usize, max_each: i32, max_total: i32) {
	// 문제에 맞게 함수를 완성해주세요.
	let mut total = 0;
	let mut nr_match = 0;
	// for person in target.iter_to() <<- 를 써도 좋다.
	for i in 0..nr_items {
		if(source[i] <= max_each){
			total += source[i];
			nr_match += 1;
		}
	}
	
	println!("{} {}", nr_match, total);
	
	let result: bool = total<=max_total;
	
	match result {
		true => println!("YES"),
		false => println!("NO"),
	}
}

fn main(){
	let case_spec = stdinln_vec_i32();
	let nr_person = case_spec[0] as usize;
	let max_each = case_spec[1];
	let max_total = case_spec[2];
	let case_data = stdinln_vec_i32();
	// sum_vec_i32_customized 를 완성해주세요!
	sum_vec_i32_customized(case_data, nr_person, max_each, max_total);
}