## 행렬의 곱셈

##print([[0] * 3 for _ in range(3)])

def matrix_multi(A, B):
    n = len(A)
##    list comprehension
    C = [ [0] * n for _ in range(n) ]
    for i in range(n):
        for j in range(n):
            for k in range(n):
                C[i][j] += A[i][k] * B[k][j]
    return C
                
A = [[2, 3], [4, 1]]
B = [[5, 7], [6, 8]]
C = matrix_multi(A, B)
print(f'C = {C}')
