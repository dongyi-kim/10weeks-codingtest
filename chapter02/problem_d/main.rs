use std::io;

enum RetStrcmp {
	left_upper,
	right_upper,
	equal,
}

fn custom_strcmp(left: &[u8], right: &[u8]) -> RetStrcmp {
	// custom_strcmp 완성해주세요. "RetStrcmp" enum에 맞게 풀어봅시다.
	// 구름IDE의 rustc 가 구버젼이라 안됨. let max_iter = left.len().min(right.len());
	let max_iter = {
		if left.len() > right.len() {
			right.len()
		}else {
			left.len()
		}
	};
	// end of alternative code for usize::min(self, other)
	
	let mut i = 0;
	loop {
		if !(i < max_iter - 1) {
			break;
		}

		if left[i] != right[i] {
			break;
		}else{
			i += 1;
		}
	}

	if left[i] > right[i] {
		RetStrcmp::right_upper
	} else if left[i] < right[i] {
		RetStrcmp::left_upper
	}else {
		if left.len() > right.len() {
			RetStrcmp::right_upper
		}else if left.len() < right.len() {
			RetStrcmp::left_upper
		}else {
			RetStrcmp::equal
		}
	}

}

fn main(){
	let mut str_left  = String::new();
	let mut str_right = String::new();
	std::io::stdin().read_line(&mut str_left).expect("Failed to read line");
	std::io::stdin().read_line(&mut str_right).expect("Failed to read line");
	let bleft = str_left.trim().as_bytes();
	let bright = str_right.trim().as_bytes();
	// custom_strcmp를 완성해주세요!
	// 이번에는 match + enu조합으로 되어있습니다.
	let result : i32 = match custom_strcmp(&bleft, &bright) {
		RetStrcmp::left_upper  => -1,
		RetStrcmp::right_upper => 1,
		RetStrcmp::equal       => 0,
	};
	println!("{}", result)
}