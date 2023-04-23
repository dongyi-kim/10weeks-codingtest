const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const N = Number(input[0]);
const arr = input[1].split(" ").map(Number);

const isOrdered = (arr) => {
    for(let i = 1 ; i < N ; i++){
        if(arr[i-1] > arr[i]){
            return "NO";
        }
    }
    return "YES";
};

const result = isOrdered(arr);

console.log(result);
