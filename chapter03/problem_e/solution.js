const fs = require('fs');
const input = (() => {
  const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
  let ln = 0;
  return () => stdin[ln++];
})();

const T = Number(input());
let count = 0;

for (let testCase = 0; testCase < T; testCase++) {
  const BigPaper = Array.from({length: 100}, () => Array.from({length: 100}).fill(0));
  const N = Number(input());

  for (let paperNum = 0; paperNum < N; paperNum++) {
    count = 0;
    const [x, y] = input().split(" ").map(Number);

    for (let i = x; i < x + 10; i++) {
      for (let j = y; j < y + 10; j++) {
        BigPaper[i][j]++;
      }
    }

    for (let i = 0; i < 100; i++) {
      for (let j = 0; j < 100; j++) {
        if (BigPaper[i][j]) count++;
      }
    }

  }
  console.log(count);
}
