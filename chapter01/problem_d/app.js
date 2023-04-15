const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const m = input[0].split(" ")[1];
const s = input[0].split(" ")[2];

console.log(input[1].split(" ").filter((x) => x === m || x === s).length);
