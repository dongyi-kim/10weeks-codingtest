const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const answer = Math.max(...input[1].split(" "));
console.log(answer);
