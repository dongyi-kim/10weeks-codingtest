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

fn is_prime(target: i32) -> bool {
    if target == 2 || target == 3 {
        return true;
    } else if target % 2 == 0 {
        return false;
    } else if target <= 2 {
        return false;
    }

    let mut foo = 3;
    loop {
        if foo * foo > target {
            return true;
        } else if target % foo != 0 {
            foo += 2;
        } else {
            return false;
        }
    }

    true
}

fn is_prime_range(a: i32, b: i32) -> usize {
    let mut count = 0;
    for i in a..=b {
        match is_prime(i) {
            true => count += 1,
            false => {}
        }
    }
    count
}

fn main() {
    let nr_ranges = stdinln_i32() as usize;
    let ranges: Vec<(i32, i32)> = {
        let mut ret: Vec<(i32, i32)> = Vec::with_capacity(nr_ranges);
        for _ in 0..nr_ranges {
            let temp = heavy_stdinln_vec_i32(2);
            ret.push((temp[0], temp[1]));
        }
        ret
    };

    for i in 0..nr_ranges {
        println!("Case #{}:", i + 1);
        println!("{}", is_prime_range(ranges[i].0, ranges[i].1));
    }
}
