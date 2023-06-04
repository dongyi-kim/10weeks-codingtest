const fs = require('fs');
const input = (() => {
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln = 0;
    return () => stdin[ln++];
})();

const testCase = Number(input())

let sudokuIndex = 1;
let groupIndex = 1;
const sudokuBoard = Array.from({length: 9}, () => Array.from({length: 9}));
const groupBoard = Array.from({length: 3}, () => Array.from({length: 3}));

for (let row = 0; row < 9; row++) {
    for (let col = 0; col < 9; col++) {
        sudokuBoard[row][col] = sudokuIndex;
        sudokuIndex++;
    }
}

for (let row = 0; row < 3; row++) {
    for (let col = 0; col < 3; col++) {
        groupBoard[row][col] = groupIndex;
        groupIndex++;
    }
}

for (let i = 0; i < testCase; i++) {
    const n = Number(input());

    const row = Math.ceil(n / 9);
    const col = (n - 1) % 9 + 1;
    const group = groupBoard[Math.ceil(row / 3) - 1][Math.ceil(col / 3) - 1];

    console.log(`Case #${i + 1}:`);
    console.log(row, col, group);

}
