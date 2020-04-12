l=[]
k=input().split()
a=int(k[0])
b=int(k[1])
k=input().split()
n=int(k[0])
for i in range(n):
    k=input().split()
    n1=int(k[0])
    n2=int(k[1])
    l.append((n1,n2))
print(l)
l.sort(key=lambda tup: tup[0])
print(l)
maxim=0
i=0
for i in range(n):
    while l[i][0]<=a:
        if l[i][1]>maxim:
            x=l[i][0]
            maxim=l[i][1]
        if i<n-1:
            i+=1
        else:
            break
    if a!=maxim:
        a=maxim
        print(x,a)
if a<b:
    print("nu se poate")
 