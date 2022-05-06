const fs = require('fs');
const input = (()=>{
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln=0;
    return ()=>stdin[ln++];
})();

let answer = '';
const n = Number(input());
const data = [];
const fibotable = [];

for(let i = 0 ; i < n ; i++){
    data[i] = Number(input());
}

const maxIndex = Math.max(...data);

let fibo1 = 0;
let fibo2 = 1;
let sum = 0;

for(let i = 0 ; i < maxIndex ; i++){
    sum = (fibo1 + fibo2) % 100000000;
    fibotable[i] = sum;
    fibo1 = fibo2;
    fibo2 = sum;
}

fibotable.unshift(1);
fibotable.unshift(0);

for(let i = 0; i < n ; i++){
    answer += fibotable[data[i]] +"\n";
}

console.log(answer);