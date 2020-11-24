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

fn solve(nr_cup :usize, contigious :usize, cups :Vec<i32>) -> bool {
	// solve를 완성시켜주세요.
	let mut ret :bool = false;
	let mut sum = 0;
	// 이문제는 아마 i32로는 안될겁니다.
	// 따라서 어차피 홀짝 체크이므로 0x1로 마스크로 하여 1이냐 0이냐만으로 홀짝체크합니다.
	for j in 0..contigious {
			sum += cups[j] & 0x1;
	}
	if sum & 0x1 != 0 {
		for i in 1..nr_cup-contigious+1 {
			sum -= cups[i-1] & 0x1;
			sum += cups[i+contigious-1] & 0x1;

			if sum & 0x1 == 0 {
				ret = true;
				break;
			}
		}
	}else {
		ret = true;
	}
	ret
}


fn main() {
	let spec = stdinln_vec_i32();
	let result = solve(spec[0] as usize, spec[1] as usize, stdinln_vec_i32());
	match result {
		true  => println!("YES"),
		false => println!("NO"),
	}
}