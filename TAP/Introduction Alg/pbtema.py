import math
l=[]
with open ("f1.txt", "r") as myfile:
    data=myfile.readlines()
    x=data[0].split()
    for i in x:
        l.append(i)
with open ("f2.txt", "r") as myfile:
    data=myfile.readlines()
    x=data[0].split()
    for i in x:
        l.append(i)
print(l)
l = list(dict.fromkeys(l))
print(l)
v1 = []
v2=[]
k=0
for i in range(0,len(l)):
    v1.append(0)
    v2.append(0)
for i in l:
    with open ("f1.txt", "r") as myfile:
        data=myfile.readlines()
        x=data[0].split()
        for j in x:
            if j==i:
                v1[k]+=1
    k+=1
k=0
for i in l:
    with open ("f2.txt", "r") as myfile:
        data=myfile.readlines()
        x=data[0].split()
        for j in x:
            if j==i:
                v2[k]+=1
    k+=1
    
print(v1)
print(v2)
s=0
nr1=0
nr2=0
for i in range(0,len(v1)):
    s=s+v1[i]*v2[i]
    nr1+=v1[i]*v1[i]
    nr2+=v2[i]*v2[i]
    
if nr1*nr2!=0:
    dcos=s/math.sqrt(nr1*nr2)
else:
    print("nu se poate imparti la 0")
print(dcos)  


from collections import defaultdict
import math
d={}
d = defaultdict(lambda: (0,0))
with open ("f1.txt", "r") as myfile:
    data=myfile.readlines()
    x=data[0].split()
    for i in x:
        a,b=d[i]
        a+=1
        d[i]=(a,b)
with open ("f2.txt", "r") as myfile:
    data=myfile.readlines()
    x=data[0].split()
    for i in x:
        a,b=d[i]
        b+=1
        d[i]=(a,b)
print(d)
s=0
nr1=0
nr2=0
for i in d:
    a,b=d[i]
    s=s+a*b
    nr1+=a*a
    nr2+=b*b
    
if nr1*nr2!=0:
    dcos=s/math.sqrt(nr1*nr2)
else:
    print("nu se poate imparti la 0")
print(dcos)                

              