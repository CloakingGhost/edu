# 순차탐색

# n: 0인덱스 제외 배열의 길이
# S: 배열(정렬 안됨)
# x: 타켓 넘버
def seqsearch(n, S, x):
    location = 1
    while (location <= n and S[location] != x):
        location += 1
    # while문에서 끝까지 조회하면 location == n + 1이다
    # 즉 배열의 x가 없음을 의미한다
    if (location > n): 
        location = 0
    return location
####
S = [0, 10, 7, 11, 5, 13, 8]
x = 5
location = seqsearch(len(S) - 1, S, x)
print(f'location = {location}')

x = 4
location = seqsearch(len(S) - 1, S, x)
print(f'location = {location}')
