const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const cards = input[1].split(" ").map(Number).sort((x, y) => x - y);
const winning = input[2].split(" ").map(Number);
let answer = 0;

const binarySearch = (target, array) => {
    let low = 0;
    let high = array.length - 1;
    while (low <= high) {
        const mid = Math.floor((high + low) / 2);
        if (array[mid] === target) {
            return mid;
        } else if (array[mid] > target) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return -1;
}

for (const k of winning) {
    for (const x of cards) {
        const y = k - x;
        if (y && binarySearch(y, cards) > 0) {
            answer++;
            break
        }
    }
}

console.log(answer);
