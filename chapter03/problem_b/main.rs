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

#[derive(Copy, Clone)]
struct ColorSpec {
	left : usize,
	right: usize,
	color_num: u8, // 색깔의 번호는 0~99 이므로 u8 사용
}

impl ColorSpec {
	fn new(_l:usize, _r:usize, c_num:u8) -> ColorSpec {
		ColorSpec{left: _l, right: _r, color_num:c_num}
	}

	fn new_from_vec_i32(vector: Vec<i32>) -> ColorSpec {
		// best way is Try_into or Try_from, for now rustc version 1.18 (1.37<=ver) cannot use.
		// so just use assert.
		assert_eq!(vector.len() , 3);
		ColorSpec::new(vector[0] as usize,
			vector[1] as usize,
			vector[2] as u8)
	}
}

struct Painter {
	nr_seat: usize,
	nr_receipt: usize,
	receipt: Vec<ColorSpec>,
	// 좌석의 수는 1000개 이하 ;1000
	// 색깔의 번호는 0~99 이므로 u8 사용
	seat_arr: [u8; 1000],
	// 문제에서 0~99이하 라고 했으나 데이터로 100이 들어오는 관계로 101로 수정.
	// 현재 테스트 케이스를 수정했으므로 101->100으로 재수정.
	c_table: [i16; 100]
}

impl Painter {
	fn new(len_seat: usize, num_color: usize, 
		input_receipt: Vec<ColorSpec>) -> Painter {
		Painter{nr_seat:len_seat, nr_receipt:num_color, 
			receipt:input_receipt, seat_arr:[0; 1000],
			c_table:[0; 100]}
	}

	fn fill(&mut self, job: &ColorSpec) {
		// .. =job.right is more better, but groom uses rustc 1.18
		let end = {
			if job.right > self.nr_seat {
				self.nr_seat
			}else{
				job.right
			}
		};
		for i in job.left .. end+1 {
			if i >= self.nr_seat{
				continue;
			}else {
				self.c_table[self.seat_arr[i] as usize] -= 1;
				self.seat_arr[i] = job.color_num;
				//assert!(prev, 0);
				self.c_table[job.color_num as usize] += 1;
			}
		}
	}

	fn solve(&mut self){
		self.c_table[0] = self.nr_seat as i16;
		for i in 0..self.nr_receipt {
			let temp = self.receipt[i];
			self.fill(&temp);
		}
		let mut min_idx = self.receipt[self.nr_receipt-1].color_num as usize;
		let mut max_idx = self.receipt[self.nr_receipt-1].color_num as usize;
		for i in 0..100 {
			if self.c_table[i] <= 0 {
				continue;
			}
			if self.c_table[min_idx] > self.c_table[i] {
				min_idx = i;
			}else if self.c_table[min_idx] == self.c_table[i]{
				if min_idx >= i {
					//println!("min dup {}", i);
					min_idx = i;
				}
			}
			
			if self.c_table[max_idx] < self.c_table[i] {
				max_idx = i;
			}else if self.c_table[max_idx] == self.c_table[i]{
				if max_idx >= i {
					//println!("max dup {}", i);
					max_idx = i;
				}
			}
		}
		println!("{}\n{}",max_idx, min_idx);
	}
}

fn main() {
	let spec = stdinln_vec_i32();
	let nr_seat = spec[0] as usize;
	let nr_receipt = spec[1] as usize;
	let mut receipt :Vec<ColorSpec> = Vec::new(); 
	for i in 0..nr_receipt {
		let mut temp = stdinln_vec_i32();
		// 좌석의 번호는 1번부터 시작하므로, 0 ~ (n-1)범위로 맞추기 위하여 1씩 빼준다
		temp[0] -= 1;
		temp[1] -= 1;
		receipt.push(
			ColorSpec::new_from_vec_i32(temp)
		);		
	}
	let mut problem = Painter::new(nr_seat, nr_receipt, receipt);
	problem.solve();
}