const fs = require('fs');
const input = (() => {
  const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
  let ln = 0;
  return () => stdin[ln++];
})();

const [N, K] = input().split(" ").map(Number);
const birthDate = input().split(" ").map(Number);

let answer = 0;
let uniqueElements = 0;
const frequency = Array.from({length: 1_000_000}).fill(0);

const addBirthDate = (birthDate) => {

  const count = frequency[birthDate];
  if (count === 0) {
    uniqueElements += 1;
  } else if (count === 1) {
    uniqueElements -= 1;
  }
  frequency[birthDate] += 1;
};

const removeBirthDate = (birthDate) => {
  const count = frequency[birthDate];
  if (count === 1) {
    uniqueElements -= 1;
  } else if (count === 2) {
    uniqueElements += 1;
  }
  frequency[birthDate] -= 1;
};

for (let i = 0; i < K - 1; i++) {
  addBirthDate(birthDate[i]);
}

for (let i = 0; i + K - 1 < N; i++) {
  let left = i;
  let right = i + K - 1;

  addBirthDate(birthDate[right]);

  if (left > 0) {
    removeBirthDate(birthDate[left - 1]);
  }

  if (uniqueElements === K) {
    answer += 1;
  }
}

console.log(answer);
