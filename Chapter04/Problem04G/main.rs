use std::io;
use std::io::prelude::*;

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

enum Action {
    Display(i32),
    RotateLeft(i32),
    RotateRight(i32),
    Init,
}

impl Action {
    fn new_from_stdin() -> Action {
        let temp = stdinln_vec_i32();
        match temp[0] {
            0 => Action::Display(temp[1]),
            1 => Action::RotateLeft(temp[1]),
            2 => Action::RotateRight(temp[1]),
            3 => Action::Init,
            _ => panic!("Unexpctable"),
        }
    }
}

struct Shake {
    map: Vec<i32>,
    action_list: Vec<Action>,
    len: i32,
}

impl Shake {
    fn new_from_stdin() -> Shake {
        let case_info = stdinln_vec_i32();
        let n = case_info[0] as usize;
        let m = case_info[1] as usize;

        let map = heavy_stdinln_vec_i32(n);

        let action_list: Vec<Action> = {
            let mut ret: Vec<Action> = Vec::with_capacity(m);
            for _ in 0..m {
                ret.push(Action::new_from_stdin());
            }
            ret
        };

        Shake {
            map: map,
            action_list: action_list,
            len: n as i32,
        }
    }

    fn solve(&self) {
        // Write some code here.
        let mut rotate: i32 = 0;
        for action in self.action_list.iter() {
            match action {
                Action::Display(x) => println!(
                    "{}",
                    self.map[((rotate + x + self.len) % self.len) as usize]
                ),
                Action::RotateLeft(x) => rotate = (rotate + x) % self.len,
                Action::RotateRight(x) => rotate = (rotate - x) % self.len,
                Action::Init => rotate = 0,
            }
        }
    }
}

fn main() {
    let stuff = Shake::new_from_stdin();
    stuff.solve();
}
