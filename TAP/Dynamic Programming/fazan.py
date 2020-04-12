import string
L = {}
pred={}
v=['masa', 'carte', 'sac','teatru', 'tema', 'rustic', 'sare']
n=len(v)
L[v[n-1][0:2]]=1
pred[v[n-1][0:2]]=v[n-1]
maxim=0
succ=[n]*len(v)
for i in range(n-1,-1,-1):
    if v[i][0:2] not in L.keys():
        L[v[i][0:2]]=0
    if v[i][-2:] not in L.keys():
        L[v[i][-2:]]=0
    if L[v[i][0:2]]<L[v[i][-2:]]+1:
        L[v[i][0:2]]=L[v[i][-2:]]+1
        pred[v[i][0:2]]=v[i]
    if L[v[i][0:2]]>maxim:
        maxim=L[v[i][0:2]]
        cuv=v[i]
        
print(L)
print(pred)
print("Lungimea maxima este",maxim)
print(cuv)
u=cuv[-2:]
maxim-=1
while maxim>0:
    print(pred[u])
    u=pred[u][-2:]
    maxim-=1
