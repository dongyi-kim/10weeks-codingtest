const fs = require('fs');
const input = (()=>{
  const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
  let ln=0;
  return ()=>stdin[ln++];
})();

const n = Number(input());
const data = input().split(" ").map(Number); // 두 번째 라인의 입력받은 숫자들을 숫자 배열로 저장

const sum = data.reduce((acc,cur) => acc + cur,0); // 총합
const diff = data.map((x) => Math.abs(sum - x * n)); // 절대값(총합 - 요소 * 요소의 총 갯수) 차이 배열
const min = Math.min(...diff);
const index = diff.findIndex((x) => x === min); // 차이가 가장 작은 수의 index를 반환

console.log(index+1, data[index])
