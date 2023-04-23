const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const isDecimal = (n) => {
    if(n === 1) return false;
    if(n === 2) return true;
    else if(n % 2 === 0) return false;

    for(let i = 3; i < Math.sqrt(n); i+=2){
        if(n % i === 0) return false;
    }
    return true;
}

for(let i = 1 ; i < input.length; i++){
    console.log(`Case #${i}`);
    console.log(isDecimal(Number(input[i])) ? "YES" : "NO");
}
