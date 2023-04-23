const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

const height = input[1].split(" ").map(Number);
const month = input[2].split(" ").map(Number);
const current_month = Number(input[3]);

const index = month.lastIndexOf(current_month);

console.log(index === -1 ? index : height[index]);
