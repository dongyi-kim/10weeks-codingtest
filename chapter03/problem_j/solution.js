const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const cards = input[1].split(" ").map(Number);
const target = input[2].split(" ").map(Number);

const answer = [];

const data = new Set();

for (const c1 of cards) {
    for (const c2 of cards) {
        data.add(c1 + c2);
    }
}

const pairs = Array.from(data).sort((x, y) => x - y);

const binarySearch = (target, data) => {
    let low = 0;
    let high = data.length - 1;
    while (low <= high) {
        let mid = Math.floor((low + high) / 2)
        if (target === data[mid]) return data[mid];
        else if (target < data[mid]) high = mid - 1;
        else low = mid + 1;
    }
    return false;
}

let isBinary = false;

for (const t of target) {
    isBinary = false;
    for (const c of pairs) {
        const target = t - c;

        if (binarySearch(target, pairs)) {
            isBinary = true;
            break;
        }
    }
    if (isBinary) {
        answer.push(t);
    }
}

if (answer.length) {
    console.log(answer.sort((x, y) => x - y).join(" ") + " ");
} else {
    console.log("NO");
}


