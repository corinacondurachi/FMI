def partition(a,begin,end):
    pivot=a[begin][0]
    while begin<=end:
        while a[begin][0]<pivot:
            begin+=1
        while a[end][0]>pivot:
            end-=1
        a[begin],a[end]=a[end],a[begin]
        if pivot==a[begin][0]:
            end-=1
        else:
            begin+=1
    return begin
v=[[5,0.1],[1,0.12],[3,0.05],[2,0.1],[9,0.2],[6,0.13],[11,0.3]]

def mediana(k,v):
    s1,s2=0,0
    poz=partition(v,k,len(v)-1)
    print(v)
    #daca inseram pe ult poz ne intoarcem la al doilea element ca sa verificam daca este bun
    if poz==len(v)-1:
        poz=1
    if len(v)>3:
        for i in range(poz):
            s1=s1+v[i][1]
        for i in range(poz+1,len(v)):
            s2+=v[i][1]
        if s1<0.5 and s2<=0.5:
            return v[poz][0]
        if s2>0.5:
            v[poz][1]+=s1
            v=v[poz:len(v)]
        if s1>=0.5:
            v[poz][1]+=s2
            v=v[0:poz+1]
        k+=1
        return mediana(k,v)
    else:
        if v[0][1]<0.5 and v[2][1]<=0.5:
            return v[1][0]
        

mediana(0,v)
