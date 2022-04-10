const input = [];
require('readline')
  .createInterface(process.stdin, {})
  .on('line', function(line) {
    input.push(line.trim());
  })
  .on('close', function() {
    const n = parseInt(input[0]);
    var first_ajou = -1;
    var last_ajou = -1;
    for (var i=1; i<n+1; i++)
    {
      if (input[i] == "AJOU") {
        if (first_ajou == -1) {
          first_ajou = i;
        }
        last_ajou = i;
      }
    }

		console.log(first_ajou + " " + last_ajou);
	});