v=[(7,3),(8,3),(10,2),(9,2),(10,1),(8,1),(5,2),(6,2),(8,4)]
v.sort(key=lambda tup: tup[0],reverse=True)  
# print(v)
n=len(v)
h=[0]*len(v)
succ=[n]*len(v)
nr=[0]*len(v)
poz_max=n-1
for i in range (n):
    h[i]=v[i][0]
    
for i in range(n-2,-1,-1):
    maxim=0
    for j in range(i+1,n):
        if v[i][0]>v[j][0] and v[i][1]!=v[j][1] and h[j]>maxim:
            maxim=h[j]
            poz=j
    if maxim!=0:
        h[i]+=maxim
        maxim=h[i]
        succ[i]=poz
    if h[i]>h[poz_max]:
        poz_max=i
#     print(h)
#     print(maxim)
    for j in range(i+1,n):
        if nr[j]==0:
            nr[j]=1
        if v[i][0]+h[j]==maxim and v[i][1]!=v[j][1]:
            nr[i]+=nr[j]

            
print('inaltimea maxima este',max(h))
k=poz_max
while k<n:
    print(v[k])
    k=succ[k]
print('exista',nr[0],'solutii')