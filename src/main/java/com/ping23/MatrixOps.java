package com.ping23;

public class MatrixOps {

	// non-instantiable
	private MatrixOps() {
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] matrixA = { { 1, 2 }, { 3, 4 }, { 5, 6 }, };
		int[][] matrixB = { { 1, 2, 3 }, { 4, 5, 6 } };
		int[][] matrixC = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		printMatrix(matrixA, "matrixA");
		printMatrix(matrixB, "matrixB");
		// printMatrix(matrixC, "matrixC");
		// canMultiply(matrixA, "matrixA", matrixB, "matrixB");
		// canMultiply(matrixA, "matrixA", matrixC, "matrixC");
		int[][] matrixQ = doMultiply(matrixA, "matrixA", matrixB, "matrixB");
		if (matrixQ != null) {
			printMatrix(matrixQ, "matrixQ");
		}
		else {
			System.out.println("matrix multiplication failed.");
		}

	} // main()
	

	// multiply the given matrices, or return null if not valid
	private static int[][] doMultiply(int[][] matrixY, String matrixYName, int[][] matrixZ, String matrixZName) {
		if (!canMultiply(matrixY, matrixYName, matrixZ, matrixZName)) {
			return null;
		}

		// establish the matrix dimensions
		int mDim = matrixY.length;
		int nDim = matrixY[0].length;
		int oDim = matrixZ[0].length;

		// create the return matrix
		int[][] matrixR = new int[mDim][oDim];

		// iterate over rows and columns
		for (int m = 0; m < mDim; m++) {
			for (int o = 0; o < oDim; o++) {
				for (int n = 0; n < nDim; n++)
				{
					matrixR[m][o] += matrixY[m][n] * matrixZ[n][o];
				}
			}
		}

		return matrixR;
	}

	// verify that the matrices can be multiplied
	private static boolean canMultiply(int[][] matrixY, String matrixYName, int[][] matrixZ, String matrixZName) {
		boolean result = false;

		int matrixYCols = matrixY[0].length;
		int matrixZRows = matrixZ.length;

		if (matrixYCols == matrixZRows) {
			result = true;
			System.out.println("matrix " + matrixYName + " and matrix " + matrixZName + " can be multiplied.");
		} else {
			System.out.println("matrix " + matrixYName + " and matrix " + matrixZName + " can NOT be multiplied.");
		}

		return result;
	}

	// print the given matrix
	private static void printMatrix(int[][] matrixZ, String matrixZName) {
		System.out.println("printing " + matrixZName);

		for (int m = 0; m < matrixZ.length; m++) {
			for (int n = 0; n < matrixZ[0].length; n++) {
				System.out.print(matrixZ[m][n] + " ");
			}
			System.out.print("\n");

		}

	} // printMatrix()

}
