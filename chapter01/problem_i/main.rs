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

/*
	이 문제도 저번문제와 같이 Method 스타일로 풀어봅시다.!
	참고하면 좋은 문서
	https://doc.rust-lang.org/rust-by-example/fn/methods.html
	https://rinthel.github.io/rust-lang-book-ko/ch04-02-references-and-borrowing.html
*/
pub struct I32Sort {
	len : usize,
	data: Vec<i32>,
}

impl I32Sort {
	fn new(length: usize) -> I32Sort {
		I32Sort{len:length, data:Vec::new()}
	}
	
	fn get_from_stdinln(&mut self) {
		self.data = stdinln_vec_i32();
		// 사전에 입력받은 길이 값과 실제 벡터 값이 다르면 ASSERT!한다.
		assert_eq!(self.data.len(), self.len);
	}

	fn sel_sort(&mut self){
		// 여기서 선택정렬을 구현해주세요!
		for i in 0..self.len {
			let mut small = i;
			for j in i+1..self.len {
				if self.data[j] <  self.data[small] {
					small = j;
				}
			}
			let temp = self.data[small];
			self.data[small] = self.data[i];
			self.data[i] = temp;
		}
	}

	fn print(self) {
		for num in self.data {
			print!("{} ", num);
		}
		println!("");
	}
}

fn main(){
	let nr_element = stdinln_i32() as usize;
	let mut problem = I32Sort::new(nr_element);
	problem.get_from_stdinln();
	// I32Sort::sel_sort() 를 완성해주세요!
	problem.sel_sort();
	problem.print();
}