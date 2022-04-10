use std::io;

fn stdinln_i32() -> i32 {
    // 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
    let mut buffer = String::new();
    std::io::stdin()
        .read_line(&mut buffer)
        .expect("Failed to read stdin.");
    buffer.trim().parse::<i32>().unwrap()
}

fn solve(target: i32) -> (i32, i32, i32) {
    let x = (target - 1) % 9;
    let y = (target - 1) / 9;
    let g = (y / 3) * 3 + (x / 3);
    (y + 1, x + 1, g + 1)
}

fn main() {
    let mut question_list: Vec<i32> = Vec::with_capacity(100);
    let nr_case = stdinln_i32() as usize;

    for _ in 0..nr_case {
        question_list.push(stdinln_i32());
    }

    for i in 0..nr_case {
        let ans = solve(question_list[i]);
        println!("Case #{}:", i + 1);
        println!("{} {} {}", ans.0, ans.1, ans.2);
    }
}
