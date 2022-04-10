const input = [];
require('readline')
  .createInterface(process.stdin, {})
  .on('line', function(line) {
    input.push(line.trim());
  })
  .on('close', function() {
    const values = input[0].split(" ").map(Number);
    const array = input[1].split(" ").map(Number);
    var count = 0;
    const n = values[0];
    const m = values[1];
    const s = values[2];
    array.forEach(function(height) {
      if (height == m || height == s)
        count++;
    });
		console.log(count);
	});