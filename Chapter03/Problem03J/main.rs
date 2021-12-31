use std::io;
use std::io::prelude::*;

// Vec<u8> -> i32
fn i32_from_vec_u8(vector: &Vec<u8>) -> i32 {
	let mut ret: i32 = 0;
	for i in 0..vector.len() {
		if b'0' <= vector[i] && vector[i] <= b'9' {
			ret = ret * 10 + (vector[i] - b'0') as i32;
		} else {
			break;
		}
	}
	ret
}

fn heavy_stdinln_vec_i32(len: usize) -> Vec<i32> {
	// 이 함수는 하나의 줄을 stdin으로 받아 여러개의 integer 을 vector로 리턴합니다.
	// 일부 코딩테스트 케이스입력에서 너무 긴 테스트케이스를 입력받기위해 사용.
	let mut ret: Vec<i32> = Vec::with_capacity(len);
	for _i in 0..len - 1 {
		let mut __stdin = io::stdin();
		let mut __stdinlck = __stdin.lock();
		let mut slice: Vec<u8> = Vec::new();
		__stdinlck.read_until(b' ', &mut slice).expect("Error");
		ret.push(i32_from_vec_u8(&slice));
	}
	let mut __stdin = io::stdin();
	let mut __stdinlck = __stdin.lock();
	let mut slice: Vec<u8> = Vec::new();
	__stdinlck.read_until(b'\n', &mut slice).expect("Error");
	ret.push(i32_from_vec_u8(&slice));
	ret
}

struct Lottery {
	card_list: Vec<i32>,
	this_week: Vec<i32>,
}

impl Lottery {
	fn new_from_stdin() -> Lottery {
		let case_info = heavy_stdinln_vec_i32(2);
		let nr_n = case_info[0] as usize;
		let nr_m = case_info[1] as usize;
		let mut card_list = heavy_stdinln_vec_i32(nr_n);
		card_list.sort();
		let mut this_week = heavy_stdinln_vec_i32(nr_m);
		this_week.sort();

		Lottery {
			card_list: card_list,
			this_week: this_week,
		}
	}

	fn find(&self, target: i32) -> bool {
		let smallest = self.card_list[0];
		for card1 in self.card_list.iter() {
			if card1 + smallest * 3 > target {
				break;
			}
			for card2 in self.card_list.iter() {
				if card1 + card2 + smallest * 2 > target {
					break;
				}
				for card3 in self.card_list.iter() {
					if card1 + card2 + card3 + smallest > target {
						break;
					} else {
						let card4_expect = target - card1 - card2 - card3;
						match self.card_list.binary_search(&card4_expect) {
							Ok(_) => {
								return true;
							}
							Err(_) => {}
						}
					}
				}
			}
		}
		false
	}

	fn solve(&self) -> Vec<i32> {
		let mut ret: Vec<i32> = Vec::new();

		for target in self.this_week.iter() {
			match self.find(target.clone()) {
				true => ret.push(target.clone()),
				false => {}
			}
		}

		ret
	}
}

fn main() {
	let problem = Lottery::new_from_stdin();
	let result = problem.solve();
	match result.len() {
		0 => {
			println!("NO");
		}
		x => {
			for y in result.iter() {
				print!("{} ", y);
			}
			println!("");
		}
	};
}
