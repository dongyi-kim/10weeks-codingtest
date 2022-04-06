const fs = require('fs');
const input = (()=>{
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln=0;
    return ()=>stdin[ln++];
})();

// data 입력 및 배열 정렬
const n = Number(input());
const data = []
for(let i = 0 ; i < n ; i++){
    data[i] = Number(input());
}
data.sort()

// 전화번호 빈도수 table 선언, 초기화
const table = Array(10000).fill(0)

for(let i = 0 ; i < n ; i++){
    table[data[i]] += 1;
}

const max = Math.max(...table);

let answer = String(table.findIndex((x)=> x === max))

//자릿수 맞추기
while(answer.length < 4){
    answer = ["0"].concat(num.split("")).join("")
}

console.log(answer)