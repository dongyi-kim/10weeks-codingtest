fn stdinln_i32() -> i32 {
    // 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
    let mut buffer = String::new();
    std::io::stdin()
        .read_line(&mut buffer)
        .expect("Failed to read stdin.");
    buffer.trim().parse::<i32>().unwrap()
}

fn stdinln_vec_i32() -> Vec<i32> {
    // 이 함수는 하나의 줄을 stdin으로 받아 여러개의 integer 을 vector로 리턴합니다.
    let mut buffer = String::new();
    std::io::stdin()
        .read_line(&mut buffer)
        .expect("Failed to read line");
    let ret: Vec<i32> = buffer
        .split(" ")
        .map(|x| x.trim().parse().expect("Unexpected Integer Pattern"))
        .collect();
    ret
}

fn custom_gcd(a: i32, b: i32) -> i32 {
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

fn custom_lcm(a: i32, b: i32, gcd: i32) -> i64 {
    (b as i64 / gcd as i64) * a as i64
}

fn main() {
    let mut question: Vec<(i32, i32)> = Vec::with_capacity(100);
    let nr_case = stdinln_i32() as usize;

    for _ in 0..nr_case {
        let case = stdinln_vec_i32();
        question.push((case[0], case[1]));
    }

    for i in 0..nr_case {
        let gcd = custom_gcd(question[i].0, question[i].1);
        let lcm = custom_lcm(question[i].0, question[i].1, gcd);

        println!("Case #{}:\n{} {}", i + 1, gcd, lcm);
    }
}
