const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const n = Number(input[0]);
const data = input[1].split(" ").map(Number);

const getMinIndexInRange = (data, begin, end) => {
    let minIndex = begin;
    for(let i = begin ; i <= end ; i++){
        if(data[i] < data[minIndex]){
            minIndex = i;
        }
    }
    return minIndex;
}

for(let i = 0 ; i < n ; i++) {
    let minIndex = getMinIndexInRange(data, i, n-1);
    let tmp = data[i];
    data[i] = data[minIndex];
    data[minIndex] = tmp;
}

console.log(data.join(" "));
