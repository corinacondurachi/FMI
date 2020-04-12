def find(n,a,b,k):

    if n==1:
        return k+a;
    
    #primul chenar
    if a<=n/2 and b<=n/2:
        return find(n/2,a,b,k)
    
    #al doilea chenar
    if a<=n/2 and b>n/2:
        k=(n/2)* (n/2)+k;
        return find(n/2, a, b-n/2,k)
    
    if a>n/2 and b<=n/2:
        k=(n/2)* (n/2) *2 +k
        return find(n/2, a-n/2, b, k)
    
    if a>n/2 and b>n/2:
        k=(n/2) * (n/2) * 3 +k;
        return find(n/2, a-n/2, b-n/2, k)
    
    
l=input().split()
n=int(l[0])
l=input().split()
i,j=int(l[0]),int(l[1])
print(find(pow(2,n),i,j,0))