fn stdinln_i32() -> i32 {
    // 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
    let mut buffer = String::new();
    std::io::stdin()
        .read_line(&mut buffer)
        .expect("Failed to read stdin.");
    buffer.trim().parse::<i32>().unwrap()
}

enum Arrow {
    Right1,
    Down1,
    Left,
    Down2,
    Right2,
    Up,
}

impl Arrow {
    fn next_arrow(&self) -> Arrow {
        match self {
            Arrow::Right1 => Arrow::Down1,
            Arrow::Down1 => Arrow::Left,
            Arrow::Left => Arrow::Down2,
            Arrow::Down2 => Arrow::Right2,
            Arrow::Right2 => Arrow::Up,
            Arrow::Up => Arrow::Right1,
        }
    }

    fn next_pos(&self, pos: &(i32, i32)) -> (i32, i32) {
        match self {
            Arrow::Right1 => (pos.0 + 1, pos.1),
            Arrow::Down1 => (pos.0, pos.1 + 1),
            Arrow::Left => (pos.0 - 1, pos.1),
            Arrow::Down2 => (pos.0, pos.1 + 1),
            Arrow::Right2 => (pos.0 + 1, pos.1),
            Arrow::Up => (pos.0, pos.1 - 1),
        }
    }
}

fn draw_rect(size: usize) {
    // Wirte some code
    let mut rect: Vec<Vec<usize>> = vec![vec![0; size]; size];
    let mut pos: (i32, i32) = (0, 0);
    let mut arrow = Arrow::Right1;
    let max_move = size * size;
    let mut cnt = 1;
    let mut s = 0;
    let mut step = 1;
    let mut phase = 0;

    rect[pos.1 as usize][pos.0 as usize] = 1;
    loop {
        if max_move <= cnt {
            break;
        }

        pos = arrow.next_pos(&pos);
        cnt += 1;
        rect[pos.1 as usize][pos.0 as usize] = cnt;

        if step <= s || phase == 0 {
            arrow = arrow.next_arrow();
            phase += 1;
            if phase >= 3 {
                phase = 0;
                step += 1;
            }
            s = 0;
        }
        s += 1;
    }

    for y in 0..size {
        for x in 0..size {
            print!("{} ", rect[y][x]);
        }
        println!("");
    }
}

fn main() {
    let nr_rect = stdinln_i32() as usize;
    let rect_sizes = {
        let mut temp: Vec<usize> = Vec::with_capacity(nr_rect);
        for _ in 0..nr_rect {
            temp.push(stdinln_i32() as usize);
        }
        temp
    };

    for size in rect_sizes.iter() {
        draw_rect(size.clone());
    }
}
