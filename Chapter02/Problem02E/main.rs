use std::io;

fn stdinln_i32() -> i32 {
	// 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
	let mut buffer = String::new();
	std::io::stdin().read_line(&mut buffer).expect("Failed to read stdin.");
	buffer.trim().parse::<i32>().unwrap()
}

fn is_prime(target: i32) -> bool {
	// is_prime을 완성시켜주세요. 소수인 경우 true, 아닌경우 false를 리턴해주세요.
	if target == 1 {
		false
	}
	else if target == 2 {
		true
	}else if target & 0x1 == 0 {
		false
	}else {
		let mut ret = true;
		let max_iter = ((target as f64).sqrt() + 1.0) as usize;
		let mut i = 3;
		loop {
			if !(i < max_iter){
				break;
			}
			if target%(i as i32) == 0 {
				ret = false;
				break;
			}

			i += 2;
		}
		ret
	}
}

fn main() {
	let nr_case = stdinln_i32() as usize;
	let mut testcase = Vec::new();
	for i in 0..nr_case {
		testcase.push(stdinln_i32());
	}

	for i in 0..nr_case {
		println!("Case #{}", i+1);
		// is_prime을 완성시켜주세요!.
		match is_prime(testcase[i]) {
			true  => println!("YES"),
			false => println!("NO"),
		}
	}
}