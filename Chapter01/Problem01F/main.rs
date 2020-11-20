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

fn find_vec_i32(source: Vec<i32>, nr_items: usize, target: i32) -> Result<usize, i32>{
	// Vec<i32>에서 우리가 원하는 target을 찾아서 Result<T,E>로 Return 하도록 해봅시다!
	// https://doc.rust-lang.org/stable/rust-by-example/error/result/early_returns.html
	// https://doc.rust-lang.org/stable/std/result/enum.Result.html
	// 좀더 러스트하게 짜보기위해서 찾은경우와 못찾은 경우 -1를 return해줄 것이 아니라
	// Result으로 묶어서 보내는 것이 적합하다.
	let mut i: usize = 0;
	let mut catch: bool = false;
	loop {
		if source[i] == target {
			catch = true;
			break;
		}

		i += 1;
		if i>=nr_items {
			break;
		}

	}
	// 찾지 못한경우 Err(-1)으로 Return 해줍니다. (Result<T,E>참고)
	if catch == true {
		Ok(i)
	}else{
		Err(-1)
	}
}

fn main(){
	let case_spec = stdinln_vec_i32();
	let inputs = stdinln_vec_i32();
	let nr_case = case_spec[0] as usize;
	let look_for = case_spec[1];
	
	// find_vec_i32 를 완성해주세요!
	let result = find_vec_i32(inputs, nr_case, look_for);
	match result {
		Ok(found)			=> println!("{}", found),
		Err(err_code)	=> println!("{}", err_code),
	}
	
}