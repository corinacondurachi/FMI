m=3 
n=3
M=[[2, 1, 4],[1,3,2],[1,6,1]]
C=[[0 for j in range(m)] for i in range(n)]
P=[[(0,0) for j in range(m)] for i in range(n)]

C[0][0]=M[0][0]
for i in range(1,m):
    C[0][i]=C[0][i-1]+M[0][i]
    P[0][i]=(0,i-1)
for i in range(1,n):
    C[i][0]=C[i-1][0]+M[i][0]
    P[i][0]=(i-1,0)

for i in range(1,m):
    for j in range(1,n):
        C[i][j]=M[i][j]+max(C[i-1][j],C[i][j-1])
        if max(C[i-1][j],C[i][j-1])==C[i-1][j]:
            P[i][j]=(i-1,j)
        else:
            P[i][j]=(i,j-1)
        
imax,jmax=0,0
maxim=0
for i in range(m):
    for j in range(n):
        if C[i][j]>maxim:
            maxim=C[i][j]
            imax=i
            jmax=j
print(maxim)

# for i in range(m):
#     print(P[i])

imax2,jmax2=0,0  
l=[]
while imax>0 or jmax>0:
    l.append(tuple([imax+1,jmax+1]))
    imax2=P[imax][jmax][0]
    jmax2=P[imax][jmax][1]
    imax=imax2
    jmax=jmax2
    
l.append(tuple([imax+1,jmax+1]))
l.reverse()
print(l)