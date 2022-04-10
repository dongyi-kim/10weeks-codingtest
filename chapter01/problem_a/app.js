function getMax(a, b) {
	if ( a >= b)
		return a;
	else
		return b;
}

const input = [];

require('readline')
  .createInterface(process.stdin, {})
  .on('line', function(line) {
    input.push(line.trim());
  })
  .on('close', function() {
		const values = input[0].split(" ").map(Number);
		const a = values[0];
		const b = values[1];
		console.log(getMax(a,b));
	});