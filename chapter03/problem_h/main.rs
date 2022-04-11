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
    let mut ret: Vec<i32> = Vec::new();
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

        Lottery {
            card_list: card_list,
            this_week: heavy_stdinln_vec_i32(nr_m),
        }
    }

    fn find(&self, target: i32) -> bool {
        for card1_index in 0..self.card_list.len() {
            let card1 = self.card_list[card1_index];

            let expect_card2 = target - card1;

            match self.card_list.binary_search(&expect_card2) {
                Ok(x) => {
                    return true;
                }
                Err(x) => {}
            }
        }
        false
    }

    fn solve(&self) -> i32 {
        let mut result = 0;

        for target in self.this_week.iter() {
            match self.find(target.clone()) {
                true => result += 1,
                false => {}
            }
        }

        result
    }
}

fn main() {
    let problem = Lottery::new_from_stdin();
    let answer = problem.solve();
    println!("{}", answer);
}
