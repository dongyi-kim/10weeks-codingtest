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

struct Netflix {
	watched : Vec<i32>,
	nr_watched : usize,
}

impl Netflix {
	fn new_from_vec_i32(inputs: Vec<i32>, len: usize) -> Netflix{
		Netflix{watched: inputs, nr_watched: len}
	}
	
	fn is_contiguous(self) -> bool {
		// Netflix::is_contiguous() 를 완성해주세요!
		let mut minimum :i32 = 10000000;
		let mut maximum :i32 = 0;
		for i in 0..self.nr_watched {
			if minimum > self.watched[i] {
				minimum = self.watched[i];
			}
			if maximum < self.watched[i] {
				maximum = self.watched[i];
			}
		}
		
		let stretched = maximum - minimum + 1;
		if stretched as usize == self.nr_watched {
			true
		}else {
			false
		}
		// End of Netflix::is_contiguous()
	}
}

fn main() {
	let nr_scene = stdinln_i32() as usize;
	let mut maum_ui_sori = Netflix::new_from_vec_i32(stdinln_vec_i32(), nr_scene);
	// Netflix::is_contiguous()를 완성시켜주세요!.
	match maum_ui_sori.is_contiguous() {
		true  => println!("YES"),
		false => println!("NO"),
	}
}