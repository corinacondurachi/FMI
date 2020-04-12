import random
import struct

dim=20
a=-1
b=2
precizia=6
prob_recomb=0.25
prob_mutatie=0.01
nr_etape=50


def float_to_bin(num):
    return format(struct.unpack('!I', struct.pack('!f', num))[0], '032b')

def bin_to_float(binary):
    return struct.unpack('!f',struct.pack('!I', int(binary, 2)))[0]

def functie(x):
    return -x*x+x+2

def binary_search(item_list,item):
    first = 0
    last = len(item_list)-1
    found=False
    mid=-1
    while( first<=last and not found):
        mid = (first + last)//2
        if mid==0:
            if item_list[mid]>float(item):
                found = True
        if mid==last:
            if item_list[mid]<float(item):
                found=True
        if item_list[mid]>float(item) and item_list[mid-1]<float(item) :
            found = True
        else:
            if float(item) < item_list[mid]:
                last = mid - 1
            else:
                first = mid + 1	
    return mid 

def change_nth_bit(x,n):
    if x[n]=='0':
        a=x[0:n]+'1'+x[n+1:]
    else:
        a=x[0:n]+'0'+x[n+1:]
    return a

f = open(r"Evolutie.txt","w+")

print("Populatia initiala")
print("Populatia initiala",file=f)

s=0
val=[]
pop_init=[]

for i in range(dim):
    print(i+1,end='')
    x=round(random.uniform(a,b),precizia)
    pop_init.append(x)
    print(':',float_to_bin(x),'x=',x,'f=',functie(x))
    print(i+1,end='',file=f)
    print(':',float_to_bin(x),'x=',x,'f=',functie(x),file=f)
    s+=functie(x)
    val.append(functie(x))

print('\n')
print("Probabilitati selectie")
print('\n',file=f)
print("Probabilitati selectie",file=f)

nr=1
interv=[]

for i in val:
    print('cromozom',end='      ')
    print(nr,'probabilitate',end=' ')
    print(i/s)
    print('cromozom',end='      ',file=f)
    print(nr,'probabilitate',end=' ',file=f)
    print(i/s,file=f)
    interv.append(i/s)
    nr+=1



print('\n')
print("Intervale probabilitati selectie")
print('\n',file=f)
print("Intervale probabilitati selectie",file=f)
i=[]
i.append(0)
s=0
for ind in interv:
    s+=ind
    i.append(s)
print(i)
print(i,file=f)

print('\n')
print('\n',file=f)

l=[]
for j in range(dim):
    u=random.uniform(0,1)
    print('u=',u,'  selectam cromozomul',end=' ')
    print(binary_search(i,u))
    print('u=',u,'  selectam cromozomul',end=' ',file=f)
    nr=binary_search(i,u)
    print(nr,file=f)
    l.append(nr)

print('\n')
print("Dupa selectie")
print('\n',file=f)
print("Dupa selectie",file=f) 
dupa_sel=[]

for j in range(dim):
    print(j+1,end='')
    x=pop_init[l[j]-1]
    dupa_sel.append(x)
    print(':',float_to_bin(x),'x=',x,'f=',functie(x))
    print(j+1,end='',file=f)
    print(':',float_to_bin(x),'x=',x,'f=',functie(x),file=f)
    
print('\n')
print("Probabilitatea de incrucisare",prob_recomb)
print('\n',file=f)
print("Probabilitatea de incrucisare",prob_recomb,file=f)
v=[]
for j in range(dim):
    print(j+1,end='')
    print(j+1,end='',file=f)
    u=random.uniform(0,1)
    print(':',float_to_bin(u),'u=',u,end=' ')
    print(':',float_to_bin(u),'u=',u,end=' ',file=f)
    if u<prob_recomb:
        print('<0.25 participa')
        print('<0.25 participa',file=f)
        v.append((j+1,u))
    else:
        print('')
        print('',file=f)

        
w=[]
print('\n')
print('\n',file=f)

while(len(v)>1):
    ind1=random.choice(v)
    v.remove(ind1)
    ind2=random.choice(v)
    v.remove(ind2)
    print('Recombinare dintre cromozomul', ind1[0], 'cu cromozomul', ind2[0],':')
    print('Recombinare dintre cromozomul', ind1[0], 'cu cromozomul', ind2[0],':',file=f)
    p=random.randrange(0,32)
    b1=float_to_bin(ind1[1])
    b2=float_to_bin(ind2[1])
    print(float_to_bin(ind1[1]),float_to_bin(ind2[1]),'punct',p)
    print(float_to_bin(ind1[1]),float_to_bin(ind2[1]),'punct',p,file=f)

    b3=b1[0:p]+b2[p:]
    b4=b2[0:p]+b1[p:]
    w.append((ind1[0],b3))
    w.append((ind2[0],b4))
    print('Rezultat  ',b3,b4)
    print('Rezultat  ',b3,b4,file=f)

if len(v)==1:
    ind1=v[0]
    v.remove(ind1)
    print('Recombinare dintre cromozomul', ind1[0], 'cu cromozomul', ind2[0],':')
    print('Recombinare dintre cromozomul', ind1[0], 'cu cromozomul', ind2[0],':',file=f)

    p=random.randrange(0,32)
    print(float_to_bin(ind1[1]),float_to_bin(ind2[1]),'punct',p)
    print(float_to_bin(ind1[1]),float_to_bin(ind2[1]),'punct',p,file=f)

    b3=b1[0:p]+b2[p:]
    b4=b2[0:p]+b1[p:]
    w.append((ind1[0],b3))
    w.append((ind2[0],b4))
    print('Rezultat  ',b3,b4)
    print('Rezultat  ',b3,b4,file=f)


