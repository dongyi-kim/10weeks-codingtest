const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

input.shift();

const match = [];
input.filter((x, i) => x === "AJOU" && match.push(i + 1));

console.log(match[0], match.at(-1));
