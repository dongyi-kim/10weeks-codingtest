use std::io;
use std::cmp;
use std::usize;

fn stdinln_i32() -> i32 {
	// 이 함수는 하나의 줄을 stdin으로 받아 단 하나의 integer를 리턴합니다.
	let mut buffer = String::new();
	std::io::stdin().read_line(&mut buffer).expect("Failed to read stdin.");
	buffer.trim().parse::<i32>().unwrap()
}

fn stdinln_vec_i32() -> Vec<i32> {
	// 이 함수는 하나의 줄을 stdin으로 받아 여러개의 integer 을 vector로 리턴합니다.
	let mut buffer = String::new();
	std::io::stdin().read_line(&mut buffer).expect("Failed to read line");
	let ret: Vec<i32> = buffer.split(" ")
		.map(|x| x.trim().parse().expect("Unexpected Integer Pattern"))
		.collect();
	ret
}

struct RazerTowerMap{
    space: Vec<Vec<i32>>,
    x: usize,
    y: usize,
    horizontal: Vec<usize>,
    vertical: Vec<usize>,
}

fn get_cross(map: &RazerTowerMap, x: usize, y: usize) -> usize {
    let mut count: i32 = 0;
    // side 1 y:- , x:+
    for i in 1..=cmp::min(y, map.x - x - 1){
        count += map.space[y-i][x+i];
    }
    // side 2 y:- , x:-
    for i in 1..=cmp::min(y, x){
        count += map.space[y-i][x-i];
    }
    // side 3 y:+ , x:-
    for i in 1..=cmp::min(map.y - y - 1, x){
        count += map.space[y+i][x-i];
    }
    // side 4 y:+ , x:+
    for i in 1..=cmp::min(map.y - y - 1, map.x - x - 1){
        count += map.space[y+i][x+i];
    }
    
    count as usize
}

fn get_horizontal(map: &RazerTowerMap, y: usize) -> usize {
    let sum: i32 = map.space[y].iter().sum();
    sum as usize
}

fn get_vertical(map: &RazerTowerMap, x: usize) -> usize {
    let mut sum: i32 = 0;
    for i in 0..map.y {
        sum += map.space[i][x];
    }
    sum as usize
}

fn pre_works(map: &mut RazerTowerMap){
    for i in 0..map.x{
        map.horizontal.push(get_horizontal(map, i));
        map.vertical.push(get_vertical(map, i));
    }
}

fn get_razer_value(map: &RazerTowerMap, x: usize, y: usize) -> usize {
    map.vertical[x] + map.horizontal[y]
    + get_cross(&map, x, y) - map.space[y][x] as usize
}

fn solve() -> usize {
	let mut space = Vec::new();
	let case_width = stdinln_i32() as usize;
	for i in 0..case_width {
		space.push(stdinln_vec_i32());
	}
	
    let mut map = RazerTowerMap {
        space:space,
        x: case_width as usize,
        y: case_width as usize,
        horizontal: Vec::new(),
        vertical: Vec::new(),
    };

    pre_works(&mut map);

    let mut biggest :usize = 0;
    for y in 0..map.y{
        for x in 0..map.x {
            biggest = cmp::max(biggest, get_razer_value(&map, x, y));
        }
    }

	biggest
}


fn main() {
	let nr_case = stdinln_i32();
	let mut answer_vec :Vec<usize> = Vec::new();
	for i in 0..nr_case {
		answer_vec.push(solve());
	}
	for ans in answer_vec.iter() {
		println!("{}", ans);
	}
	
}