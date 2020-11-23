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

// For문을 쉽게 돌기 위해 붙인다.
// 또한 x86_64에서는 이와같은 struct는 간단하기에 Copy,Clone이 간편하다.
// 참조 https://doc.rust-lang.org/rust-by-example/trait/clone.html
// 참조 https://doc.rust-lang.org/rust-by-example/trait/derive.html
#[derive(Copy, Clone)]
struct Point {
	x: i32,
	y: i32,
}

struct Destiny {
	data: Vec<Point>,
	nr_point: usize,   // 데이터 갯수.
	nearest_path: f32, // 가장 가까운 buddy의 거리
	nr_nearest: usize, // 가장 가까운 buddy의 갯수
}

impl Point {
	fn new(_x:i32, _y:i32) -> Point {
		Point{x: _x, y: _y}
	}
	
	fn distance(self, other: &Point) -> f32 {
		// Point::distance함수를 완성해주세요!
		let x = (self.x - other.x) as f32;
		let y = (self.y - other.y) as f32;
		(x*x+y*y).sqrt()
		// End of Point::distance
	}
}

impl Destiny {
	// may not use in this code-test
	fn new() -> Destiny {
		Destiny{data: Vec::new(), nr_point: 0, nearest_path : 0.0, nr_nearest : 0}
	}

	// generate struct destiny from Vec<Vec<i32>>
	fn new_from_vv_i32(inputs: &Vec<Vec<i32>>, nr_item: usize) -> Destiny {
		let mut temp: Vec<Point> = Vec::new();
		for i in 0..nr_item {
			temp.push(Point{x:inputs[i][0], y:inputs[i][1]});
		}
		Destiny{data:temp, nr_point: nr_item, nearest_path: 0.0, nr_nearest: 0}
	}

	fn find_nearests(&mut self) {
		// Destiny::find_nearests 를 완성해주세요!
		// Point::distance를 완성 시킨 후 Point::distance 를 활용하여 풀어봅시다.
		//self.nearest_path = f32::MAX; <- 구름IDE rustc 구버젼 문제때문에 안됨;;;
		self.nearest_path = 1000000000.0;
		self.nr_nearest = 0;
		for i in 0..self.nr_point {
			for j in i+1..self.nr_point {
				let distance = self.data[i].distance(&self.data[j]);
				if distance < self.nearest_path {
					self.nearest_path = distance;
					self.nr_nearest = 1;
				}else if distance == self.nearest_path {
					self.nr_nearest += 1;
				}
			}
		}
		// End of Destiny::find_nearests
	}

	fn print(self){
		println!("{:.1}\n{}", self.nearest_path, self.nr_nearest);
	}
}

fn main() {
	let nr_stars = stdinln_i32() as usize;
	let mut testcase = Vec::new();
	for i in 0..nr_stars {
		testcase.push(stdinln_vec_i32());
	}
	let mut problem = Destiny::new_from_vv_i32(&testcase, nr_stars);
	// Point::distance 와 Destiny::find_nearests 를 완성시킵시다.
	// 또한 이 문제가 왜 이러한 방식으로 푸는지 Rust를 공부하며 학습합시다.
	// Hint : https://doc.rust-lang.org/rust-by-example/fn/methods.html
	problem.find_nearests();
	problem.print();
}