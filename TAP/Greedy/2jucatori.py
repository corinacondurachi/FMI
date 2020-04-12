l=[]
k=input().split()
n=int(k[0])
line=input().split()
for i in range(n):
    l.append(line[i])
si=0
sp=0
unu=[]
doi=[]
for i in range(0,len(l)-1,2):
    si=si+int(l[i])
    unu.append(l[i])
    sp=sp+int(l[i+1])
    doi.append(l[i+1])
if si>sp:
    print(si)
    print(sp)
    print(unu)
    print(doi)
else:
    print(sp)
    print(si)
    print(doi)
    print(unu)