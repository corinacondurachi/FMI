l=[]
k=input().split()
n=int(k[0])
l=[None]*n
for i in range(n):
    k=input().split()
    n1=int(k[0])
    n2=int(k[1])
    l[i]=(n1,n2)
print(l)
l.sort(key=lambda tup: tup[0],reverse=True)
print(l)

def find(nod):
    if tata[nod]==nod:
        return nod
    else:
        return find(tata[nod])

def union(x,y):
        if rang[x]<rang[y]:
            tata[x]=y
            rang[y]+=rang[x]
        else:
            tata[y]=x
            rang[x]+=rang[y]
    

tata=[]
rang=[]
profit=0
tata=[None]*(n)
aux=[None]*(n)
rang=[0]*(n)
for i in range(n):
    tata[i]=i
    aux[i]=i-1
for i in range(n):
    a=l[i]
    f=find(a[1])
    if aux[f]==-1:
        continue
    union(aux[f],f)
    aux[f]=aux[aux[f]]
    profit+=a[0]
    

print(profit)




