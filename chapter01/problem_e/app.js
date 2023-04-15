const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const [_, P, Q] = input[0].split(" ").map(Number);
const data = input[1].split(" ").map(Number);

const weight_pass = data.filter(weight => P >= weight);
const sum = weight_pass.reduce((acc, cur) => { return acc + cur });

console.log(weight_pass.length, sum);
console.log(sum <= Q ? "YES" : "NO");
