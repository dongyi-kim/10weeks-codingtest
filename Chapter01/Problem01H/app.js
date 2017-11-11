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
    const n = parseInt(input[0]);
    const data = input[1].split(" ").map(Number);
    var index = -1, answer = 0, average = 0, sum = 0;
    var minGap = Number.MAX_SAFE_INTEGER;
    for (var i=0; i<n; i++)
    {
      sum += data[i];
    }
    average = sum / n;
    for (var i=0; i<n; i++)
    {
      var gap = Math.abs(average - data[i]);
      if (gap < minGap) {
        minGap = gap;
        index = i+1;
        answer = data[i];
      }
    }
		console.log(index + " " + answer);
	});