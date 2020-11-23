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

struct Bubble {
	data: Vec<i32>,
	nr_item: usize
}

impl Bubble {
	fn new_from_vec_i32(inputs: Vec<i32>, len: usize) -> Bubble {
		Bubble{data:inputs, nr_item: len}
	}

	fn sort(&mut self) {
		// Bubble::sort() 를 완성해주세요.
		for i in 0..self.nr_item {
			for j in 1..self.nr_item {
				if self.data[j-1] > self.data[j] {
					let temp = self.data[j-1];
					self.data[j-1] = self.data[j];
					self.data[j] = temp;
				}
			}
		}
		// end of Bubble::sort()
	}

	fn print(self) {
		for item in self.data {
			print!("{} ", item);
		}
		println!("");
	}
}

fn main() {
	let nr_item = stdinln_i32() as usize;
	let mut problem = Bubble::new_from_vec_i32(stdinln_vec_i32(), nr_item);
	// Bubble::sort() 를 완성해주세요.
	problem.sort();
	problem.print();
}