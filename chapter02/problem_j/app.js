const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const [n, k] = input[0].split(" ").map(Number);
const data = input[0].split(" ").map(Number);

let sum = 0;

for(let i = 0 ; i < k ; i++){
    sum += data[i];
}

if(sum % 2 === 0) return console.log("YES");

for(let i = 1 ; i + k - 1 < n ; i++){
    sum = sum - data[i - 1] + data[i + k - 1];

    if(sum % 2 === 0) return console.log("YES");
}

return console.log("NO");
