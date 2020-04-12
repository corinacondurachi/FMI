l=input().split()
n=int(l[0])
d={}
for i in range(n):
    l=input().split()
    x=int(l[0])
    y=int(l[1])
    z=int(l[2])
    if (x,y) not in d:
        d[(x,y)]=(z,0)
    else:
        if d[(x,y)][1]==0:
            a=d[(x,y)][0]
            d[(x,y)]=(a,z)
        else:
            a=d[(x,y)][0]
            b=d[(x,y)][1]
            maxim=max(a,b,z);
            if a==maxim: 
                d[(x,y)]=(maxim,max(b,z))
            if b==maxim: 
                d[(x,y)]=(maxim,max(a,z))
            if z==maxim: 
                d[(x,y)]=(maxim,max(b,a))
    if (x,z) not in d:
        d[(x,z)]=(y,0)
    else:
        if d[(x,z)][1]==0:
            a=d[(x,z)][0]
            d[(x,z)]=(a,y)
        else:
            a=d[(x,z)][0]
            b=d[(x,z)][1]
            maxim=max(a,b,y);
            if a==maxim: 
                d[(x,z)]=(maxim,max(b,y))
            if b==maxim: 
                d[(x,z)]=(maxim,max(a,y))
            if z==maxim: 
                d[(x,z)]=(maxim,max(b,a))
    if (y,z) not in d:
        d[(y,z)]=(x,0)
    else:
        if d[(y,z)][1]==0:
            a=d[(y,z)][0]
            d[(y,z)]=(a,x)
        else:
            a=d[(y,z)][0]
            b=d[(y,z)][1]
            maxim=max(a,b,x);
            if a==maxim: 
                d[(y,z)]=(maxim,max(b,x))
            if b==maxim: 
                d[(y,z)]=(maxim,max(a,x))
            if x==maxim: 
                d[(y,z)]=(maxim,max(b,a))
print(d)
v_max=0
for i in d:
    a,b=d[i]
    v=i[0]*i[1]*(a+b)
    if v>v_max:
        v_max=v
print(v_max)

    
    
    