function get_sum(data, length)
{
  var sum = 0;

  for (var i=0; i<length; i++)
  {
    sum += data[i];
  }
  return sum;
}

const input = [];
require('readline')
  .createInterface(process.stdin, {})
  .on('line', function(line) {
    input.push(line.trim());
  })
  .on('close', function() {
    const length = parseInt(input[0]);
    const data = input[1].split(" ").map(Number);
    const answer = get_sum(data, length);
		console.log(answer);
	});