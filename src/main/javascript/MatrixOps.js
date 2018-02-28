/**
 * Matrix Ops
 */

main();

function main() {
	let matrixA = [ [ 1, 2 ], [ 3, 4 ], [ 5, 6 ] ];
	let matrixB = [ [ 1, 2, 3 ], [ 4, 5, 6 ] ];
	let matrixC = [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ];

	printMatrix(matrixA, "matrixA");
	canMultiply(matrixA, "matrixA", matrixB, "matrixB");
	canMultiply(matrixA, "matrixA", matrixC, "matrixC");
	
	let matrixX = doMultiply(matrixA, "matrixA", matrixB, "matrixB");
	printMatrix(matrixX, "matrixX");
}

// doMultiply()
function doMultiply(matrixS, matrixSName, matrixT, matrixTName) {

	if (!canMultiply(matrixS, matrixSName, matrixT, matrixTName)) {
		return -1;
	}

	let mDim = matrixS.length;
	let nDim = matrixS[0].length;
	let oDim = matrixT[0].length;
	
	//let matrixR = new Array(mDim);
	let matrixR = [];
	
	for (let m = 0; m < mDim; m++) {
		//matrixR[m] = new Array(oDim);
		matrixR.push([]);
		for (let o = 0; o < oDim; o++) {
			matrixR[m][o] = 0;
			for (let n = 0; n < nDim; n++) {
				matrixR[m][o] += matrixS[m][n] * matrixT[n][o];
			}
				
		}
	}
		
	return matrixR;

} // end doMultiply()

// canMultiply()
// matrixA[m][n] * matrixB [n][o] can be multiplied iff n==n
function canMultiply(matrixS, matrixSName, matrixT, matrixTName) {

	let n = matrixS[0].length;
	let o = matrixT.length;

	let canMultiply = n === o ? true : false;
	console.log("canMultiply " + matrixSName + " * " + matrixTName + ": "
			+ canMultiply);
	return canMultiply;
} // end canMultiply()

// printMatrix()
function printMatrix(matrixP, matrixPName) {
	console.log("printing " + matrixPName);
	let rows = matrixP.length;
	let cols = matrixP[0].length;

	for (row = 0; row < rows; row++) {
		let outLine = "";
		for (col = 0; col < cols; col++) {
			outLine += matrixP[row][col];
			if (col < (cols - 1)) {
				outLine += " ";
			}
		}
		console.log(outLine);
	}
} // end printMatrix()
