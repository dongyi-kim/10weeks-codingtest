const input = [];
require('readline')
  .createInterface(process.stdin, {})
  .on('line', function(line) {
    input.push(line.trim());
  })
  .on('close', function() {
    const n = parseInt(input[0]);
    const sum = [0];
		var answer = 0;
    for (var i=1; i<=n; i++)
    {
      sum[i] = sum[i-1] + i;
    }
		for (var i=1; i<=n; i++)
		{
			answer += sum[i];
		}
		console.log(answer);
	});