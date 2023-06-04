const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const cards = input[1].split(" ").map(Number).sort((x, y) => x - y);
const winning = input[2].split(" ").map(Number);
const answer = [];

const binarySearch = (target, data) => {
    let low = 0;
    let high = data.length - 1;
    while (low <= high) {
        let mid = Math.floor((low + high) / 2);
        if (target === data[mid]) return data[mid];
        else if (target < data[mid]) high = mid - 1;
        else low = mid + 1;
    }
    return false;
}

for (const k of winning) {

    let searchResult = false;

    for (const p of cards) {

        if (p > k) break;

        for (const q of cards) {

            if (q > k) break;

            const target = k - (p + q);

            if (target > 0 && binarySearch(target, cards) > 0) {
                searchResult = true;
                break;
            }

        }

        if (searchResult) {
            break;
        }

    }
    if (searchResult) {
        answer.push(k);
    }
}

console.log(answer.sort((x, y) => x - y).join(" ") + " ");
