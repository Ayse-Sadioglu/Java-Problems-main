You are given two arrays of nonnegative integers, R[1..n] and C[1..n], such that the sum of
numbers in R and the sum of numbers in C are equal. Using these arrays, you are asked to
fill an n ×n matrix M as follows: if R[i] > 0, you are required to place R[i] many 1’s in the i-th
row of M. The rest of the entries in the i-th row will be 0’s. If C[j] > 0, you are required to
place C[j] many 1’s in the j-th column of M, and the rest of the entries will be 0’s. Give an
O(n
2
) algorithm that determines if such a placement is possible for the given input arrays R
and C, and if it is, outputs the row and column numbers of the entries that are 1’s.
Complete the method whose signature is public int [][] matrixConstruction(int [ ] R, int [] C)
in Java.