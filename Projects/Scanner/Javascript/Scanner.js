const fs = require('fs');
const input = (()=>{
  //콘솔에서 입력할시에는 모든 입력 후 Ctrl+D 누르면 사용 가능
  //stdin = ["1 23 456","789 1011 121314"] 의 형식임  
	const stdin = fs.readFileSync('/dev/stdin').toString().split('\n');
	let ln=0;
  //stdin[ln++] (ln = 현재 내가 읽고있는 라인 넘버)
	return ()=>stdin[ln++];
})();

//console.log(input()) 의 결과 : "1 23 456"
//배열로 바꾸고싶은 경우 : console.log(input().split("")) ["1","23","456"]