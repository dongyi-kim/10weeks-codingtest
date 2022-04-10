function selection_sort(data, length)
{
  for(var i = 0 ; i < data.length ; i++ )
  {
    for (var j = i+1 ; j < data.length ; j++ )
    {
      if ( data[i] > data[j] ) {
        var temp = data[i];
        data[i] = data[j];
        data[j] = temp;
      }
    }
  }
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
    var answer = "";

    selection_sort(data, n);

    for (var i=0; i<n; i++)
      answer += data[i] + " ";
		console.log(answer);
	});