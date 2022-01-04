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

fn stdinln_i32() -> i32 {
    // 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
    let mut buffer = String::new();
    std::io::stdin()
        .read_line(&mut buffer)
        .expect("Failed to read stdin.");
    buffer.trim().parse::<i32>().unwrap()
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

fn custom_gcd(a: i64, b: i64) -> i64 {
    // Optimized fastest way to get gcd value that 2**N.
    let gcd2_log = std::cmp::min(a.trailing_zeros(), b.trailing_zeros());
    let mut x = a >> gcd2_log;
    let mut y = b >> gcd2_log;
    loop {
        let z = x % y;
        x = y;
        y = z;
        if y == 0 {
            break;
        }
    }
    x << gcd2_log
}

fn custom_lcm(a: i64, b: i32, gcd: i64) -> i64 {
    (b as i64 / gcd as i64) * a as i64
}

fn main() {
    let nr_nums = stdinln_i32() as usize;
    let nums = heavy_stdinln_vec_i32(nr_nums);

    let gcd_lcm: (i64, i64) = {
        let mut g = nums[0] as i64;
        let mut l = nums[0] as i64;
        for i in 1..nr_nums {
            g = custom_gcd(l, nums[i] as i64);
            l = custom_lcm(l, nums[i], g);
        }
        (g, l)
    };

    println!("{}", gcd_lcm.1 + 1);
}
