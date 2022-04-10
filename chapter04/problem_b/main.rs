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

fn main() {
    let mut map: [bool; 5000] = [false; 5000];
    let mut result: Vec<i32> = Vec::with_capacity(1000);
    let case_info = stdinln_vec_i32();
    let len = case_info[0] as usize;
    let nr_user = case_info[1] as usize;

    for _ in 0..nr_user {
        let input = stdinln_i32() as usize;
        let mut idx = input % len;
        loop {
            if map[idx] {
                idx = (idx + 1) % len;
            } else {
                map[idx] = true;
                result.push(idx as i32);
                break;
            }
        }
    }

    for i in 0..nr_user {
        println!("{}", result[i]);
    }
}
