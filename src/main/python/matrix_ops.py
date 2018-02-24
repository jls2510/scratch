import random
import sys
import os

matrixA = [[ 1,2 ],[ 3,4 ],[ 5,6 ]]
matrixB = [[ 1,2,3 ],[ 4,5,6 ]]
matrixC = [[ 1, 2, 3 ],[ 4, 5, 6 ],[ 7, 8, 9 ],[ 10, 11, 12 ]]

# multiply the given matrices
def doMultiply(matrixA, matrixAName, matrixB, matrixBName):
    
    # validate
    if not canMultiply(matrixA, matrixAName, matrixB, matrixBName):
        return False
    
    # matrixA dimensions = m x n
    mDim = len(matrixA)
    nDim = len(matrixA[0])
    # matrixB dimensions = n x o
    oDim = len(matrixB[0])
    
    
    # result matrixR dimensions = m x o
    matrixR = [0] * mDim
    print("dimensions of result matrixR:")
    print ("mDim = " + str(mDim))
    print ("oDim = " + str(oDim))
    
    for m in range(mDim):
        matrixR[m] = [0] * oDim
        #print("m = " + str(m))
        for o in range(oDim):
            
            print("o = " + str(o))
            for n in range(nDim):
                #print("n = " + str(n))
                matrixR[m][o] = matrixR[m][o] + (matrixA[m][n] * matrixB[n][o])
            #print("matrixR[" + str(m) + "][" + str(o) + "] = " + str(matrixR[m][o]) )
    
    printMatrix(matrixR, "result: matrixR")
    return matrixR
# end doMultiply()


# return whether we can multiply the two matrices in the given order
def canMultiply(matrixA, matrixAName, matrixB, matrixBName):
    
    printMatrix(matrixA, matrixAName)
    printMatrix(matrixB, matrixBName)
    
    returnValue = False
    matrixACols = len(matrixA[0])
    matrixBRows = len(matrixB)
    if matrixBRows == matrixACols:
        returnValue = True
        print(matrixAName + " and " + matrixBName + " can be multiplied.")
    else:
        print(matrixAName + " and " + matrixBName + " can NOT be multiplied.")
        
    return returnValue
# end canMultiply()
    
    
# print the given matrix in appropriate format    
def printMatrix(aMatrix, aMatrixName):
    
    print("matrix: " + aMatrixName)
    
    rows = len(aMatrix)
    cols = len(aMatrix[0])
    
    row_string = ""
    
    for row in range(rows):
        row_string = ""
        for col in range(cols):
            row_string = row_string + " " + str('%3s' % aMatrix[row][col])
            #print(str(aMatrix[row][col]))
        print(row_string)
# end printMatrix()
            
            
#printMatrix(matrixC, "matrixC")
#canMultiply(matrixA, "matrixA", matrixB, "matrixB")
#canMultiply(matrixA, "matrixA", matrixC, "matrixC")
matrixX = doMultiply(matrixA, "matrixA", matrixB, "matrixB")
#printMatrix(matrixX, "matrixX")
print("matrixX = " + str(matrixX))

    