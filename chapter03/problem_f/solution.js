const fs = require('fs');
const input = (() => {
  const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
  let ln = 0;
  return () => stdin[ln++];
})();

const [_, M] = input().split(" ").map(Number);
const card = input().split(" ").map(Number);

const rangeSum = card.reduce((acc, curr, i) => (acc[i + 1] = acc[i] + curr, acc), [0]);

let totalPoint = rangeSum[0];
let index = 0;

for (let i = 0; i < M; i++) {
  const [left, right] = input().split(" ").map(Number);
  const range = rangeSum[right] - rangeSum[left - 1];

  if (totalPoint < range) {
    totalPoint = range;
    index = i + 1;
  }
}

console.log(index, totalPoint);
