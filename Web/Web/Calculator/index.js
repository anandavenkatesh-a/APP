let expr = ["0"];
let disp = ["zero"];

const chars = new Map([
  ["1", "one"],
  ["2", "two"],
  ["3", "three"],
  ["4", "four"],
  ["5", "five"],
  ["6", "six"],
  ["7", "seven"],
  ["8", "eight"],
  ["9", "nine"],
  ["0", "zero"],
  ["*", "times"],
  ["+", "plus"],
  ["-", "minus"],
  [".", "point"],
  ["/", "by"],
]);

let res = document.getElementById("result");
res.value = getStringFromArray(disp);

function getStringFromArray(thing){
	let out = "";
	for (let j = 0; j < thing.length; j++){
		out += thing[j] + " ";
	}
	out = out.trim();
	return out;
}

document.getElementById("equal").addEventListener('click', () => {
		let result = getStringFromArray(expr).replaceAll(" ", "");
		console.log(result);	
		let ans = String(eval(result).toFixed(2));
		if (ans == "Infinity"){
			alert("Division by zero");
			expr = ["0"];
			disp = ["zero"];
			res.value = getStringFromArray(disp);
		} else {
		
		let dis = [];
		for (let char of ans){
			console.log(char);
			dis.push(chars.get(String(char)));
		}

		let ind = ans.indexOf(".");
		if (ind > -1)
		{
  			let j = dis.length - 1;
			while (dis[j] == "zero"){
				dis.pop();
				j --;
			}
			if (dis[j] == "point"){
				dis.pop();
			}
		}

		res.value = getStringFromArray(dis);
}
});

document.getElementById("AC").addEventListener('click', () => {
	expr = ["0"];
	disp = ["zero"];
	res.value = getStringFromArray(disp);
});

document.getElementById("CE").addEventListener('click', () => {
	expr.pop();
	disp.pop();
	res.value = getStringFromArray(disp);
});

document.querySelectorAll('input[type="button"]').forEach(button => {
    if (button.value !== "=" && button.value !== "AC" && button.value !== "CE") {
        button.addEventListener('click', () => {
            if (disp[0] === "zero" && disp.length === 1) {
                disp = [chars.get(button.value)];
                expr = [button.value];
            } else {
                disp.push(chars.get(button.value));
                expr.push(button.value);
            }
            res.value = getStringFromArray(disp);
        });
    }
});
