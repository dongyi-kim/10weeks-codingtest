const fs = require('fs');
const input = (() => {
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln = 0;
    return () => stdin[ln++];
})();

const n = Number(input());

const getSquaredDistanceTo = (p1, p2) => {
    const deltaX = p1.x - p2.x;
    const deltaY = p1.y - p2.y;
    return deltaX * deltaX + deltaY * deltaY;
};

const getDistanceTo = (p1, p2) => {
    return Math.sqrt(getSquaredDistanceTo(p1, p2));
}

const points = [];

for (let i = 0; i < n; i++) {
    const [x, y] = input().split(" ").map(Number);
    points.push({x, y});
}

let minDist = Number.MAX_SAFE_INTEGER;
let minCount = 0;

for (let i = 0; i < n; i++) {
    for (let j = 0; j < i; j++) {
        const sqd = getDistanceTo(points[i], points[j]);
        if (sqd < minDist) {
            minDist = sqd;
            minCount = 1;
        } else if (sqd === minDist) {
            minCount++;
        }
    }
}

console.log(minDist.toFixed(1));
console.log(minCount);
