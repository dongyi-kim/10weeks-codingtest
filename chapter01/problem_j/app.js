const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const N = Number(input[0]);

// 등차수열의 합 공식으로 대체
const getRangeSumFromOne = (i) => ((i * (i + 1)) / 2);

const getAnswer = (N) => {
    let answer = 0;
    for(let i =  1; i <= N; i++) {
        let rangeSum = getRangeSumFromOne(i);
        answer += rangeSum;
    }
    return answer;
};

const answer = getAnswer(N);

console.log(answer);
