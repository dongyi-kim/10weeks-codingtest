const fs = require('fs');
const input = (()=>{
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln=0;
    return ()=>stdin[ln++];
})();

const [N, M] = input().split(" ").map(Number);
const seats = Array(N).fill(0);
const table = Array(100).fill(0);

for(let i = 0 ; i < M ; i++){
    const [left, right, color] = input().split(" ").map(Number);

    for(let j = left - 1 ; j <= right - 1 ; j++){
        seats[j] = color;
    }
}

for(let i = 0 ; i < N ; i++){
    table[ seats[i] ] += 1;
}

let minColor = -1;
let maxColor = -1;

for(let color = 0 ; color <= 99 ; color++){
    if(table[color] === 0) continue;

    if(minColor === -1 || table[color] < table[minColor]){
        minColor = color;
    }
    if(maxColor === -1 || table[color] > table[maxColor]){
        maxColor = color;
    }
}

console.log(maxColor);
console.log(minColor);
