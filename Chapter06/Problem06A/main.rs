fn solve(line: &String) -> bool {
    let mut k = 0;
    for c in line.chars() {
        match c {
            '(' => k = k + 1,
            ')' => k = k - 1,
            _ => return k == 0,
        }
        if k < 0 {
            return false;
        }
    }
    k == 0
}

fn main() {
    let lines = {
        let nr_string = {
            let mut buffer = String::new();
            std::io::stdin().read_line(&mut buffer).expect("Fail");
            buffer.trim().parse::<i32>().unwrap()
        };

        let mut ret: Vec<String> = Vec::new();
        for _ in 0..nr_string {
            let mut tmp = String::new();
            std::io::stdin().read_line(&mut tmp).unwrap();
            ret.push(tmp);
        }
        ret
    };

    for line in lines.iter() {
        match solve(&line) {
            true => println!("YES"),
            false => println!("NO"),
        };
    }
}
