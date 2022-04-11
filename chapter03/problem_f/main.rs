use std::io;
use std::io::prelude::*;

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

// Vec<u8> -> i32
fn i32_from_vec_u8(vector :&Vec<u8>) -> i32{
	let mut ret: i32 = 0;
	let mut minus_factor: i32 = 1;
	if b'0' <= vector[0] && vector[0] <= b'9' {
		ret = ret*10 + (vector[0]-b'0') as i32;
	}else if b'-' == vector[0] {
		minus_factor = -1;
	}

	for i in 1..vector.len() {
		if b'0' <= vector[i] && vector[i] <= b'9' {
			ret = ret*10 + (vector[i]-b'0') as i32;
		}else {
			break;
		}
	}
	ret * minus_factor
}

// N개의 Space로 구분된 i32를 Vec<i32>로 반환합니다. Ex)"1 2 3 4\n"
fn heavy_stdinln_vec_i32(len: usize) -> Vec<i32> {
	// 이 함수는 하나의 줄을 stdin으로 받아 여러개의 integer 을 vector로 리턴합니다.
	// 일부 코딩테스트 케이스입력에서 너무 긴 테스트케이스를 입력받기위해 사용.
	let mut __stdin = io::stdin();
	let mut __stdinlck = __stdin.lock();
	let mut ret: Vec<i32> = Vec::new();
	let mut slice: Vec<u8> = Vec::new();
	for _i in 0..len-1 {
		__stdinlck.read_until(b' ', &mut slice).expect("Error");
		ret.push(i32_from_vec_u8(&slice));
		slice.clear();
	}
	__stdinlck.read_until(b'\n', &mut slice).expect("Error");
	ret.push(i32_from_vec_u8(&slice));
	ret
}

struct FanChoice {
	l: usize,
	r: usize,
}

struct IAmRoot {
	nr_cards: usize,
	nr_fans: usize,
	cards: Vec<i32>,
	fans: Vec<FanChoice>,
}

impl IAmRoot {
	fn new_from_vecs(cards: Vec<i32>, fans: Vec<FanChoice>) -> IAmRoot {

		IAmRoot{
			nr_cards : cards.len(),
			nr_fans : fans.len(),
			cards : cards,
			fans : fans
		}
	}

	fn solve(&mut self) {
		let mut best_order: usize = 0;
		let mut best_score: i32 = i32::MIN;
		for i in 0..self.nr_fans {
			let mut seek_score: i32 = 0;
			for j in self.fans[i].l-1 ..=self.fans[i].r-1 {
				seek_score = seek_score + self.cards[j]
			}
			if seek_score > best_score {
				best_order = i+1;
				best_score = seek_score;
			}
		}
		println!("{} {}", best_order, best_score);
	}
}

fn main() {
	let condition = stdinln_vec_i32();
	let nr_cards: usize = condition[0] as usize;
	let nr_fan: usize = condition[1] as usize;
	// 기존의 stdin으로는 rust의 기본 buffer크기때문에 한줄에
	// 8Kbyte이상오는 testcase를 읽을 수 없습니다.
	// 따라서 이번 문제는 새로 커스텀한 heavy_stdinln_vec_i32 을 사용합니다.
	let cards = heavy_stdinln_vec_i32(nr_cards);
	let mut fans: Vec<FanChoice> = Vec::new();
	for i in 0..nr_fan {
		let tuple = stdinln_vec_i32();
		fans.push(FanChoice{
			l:tuple[0] as usize,
			r:tuple[1] as usize
		});
	}
	// For strict length check.
	assert_eq!(cards.len() , nr_cards);
	assert_eq!(fans.len() , nr_fan);

	let mut kernel_study = IAmRoot::new_from_vecs(cards, fans);
	kernel_study.solve();
}
