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

fn custom_prime(target: i32) -> Vec<i32> {
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
    let nr_nums = stdinln_i32() as usize;
    let nums = {
        let mut ret: Vec<i32> = Vec::with_capacity(nr_nums);
        for _ in 0..nr_nums {
            ret.push(stdinln_i32());
        }
        ret
    };

    for i in 0..nr_nums {
        let answer = custom_prime(nums[i]);
        println!("#{}:", i + 1);
        for item in answer.iter() {
            print!("{} ", item);
        }
        println!("");
    }
}
