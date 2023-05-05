const fs = require('fs');
const input = (() => {
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln = 0;
    return () => stdin[ln++];
})();

const T = Number(input());
const data = [];
const FIBONACCI_TABLE = [0, 1];

let maxCase = 0;

for (let i = 0; i < T; i++) {
    data[i] = Number(input());
    if (data[i] > maxCase) {
        maxCase = data[i];
    }
}

const makeFibonacciTable = () => {
    for (let i = 2; i <= maxCase; i++) {
        FIBONACCI_TABLE[i] = (FIBONACCI_TABLE[i - 1] + FIBONACCI_TABLE[i - 2]) % 100_000_000;
    }
};

const getFibo = () => {
    return data.map(num => FIBONACCI_TABLE[num]).join("\n");
};

makeFibonacciTable();
console.log(getFibo());
