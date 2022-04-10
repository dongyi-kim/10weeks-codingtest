use std::io;

fn stdinln_i32() -> i32 {
	// 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
	let mut buffer = String::new();
	std::io::stdin().read_line(&mut buffer).expect("Failed to read stdin.");
	buffer.trim().parse::<i32>().unwrap()
}

struct LimitedFibo {
	series: Vec<i32>,
	nr_case: usize,
	cases: Vec<i32>,
}

impl LimitedFibo {
	fn new_from_cases(inputs: Vec<i32>, len: usize) -> LimitedFibo {
		// 피보나치 수열을 만들기 위해서 첫번째, 두번째 값을 미리 넣어줘야한다.
		let initial_series = vec![0, 1];
		LimitedFibo {series: initial_series, nr_case: len, cases: inputs}
	}
	
	fn calculation(&mut self) {
		// LimitedFibo::calculation() 을 완성해주세요.
		// 사용자가 요구하는 피보나치(8자 한정)을 self.cases vector에 넣어줍시다.
		// 1번째 피보나치 값은 series[0]에 들어가며 100번째는 [99]에 들어갑니다.
		let max_order = match self.cases.iter().max() {
			Some(max) => (*max - 1) as usize,
			None => 0 as usize,
		};
		// 구름IDE가 구버젼인 관계로 0 .. =max_order 사용 불가능
		let start = self.series.len();
		for i in start..max_order+1 {
			let val = (self.series[i-1] + self.series[i-2]) % 100000000;
			self.series.push(val);
		}
		// end of LimitedFibo::calculation()
	}
	
	fn show_result(self) {
		for iidx in self.cases {
			let uidx = (iidx-1) as usize;
			let temp = {
				// 응시자가 해당 번호에대해서 만들지 않았다면 -1출력.
				if uidx >= self.series.len() {
					-1 as i32
				}else {
					self.series[uidx]
				}
			};
			println!("{}", temp);
		}
	}
}

fn main() {
	let nr_case = stdinln_i32() as usize;
	let mut inputs: Vec<i32> = Vec::new();
	for _i in 0..nr_case {
		inputs.push(stdinln_i32());
	}
	let mut problem = LimitedFibo::new_from_cases(inputs, nr_case);
	// LimitedFibo::calculation() 을 완성해주세요!
	problem.calculation();
	problem.show_result();
}