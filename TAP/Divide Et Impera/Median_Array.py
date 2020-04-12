v1=[1,3,8,9,15]
v2=[7,11,18,19,21,25]
def mediana(v1,v2):
    m=len(v1)
    n=len(v2)
    if m>n:
        m,n,v1,v2=n,m,v2,v1
    start, end, half_len = 0, m, (m + n + 1) // 2
    while start <= end:
        poz_x = (start + end) // 2
        poz_y = half_len - poz_x
        if poz_x < m and v2[poz_y-1] > v1[poz_x]:
            start = poz_x + 1
        elif poz_x > 0 and v1[poz_x-1] > v2[poz_y]:
            end = poz_y - 1
        else:
            if poz_x == 0: max_of_left = v2[poz_y-1]
            elif poz_y == 0: max_of_left = v1[poz_x-1]
            else: max_of_left = max(v1[poz_x-1], v2[poz_y-1])
            if (m + n) % 2 == 1:
                return max_of_left
            if poz_x == m: min_of_right = v2[poz_y]
            elif poz_y == n: min_of_right = v1[poz_x]
            else: min_of_right = min(v1[poz_x], v2[poz_y])
            return (max_of_left + min_of_right) / 2.0
print(mediana(v1,v2))