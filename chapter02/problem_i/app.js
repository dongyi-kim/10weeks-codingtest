const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const n = Number(input[0]);
const data = input[1].split(" ").map(Number);

const isConsecutive = (n, data) => {
    let l, g, m;
    l = g = data[0];

    for (let i = 0; i < n; i++) {
        if (l > data[i]) {
            l = data[i];
        }

        if (g < data[i]) {
            g = data[i];
        }
    }

    m = (g - l + 1);

    if (n === m) {
        return "YES";
    } else {
        return "NO";
    }
}

console.log(isConsecutive(n, data));
