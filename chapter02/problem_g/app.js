const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const N = Number(input[0]);
const data = input[1].split(" ").map(Number);

for(let i = 0 ; i < N ; i++){
    let negativeCount = 0;

    for(let j = 0 ; j < N - 1 - i ; j++) {
        if(data[j] > data[j+1]){
            const tmp = data[j];
            data[j] = data[j+1];
            data[j+1] = tmp;

            negativeCount += 1;
        }
    }

    if(negativeCount === 0){
        break;
    }
}

console.log(data.join(" "));
