use std::io;
use std::io::prelude::*;

fn stdinln_i32() -> i32 {
	// 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
	let mut buffer = String::new();
	std::io::stdin().read_line(&mut buffer).expect("Failed to read stdin.");
	buffer.trim().parse::<i32>().unwrap()
}

// (len: usize) 몇개가 들어올지는 확실히 정해져있습니다.
fn i32_from_vec_u8(vector :&Vec<u8>) -> i32{
	let mut ret: i32 = 0;
	for i in 0..vector.len() {
		if b'0' <= vector[i] && vector[i] <= b'9' {
			ret = ret*10 + (vector[i]-b'0') as i32;
		}else {
			break;
		}
	}
	ret
}

fn heavy_stdinln_vec_i32(len: usize) -> Vec<i32> {
	// 이 함수는 하나의 줄을 stdin으로 받아 여러개의 integer 을 vector로 리턴합니다.
	let mut ret: Vec<i32> = Vec::new();
	for _i in 0..len-1 {
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

struct BitMap14B {
	// Assume ARCH is 64bit oriented system.
	// 100,000 <= 131072 == (2**14)*8(byte to bits)
	map: [u64; 2048]
}

impl BitMap14B {
	fn new() -> BitMap14B {
		BitMap14B {map : [0x0; 2048]}
	}

	// set_bit 131072비트의 맵에서 target 비트를 set함 (1로 바꿈/ set high).
	fn set_bit(&mut self, target: usize) {
		self.map[target>>6] |= 1 << (target&63);
	}

	// get_bit 131072비트의 맵에서 target 비트를 bool로 return함.
	fn get_bit(&mut self, target: usize) -> bool {
		self.map[target>>6] & (1 << (target&63)) != 0
	}
}

struct IAmRoot {
	bitmap: BitMap14B,
	xormap: BitMap14B,
	nr_fan: usize,
	ticket_list: Vec<i32>,
}

impl IAmRoot {
	fn new_from_vec(tickets: Vec<i32>, len: usize) -> IAmRoot {
		assert_eq!(tickets.len() , len);
		IAmRoot{bitmap: BitMap14B::new(), xormap: BitMap14B::new(),
			nr_fan: len, ticket_list: tickets}
	}

	fn solve(&mut self) {
		for i in 0..self.nr_fan {
			let pos = self.ticket_list[i] as usize;

			match self.bitmap.get_bit(pos) {
				true  => self.xormap.set_bit(pos),
				false => self.bitmap.set_bit(pos),
			}
		}

		for i in 0..100001 {
			let applied  = self.bitmap.get_bit(i);
			let duplicated = self.xormap.get_bit(i);
			if applied == true && duplicated == false {
				print!("{} ", i);
			}
		}
		println!("");
	}
}

fn main() {
	let nr_fan = stdinln_i32() as usize;
	let mut kernel_study = IAmRoot::new_from_vec(heavy_stdinln_vec_i32(nr_fan), nr_fan);
	kernel_study.solve();
}