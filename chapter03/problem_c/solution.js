const fs = require('fs');
const input = (()=>{
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln=0;
    return ()=>stdin[ln++];
})();

const n = Number(input())
const data = input().split(" ").map(Number)
const serial = Array(100001).fill(0)
const application = []

for(let i = 0 ; i < n ; i++){
    serial[data[i]] += 1;
}

for(let i = 0 ; i < serial.length ; i++){
    if(serial[i] === 1) application.push(i)
}

console.log(application.join(" "))