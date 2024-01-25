## 배열 원소의 합

# 배열의 0인덱스는 올바른 값이 나오지 않는 상황에 사용 됨
# n: 배열의 길이
# S: 배열
def sum(n, S):
    result = 0
    for i in range(1, n + 1):
        result += S[i]
    return result

S = [-1, 10, 7, 11, 5, 13, 8]
sum = sum(len(S) - 1, S)
print(f'sum = {sum}')
    
