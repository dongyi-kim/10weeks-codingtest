const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const [a, b] = input[0].split(" ").map(Number);

const getMax = (a, b) => {
	if(a > b) {
		return a;
	} else {
		return b;
	}
};

console.log(getMax(a,b));
