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

// N개의 Space로 구분된 i32를 Vec<i32>로 반환합니다. Ex)"1 2 3 4\n"
fn heavy_stdinln_vec_i32(len: usize) -> Vec<i32> {
    // 이 함수는 하나의 줄을 stdin으로 받아 여러개의 integer 을 vector로 리턴합니다.
    // 일부 코딩테스트 케이스입력에서 너무 긴 테스트케이스를 입력받기위해 사용.
    let mut __stdin = io::stdin();
    let mut __stdinlck = __stdin.lock();
    let mut ret: Vec<i32> = Vec::new();
    let mut slice: Vec<u8> = Vec::new();
    for _i in 0..len - 1 {
        __stdinlck.read_until(b' ', &mut slice).expect("Error");
        ret.push(i32_from_vec_u8(&slice));
        slice.clear();
    }
    __stdinlck.read_until(b'\n', &mut slice).expect("Error");
    ret.push(i32_from_vec_u8(&slice));
    ret
}

fn factorize(target: i32) -> Vec<i32> {
    let mut ret: Vec<i32> = Vec::new();
    // Optimized fastest way to get prime value that 2**N.
    let prime2 = target.trailing_zeros() as usize;
    for _ in 0..prime2 {
        ret.push(2);
    }
    // for(long i = 2; i*i <= N && temp != 1;)
    let mut factor = 3;
    let mut foo = target >> prime2;

    loop {
        if foo == 1 {
            break;
        } else if factor * factor > target {
            ret.push(foo);
            break;
        }

        if foo % factor == 0 {
            ret.push(factor);
            foo /= factor;
        } else {
            factor += 2;
        }
    }

    ret
}

fn main() {
    let nr_card = stdinln_i32() as usize;
    let cards = {
        let mut temp = heavy_stdinln_vec_i32(nr_card);
        temp.sort();
        temp
    };
    let cards_factorized = {
        let mut temp: Vec<i32> = Vec::new();
        for i in 0..nr_card {
            let mut factors = factorize(cards[i]);
            temp.append(&mut factors);
        }
        temp.sort();
        temp.shrink_to_fit();
        temp
    };
    let answer = {
        let mut ret = 1;
        let mut prev = 1;
        let mut count = 1;
        for prime in cards_factorized.iter() {
            if prime.clone() == prev {
                count += 1;
            } else {
                ret *= prev.pow(count / (nr_card as u32));
                prev = prime.clone();
                count = 1;
            }
        }
        ret *= prev.pow(count / (nr_card as u32));
        ret
    };
    println!("{}", answer);
}
