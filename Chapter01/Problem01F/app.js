function find_value(value, data, length)
{
  var index = -1;
  for (var i=0; i<length; i++)
  {
    if (value == data[i]) {
      index = i;
    }
  }
  return index;
}

const input = [];
require('readline')
  .createInterface(process.stdin, {})
  .on('line', function(line) {
    input.push(line.trim());
  })
  .on('close', function() {
    const values = input[0].split(" ").map(Number);
    const data = input[1].split(" ").map(Number);
    const n = values[0];
    const m = values[1];

		console.log(find_value(m, data, n));
	});