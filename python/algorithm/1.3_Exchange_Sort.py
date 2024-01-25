# 교환 정렬

def exchange(S):
    n = len(S)
    for i in range(n - 1): # 마지막 원소 직전까지
        for j in range(i + 1, n):
            if (S[i] > S[j]):
                S[i], S[j] = S[j], S[i] # swap
            print(S)

S = [-1, 10, 7, 11, 5, 13, 8]
print(f'Before : {S}')
exchange(S)
print(f'After : {S}')