w.sort(key=lambda tup: tup[0]) 

print('\n')
print("Dupa recombinare")
print('\n',file=f)
print("Dupa recombinare",file=f)

dupa_recomb=[]
for j in range(dim):
    print(j+1,end='')
    print(j+1,end='',file=f)
    x=dupa_sel[j]
    if len(w)>0 and j+1==w[0][0] :
            x=w[0][1]
            print(':',x,'x=',round(bin_to_float(x),precizia),'f=',functie(bin_to_float(x)))
            print(':',x,'x=',round(bin_to_float(x),precizia),'f=',functie(bin_to_float(x)),file=f)
            w.pop(0)
            dupa_recomb.append(x)
    else:
        print(':',float_to_bin(x),'x=',x,'f=',functie(x))
        print(':',float_to_bin(x),'x=',x,'f=',functie(x),file=f)
        dupa_recomb.append(float_to_bin(x))

print('\n')
print('Probabilitate de mutatie pentru fiecare gena', prob_mutatie)
print('Au fost modificati cromozomii:',end=' ')
 
print('\n',file=f)
print('Probabilitate de mutatie pentru fiecare gena', prob_mutatie,file=f)
print('Au fost modificati cromozomii:',end=' ',file=f)

crom=[]
for i in range(dim):
    for k in range (len(dupa_recomb[0])):
        u=random.uniform(0,1)
        if u<prob_mutatie:
            crom.append(i)
            x=change_nth_bit(dupa_recomb[i],k)
            dupa_recomb[i]=x
        
print(crom) 
print(crom,file=f)

print('\n')
print("Dupa mutatie")
print('\n',file=f)
print("Dupa mutatie",file=f)
maxim=0
s=0
for j in range(dim):
    print(j+1,end='')
    print(j+1,end='',file=f)
    x=dupa_recomb[j]
    print(':', x,'x=',round(bin_to_float(x),precizia),'f=',functie(bin_to_float(x)))
    print(':', x,'x=',round(bin_to_float(x),precizia),'f=',functie(bin_to_float(x)),file=f)
    if functie(bin_to_float(x))>maxim:
               maxim=functie(bin_to_float(x))
    s+=functie(bin_to_float(x))

print('\n')
print("Evolutia maximului")
print('\n',file=f)
print("Evolutia maximului",file=f)
print('maximul este', maxim)
# print('valoarea medie a performan?ei este', s/dim)

def maxim_functie(dim=20,a=-1,b=2,precizia=6,prob_recomb=0.25,prob_mutatie=0.01):
    s=0
    val=[]
    pop_init=[]

    for i in range(dim):
        x=round(random.uniform(a,b),precizia)
        pop_init.append(x)
        s+=functie(x)
        val.append(functie(x))
    nr=1
    interv=[]

    for i in val:
        interv.append(i/s)
        nr+=1
        
    i=[]
    i.append(0)
    s=0
    for ind in interv:
        s+=ind
        i.append(s)
        
    l=[]
    for j in range(dim):
        u=random.uniform(0,1)
        nr=binary_search(i,u)
        l.append(nr)


    dupa_sel=[]

    for j in range(dim):
        x=pop_init[l[j]-1]
        dupa_sel.append(x)

    v=[]
    for j in range(dim):
        u=random.uniform(0,1)
        if u<prob_recomb:
            v.append((j+1,u))


    w=[]
    while(len(v)>1):
        ind1=random.choice(v)
        v.remove(ind1)
        ind2=random.choice(v)
        v.remove(ind2)
        p=random.randrange(0,32)
        b1=float_to_bin(ind1[1])
        b2=float_to_bin(ind2[1])
        b3=b1[0:p]+b2[p:]
        b4=b2[0:p]+b1[p:]
        w.append((ind1[0],b3))
        w.append((ind2[0],b4))

    if len(v)==1:
        ind1=v[0]
        v.remove(ind1)
        p=random.randrange(0,32)
        b3=b1[0:p]+b2[p:]
        b4=b2[0:p]+b1[p:]
        w.append((ind1[0],b3))
        w.append((ind2[0],b4))

    w.sort(key=lambda tup: tup[0]) 


    dupa_recomb=[]
    for j in range(dim):
        x=dupa_sel[j]
        if len(w)>0 and j+1==w[0][0] :
                x=w[0][1]
                w.pop(0)
                dupa_recomb.append(x)
        else:
            dupa_recomb.append(float_to_bin(x))

    crom=[]
    for i in range(dim):
        for k in range (len(dupa_recomb[0])):
            u=random.uniform(0,1)
            if u<prob_mutatie:
                crom.append(i)
                x=change_nth_bit(dupa_recomb[i],k)
                dupa_recomb[i]=x

    maxim=0
    suma=0.0
    for j in range(dim):
        x=dupa_recomb[j]
        if functie(bin_to_float(x))>maxim:
                   maxim=functie(bin_to_float(x))
        suma+=round(functie(bin_to_float(x)),precizia)
        
    print('maximul este', maxim)
    print('maximul este', maxim,file=f)
#     print('valoarea medie a performan?ei este', suma/dim)

for contor in range(1,nr_etape):
    maxim_functie(dim,a,b,precizia,prob_recomb,prob_mutatie)

    