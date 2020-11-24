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

struct TelBox {
	data: Vec<i32>,
	nr_tel: usize,
	cntbox: [u32; 10000],	// 컴파일 시간내에 사이즈가 결정되는 어레이, Vector보다 빠르다
}

impl TelBox {
	fn new_from_vec_i32(inputs: Vec<i32>, len: usize) -> TelBox {
		TelBox{data:inputs, nr_tel:len, cntbox:[0; 10000]}
	}

	fn get_most(&mut self) -> u32 {
		// TelBox::get_most() 를 완성해주세요!
		for i in 0..self.nr_tel {
			self.cntbox[self.data[i] as usize] += 1;
		}
		let mut biggest = 0;
		for i in 1..10000 {
			if self.cntbox[i] > self.cntbox[biggest] {
				biggest = i;
			}else if self.cntbox[i] == self.cntbox[biggest] {
				if i < biggest {
					biggest = i;
				}
			}
		}
		biggest as u32
		// End of TelBox::get_most()
	}
}

// Rust에서는 array사용시 컴파일시간내에서만 결정되어야한다.
// 이 문제같은 경우 0~10000이라는 조건이 컴파일 시간이전에 결정된다.
// 따라서 이 문제는 Vector가 아닌 Array를 사용한다.
// 힌트 : https://doc.rust-lang.org/std/primitive.array.html

fn main() {
	let nr_tel = stdinln_i32() as usize;
	let mut tels: Vec<i32> = Vec::new();
	for i in 0..nr_tel {
		tels.push(stdinln_i32());
	}
	let mut problem = TelBox::new_from_vec_i32(tels, nr_tel);
	// TelBox::get_most()를 완성해주세요!
	println!("{:04}", problem.get_most());
}