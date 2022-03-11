fn stdinln_i32() -> i32 {
    // 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
    let mut buffer = String::new();
    std::io::stdin()
        .read_line(&mut buffer)
        .expect("Failed to read stdin.");
    buffer.trim().parse::<i32>().unwrap()
}

enum Arrow {
    Right1to3,
    Down1to3,
    Cross1to3,
    Down3to1,
    Right3to1,
    Cross3to1,
}

impl Arrow {
    fn next_arrow(&self) -> Arrow {
        match self {
            Arrow::Right1to3 | Arrow::Down1to3 | Arrow::Cross1to3 => Arrow::Cross1to3,
            Arrow::Down3to1 | Arrow::Right3to1 | Arrow::Cross3to1 => Arrow::Cross3to1,
        }
    }

    fn next_arrow_when_collision(&self) -> Arrow {
        match self {
            Arrow::Right1to3 => Arrow::Down1to3,
            Arrow::Down1to3 => Arrow::Right1to3,
            Arrow::Cross1to3 => Arrow::Down3to1,
            Arrow::Down3to1 => Arrow::Right3to1,
            Arrow::Right3to1 => Arrow::Down3to1,
            Arrow::Cross3to1 => Arrow::Right1to3,
        }
    }

    fn next_pos(&self, pos: &(i32, i32), size: usize) -> Result<(i32, i32), ()> {
        let ret = match self {
            Arrow::Right1to3 | Arrow::Right3to1 => (pos.0 + 1, pos.1),
            Arrow::Down1to3 | Arrow::Down3to1 => (pos.0, pos.1 + 1),
            Arrow::Cross1to3 => (pos.0 - 1, pos.1 + 1),
            Arrow::Cross3to1 => (pos.0 + 1, pos.1 - 1),
        };

        if 0 <= ret.0 && ret.0 < size as i32 && 0 <= ret.1 && ret.1 < size as i32 {
            Ok(ret)
        } else {
            Err(())
        }
    }
}

fn draw_rect(size: usize) {
    // Wirte some code
    let mut rect: Vec<Vec<usize>> = vec![vec![0; size]; size];
    let mut pos: (i32, i32) = (0, 0);
    let mut arrow = Arrow::Right1to3;
    let max_move = size * size;
    let mut cnt = 1;

    rect[pos.1 as usize][pos.0 as usize] = 1;

    loop {
        if max_move <= cnt {
            break;
        }

        pos = match arrow.next_pos(&pos, size) {
            Ok(x) => x,
            Err(_) => {
                arrow = arrow.next_arrow_when_collision();
                match arrow.next_pos(&pos, size) {
                    Ok(y) => y,
                    Err(_) => {
                        arrow = arrow.next_arrow_when_collision();
                        match arrow.next_pos(&pos, size) {
                            Ok(z) => z,
                            Err(_) => panic!("Unexpected"),
                        }
                    }
                }
            }
        };

        arrow = arrow.next_arrow();
        cnt += 1;
        rect[pos.1 as usize][pos.0 as usize] = cnt;
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
