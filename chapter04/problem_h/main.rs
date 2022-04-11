use std::io;
use std::io::prelude::*;

fn stdinln_i32() -> i32 {
    // 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
    let mut buffer = String::new();
    std::io::stdin()
        .read_line(&mut buffer)
        .expect("Failed to read stdin.");
    buffer.trim().parse::<i32>().unwrap()
}

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

struct Kaing {
    m: i32,
    n: i32,
    x: i32,
    y: i32,
}

impl Kaing {
    fn solve(&self) -> i32 {
        // Write some codes.
        let mut a = self.x;
        let mut b = self.y;
        let max_year = self.m * self.n;

        loop {
            if b == 0 {
                break;
            } else {
                let r = a % b;
                a = b;
                b = r;
            }
        }

        for i in (self.x - 1..max_year).step_by(self.m as usize) {
            let __y = (i % self.n) + 1;
            if __y == self.y {
                return i + 1;
            }
        }
        -1
    }
}

fn main() {
    let nr_question = stdinln_i32() as usize;
    let questions = {
        let mut ret: Vec<Kaing> = Vec::with_capacity(nr_question);
        for _ in 0..nr_question {
            let temp = heavy_stdinln_vec_i32(4);
            ret.push(Kaing {
                m: temp[0],
                n: temp[1],
                x: temp[2],
                y: temp[3],
            });
        }
        ret
    };

    for i in 0..nr_question {
        println!("{}", questions[i].solve())
    }
}
