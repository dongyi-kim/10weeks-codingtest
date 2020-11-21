use std::io;

pub struct JiaeHomework {
	index: usize,
	value: i32,
}

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

/*
	이번 문제는 Method 스타일로 풀어봅시다.!
	https://doc.rust-lang.org/rust-by-example/fn/methods.html
	이번 문제는 Rust-Ownership 문제가 야기될 수 있습니다. 한번 읽어보세요.
	https://rinthel.github.io/rust-lang-book-ko/ch04-02-references-and-borrowing.html
*/
impl JiaeHomework {
	fn new() -> JiaeHomework {
		JiaeHomework{index:0, value:0}
	}
	
	fn solve(&mut self, source: &Vec<i32>, nr_items: usize) {
		// solve를 완성해주세요!
		let mut sum :i32 = 0;
		// get Sum of source vector.
		for i in 0..nr_items{
			sum += source[i];
		}
		let avg = sum/nr_items as i32;
		// Rust 2018에서는 (source[0]-avg).abs() 를 해도 되나.
		// 현재 구름은 rustc 2017년대 버젼을 쓰고있다. 따라서 이와같이 해결한다.
		// i32::abs(...)
		let mut near_gap = i32::abs(source[0]-avg);
		for i in 1..nr_items {
			let current_gap = i32::abs(source[i]-avg);
			if current_gap < near_gap {
				self.index = i;
				near_gap = current_gap;
			}
		}
		self.value = source[self.index];
		// End of solve
	}
	fn print(&self) {
		println!("{} {}", self.index + 1, self.value);
	}
}

fn main(){
	let nr_case = stdinln_i32() as usize;
	let inputs = stdinln_vec_i32();
	
	// JiaeHomework.solve(...) 를 완성해주세요!
	let mut problem = JiaeHomework::new();
	problem.solve(&inputs, nr_case);
	problem.print();
}