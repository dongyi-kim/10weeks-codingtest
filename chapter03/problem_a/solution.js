const fs = require('fs');
const input = (()=>{
    const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
    let ln=0;
    return ()=>stdin[ln++];
})();

const n = Number(input());
const data = Array.from({length: n}, () => Number(input()));
const table = Array.from({length: 10_000}, () => 0);

const fillFrequencyTable = () => {
    data.forEach(number => table[number] += 1);
}

const getFrequentNumber = () => {
    let frequent_number = 0;

    for (let number = 0; number < table.length; number++) {
        if (table[number] > table[frequent_number]) {
            frequent_number = number;
        }
    }

    return frequent_number;
}

fillFrequencyTable();
// 자릿수 맞추어 출력하기
console.log(String(getFrequentNumber()).padStart(4, '0'));
