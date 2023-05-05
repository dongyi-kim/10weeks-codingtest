const fs = require('fs');
const input = (()=>{
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln=0;
    return ()=>stdin[ln++];
})();

const N = Number(input());
const data = input().split(" ").map(Number);
const serial = Array(100001).fill(0);

for(let i = 0 ; i < N ; i++){
    serial[data[i]] += 1;
}

const getUniqueElements = serial.flatMap((x, i) => x === 1 ? i : []);

console.log(getUniqueElements.join(" "));
