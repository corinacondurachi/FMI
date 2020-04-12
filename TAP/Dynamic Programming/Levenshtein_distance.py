a = ' carte'
b = ' antet'
c1 = 1
c2 = 2
c3 = 1
n, m = len(a), len(b)
M = [[0 for j in range(m)] for i in range(n)]
M[0] = [j for j in range(m)]

for i in range(1, n):
    M[i][0] = i*c2
    for j in range(1, m):
        if a[i] == b[j]:
            M[i][j] = M[i - 1][j - 1]
        else:
            M[i][j] = min(M[i - 1][j - 1] + c3, M[i - 1][j] + c2, M[i][j - 1] + c1)

# for i in range(0,m):
#     print(M[i])

print('distanta Levenshtein este', M[n - 1][m - 1])

i, j = n - 1, m - 1
l = []
while i >= 0 and j >= 0:
    if a[i] == b[j]:
        l.append('pastram ' + a[i])
        i, j = i - 1, j - 1
    else:
        if M[i][j] == c3 + M[i - 1][j - 1]:
            l.append('inlocuim ' + a[i] + ' - ' + b[j])
            i, j = i - 1, j - 1

        elif M[i][j] == c1 + M[i][j - 1]:
            l.append('inseram ' + b[j])
            j = j - 1

        elif M[i][j] == c2 + M[i - 1][j]:
            l.append('stergem ' + a[i])
            i = i - 1

while i >= 0:
    if M[i][j] == c2 + M[i - 1][j]:
        l.append('stergem ' + a[i])
        i = i - 1

while j >= 0:
    if M[i][j] == c1 + M[i][j - 1]:
        l.append('inseram ' + b[j])
        j = j - 1
l.reverse()
print(l)