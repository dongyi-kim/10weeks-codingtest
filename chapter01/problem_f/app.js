const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const [_, M] = input[0].split(" ").map(Number);
const data = input[1].split(" ").map(Number);

const answer = data.findIndex(x => M === x);
console.log(answer);
