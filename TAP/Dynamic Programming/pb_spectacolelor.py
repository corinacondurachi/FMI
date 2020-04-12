v=[(1, 3, 1), (2, 6, 8), (4, 7, 2), (10, 11, 5)]
v.sort(key=lambda tup: tup[1])  
n=len(v)
profit=[0]*len(v)
succ=[n]*len(v)

poz_max=n-1
for i in range (n):
    profit[i]=v[i][2]
for i in range(n-2,-1,-1):
    maxim=0
    for j in range(i+1,n):
        if v[i][1]<v[j][0] and profit[j]>maxim:
            maxim=profit[j]
            poz=j
    if maxim!=0:
        profit[i]+=maxim
        succ[i]=poz
    if profit[i]>profit[poz_max]:
        poz_max=i
print('profitul maxim este',max(profit))
k=poz_max
while k<n:
    print(v[k])
    k=succ[k]