const fs = require('fs');
const input = (() => {
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln = 0;
    return () => stdin[ln++];
})();

const caseSize = Number(input());
let answer = '';

const isInside = (x, y, R) => {
    const sqd = x * x + y * y;
    return sqd < R * R;
}

const testCase = (caseIndex) => {
    const R = Number(input());
    let sum = 0;
    let y = R;

    for (let x = 0; x <= R; x++) {
        for (; y >= 0; y--) {
            if (isInside(x, y, R)) {
                sum += y + 1;
                break;
            }
        }
    }
    answer += `#${caseIndex}\n`;
    answer += `${sum * 4}\n`;
};

for (let caseIndex = 1; caseIndex <= caseSize; caseIndex++) {
    testCase(caseIndex);
}

console.log(answer);
