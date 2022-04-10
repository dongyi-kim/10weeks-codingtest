const input = [];
require('readline')
  .createInterface(process.stdin, {})
  .on('line', function(line) {
    input.push(line.trim());
  })
  .on('close', function() {
    var count = 0; //이 변수에 탑승할 수 있는 회원의 수를 저장한다 
    var sum = 0; //이 변수에 탑승할 수 있는 회원의 몸무게의 합을 구한다
    var answer = "NO";
    const values = input[0].split(" ").map(Number);
    const data = input[1].split(" ").map(Number);
    const n = values[0];
    const p = values[1];
    const q = values[2];
    for (var i=0; i<n; i++)
    {
      if (data[i] <= p) {
        count++;
        sum += data[i];
      }
    }
    if (sum <= q) {
      answer = "YES";
    }
    console.log(count + " " + sum);
		console.log(answer);
	});