l=[]
k=input().split()
n=int(k[0])
line=input().split()
for i in range(n):
    l.append(int(line[i]))
print(l)

def binary_search(item_list,item):
    first = 0
    last = len(item_list)-1
    found=False
    mid=-1
    while( first<=last and not found):
        mid = (first + last)//2
        if mid==0:
            if item_list[mid]>int(item):
                found = True
        if mid==last:
            if item_list[mid]<int(item):
                found=True
        if item_list[mid]>int(item) and item_list[mid-1]<int(item) :
            found = True
        else:
            if int(item) < item_list[mid]:
                last = mid - 1
            else:
                first = mid + 1	
    return mid   

v=[1000]*n
sol=[[] for _ in range(n)]
v[0]=l[0]
sol[0]=[l[0]]
l.pop(0)
# print(sol)
# print(v) 
for i in range(len(l)):
    poz=binary_search(v,l[i])
#     print(poz)
    sol[poz].append(l[i])
    v[poz]=l[i]
#     print(v)
#     print(sol)
sol = [x for x in sol if x != []]
print(len(sol))
print(sol)