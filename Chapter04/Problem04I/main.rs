fn stdinln_i32() -> i32 {
    // 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
    let mut buffer = String::new();
    std::io::stdin()
        .read_line(&mut buffer)
        .expect("Failed to read stdin.");
    buffer.trim().parse::<i32>().unwrap()
}

fn is_prime(target: i32, table: &Vec<i32>) -> bool {
    if target == 2 || target == 3 {
        return true;
    } else if target % 2 == 0 {
        return false;
    } else if target <= 2 {
        return false;
    }

    for existed in table.iter() {
        if existed * existed > target {
            return true;
        } else if target % existed == 0 {
            return false;
        }
    }

    let mut seek = table.last().unwrap().clone();
    loop {
        if seek * seek > target {
            return true;
        } else if target % seek != 0 {
            seek += 2;
        } else {
            return false;
        }
    }
}

fn gen_prime_table(biggest: i32) -> Vec<i32> {
    let mut ret: Vec<i32> = Vec::new();
    ret.push(3);
    for target in (5..=biggest).step_by(2) {
        match is_prime(target, &ret) {
            true => ret.push(target),
            false => {}
        }
    }
    ret
}

fn solve_from_prime_table(target: i32, table: &Vec<i32>) -> Option<(i32, i32)> {
    for lvalue in table.iter() {
        let rvalue_expect = target - lvalue;
        if rvalue_expect <= 1 {
            return None;
        } else {
            match table.binary_search(&rvalue_expect) {
                Ok(_) => return Some((lvalue.clone(), rvalue_expect)),
                Err(_) => {}
            }
        }
    }
    None
}

fn main() {
    let nr_nums = stdinln_i32() as usize;
    let mut biggest = i32::MIN;
    let nums = {
        let mut ret: Vec<i32> = Vec::with_capacity(nr_nums);
        for _ in 0..nr_nums {
            let temp = stdinln_i32();
            ret.push(temp);
            if biggest < temp {
                biggest = temp;
            }
        }
        ret
    };

    let prime_table = gen_prime_table(biggest);

    for idx in 0..nr_nums {
        let target = nums[idx];
        println!("Case #{}:", idx + 1);
        match solve_from_prime_table(target, &prime_table) {
            Some(x) => println!("{} = {} + {}", target, x.0, x.1),
            None => println!("-1"),
        }
    }
}
