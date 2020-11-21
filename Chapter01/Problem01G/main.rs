use std::io;

struct CandidateAjou {
	first: usize,
	last: usize,
}

fn stdinln_i32() -> i32 {
	// 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
	let mut buffer = String::new();
	std::io::stdin().read_line(&mut buffer).expect("Failed to read stdin.");
	buffer.trim().parse::<i32>().unwrap()
}

fn stdinln_vec_multiple_str(nr_lines: usize) -> Vec<String> {
	// 이 함수는 nr_lines의 라인 만큼 stdin으로 받아 nr_lines갯수의 String을 vector로 리턴합니다.
	let mut ret: Vec<String> = Vec::new();
	for _i in 0..nr_lines {
		ret.push(String::new());
		std::io::stdin().read_line(&mut ret[_i]).expect("Failed to read line");
		ret[_i] = ret[_i].trim().to_string();
	}
	ret
}

fn get_ajou(source: Vec<String>, nr_items: usize) -> CandidateAjou{
	let mut ret = CandidateAjou{first:0, last:0};
	let target= String::from("AJOU");
	for i in 0..nr_items {
		if source[i].eq(&target) {
			ret.first = i+1;
			break;
		}
	}
	for i in (0..nr_items).rev() {
		if source[i].eq(&target) {
			ret.last = i+1;
			break;
		}
	}
	ret
}

fn main(){
	let nr_case = stdinln_i32() as usize;
	let inputs = stdinln_vec_multiple_str(nr_case);
	let result = get_ajou(inputs, nr_case);
	println!("{} {}", result.first, result.last);

}