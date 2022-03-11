fn stdinln_i32() -> i32 {
    // 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
    let mut buffer = String::new();
    std::io::stdin()
        .read_line(&mut buffer)
        .expect("Failed to read stdin.");
    buffer.trim().parse::<i32>().unwrap()
}

enum Arrow {
    Right,
    Down,
    Left,
    Up,
}

impl Arrow {
    fn next_arrow(&self) -> Arrow {
        match self {
            Arrow::Right => Arrow::Down,
            Arrow::Down => Arrow::Left,
            Arrow::Left => Arrow::Up,
            Arrow::Up => Arrow::Right,
        }
    }

    fn next_pos(&self, pos: &(i32, i32)) -> (i32, i32) {
        match self {
            Arrow::Right => (pos.0 + 1, pos.1),
            Arrow::Down => (pos.0, pos.1 + 1),
            Arrow::Left => (pos.0 - 1, pos.1),
            Arrow::Up => (pos.0, pos.1 - 1),
        }
    }
}

fn draw_rect(size: usize) {
    // Wirte some code
    let mut rect: Vec<Vec<usize>> = vec![vec![0; size]; size];
    let mut pos: (i32, i32) = (0, 0);
    let mut arrow = Arrow::Right;
    let max_move = size * size;
    let mut cnt = 1;
    loop {
        if max_move <= cnt {
            rect[pos.1 as usize][pos.0 as usize] = cnt;
            break;
        }

        rect[pos.1 as usize][pos.0 as usize] = cnt;

        let pos_next = arrow.next_pos(&pos);
        let pos_next_avail = {
            if !((0 <= pos_next.0 && pos_next.0 < size as i32)
                && (0 <= pos_next.1 && pos_next.1 < size as i32))
            {
                false
            } else if rect[pos_next.1 as usize][pos_next.0 as usize] != 0 {
                false
            } else {
                true
            }
        };
        if pos_next_avail {
            pos = pos_next;
            cnt += 1;
        } else {
            arrow = arrow.next_arrow();
        }
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
