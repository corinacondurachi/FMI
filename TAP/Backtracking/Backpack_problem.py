class Object:
    def __init__(self, id, cost, weight,cw):
        self.id = id
        self.cost = cost
        self.weight = weight
        self.cw=cw
    def __repr__(self):
        return "object " + str(self.id) + ":(" + str(self.cost) + " " + str(self.weight) + " " + str(self.cw) +")"

class Node:
    def __init__(self, level, cd, cf,weight):
        self.level = level
        self.cd = cd
        self.cf=cf
        self.weight = weight
    
n, G = 5, 30
object = [None] * n
costs = [15,50,80,45,10]
weights = [2.5,10,20,15,5]
cw=[None]*n
for i in range(n):
    cw[i]=costs[i]/weights[i]
    
for i in range(n):
    object[i] = Object(i+1, float(costs[i]), float(weights[i]),float(cw[i]))
    
object =sorted(object,key=lambda t:t.cw,reverse=True)
print(object)

def get_cf (u, n, W,arr): 
    
#     // if weight overcomes the knapsack capacity, return 0 as expected bound 
    if u.weight >= W: 
        return 0 
    
#   initialize bound on profit by current profit 
    cf = u.cd
    
#     // start including items from index 1 more to current 
#     // item index 
    j = u.level + 1;
    totweight = u.weight 

#     // checking index condition and knapsack capacity 
    while ((j < n) and (totweight + arr[j].weight <= W)): 
        totweight    += arr[j].weight 
        cf += arr[j].cost 
        j+=1   
        
#     // If k is not n, include last item partially for 
#     // upper bound on profit 
    if (j < n): 
        cf += (W - totweight) * arr[j].cost / arr[j].weight
    return cf; 

def knapsack( W,  arr,  n): 
    
#     // make a queue for traversing the node 
    Q=[] 
    
#     // dummy node at starting 
    u=Node(-1,0,0,0)
    v=Node(-1,0,0,0)
    Q.append(u)
    
#     // One by one extract an item from decision tree 
#     // compute profit of all children of extracted item 
#     // and keep saving maxProfit 
    maxProfit = 0; 
    while (len(Q)!=0): 

#         // Dequeue a node 
        u = Q[0]; 
        Q.pop(); 
        
#         // If it is starting node, assign level 0 
        if (u.level == -1): 
            v.level = 0; 
        
#         // If there is nothing on next level 
        if (u.level == n-1): 
            continue; 
        
#         // Else if not last node, then increment level, 
#         // and compute profit of children nodes. 
        v.level = u.level + 1; 
    
#         // Taking current level's item add current 
#         // level's weight and value to node u's 
#         // weight and value 
        v.weight = u.weight + arr[v.level].weight; 
        v.cd = u.cd + arr[v.level].cost; 
        
#         // If cumulated weight is less than W and 
#         // profit is greater than previous profit, 
#         // update maxprofit 
        if (v.weight <= W and v.cd > maxProfit): 
            maxProfit = v.cd; 
        
#         // Get the upper bound on profit to decide 
#         // whether to add v to Q or not. 
        v.cf = get_cf(v, n, W, arr); 
    
#         // If bound value is greater than profit, 
#         // then only push into queue for further 
#         // consideration 
        if (v.cf > maxProfit): 
            Q.append(v); 
        
#         // Do the same thing,  but Without taking 
#         // the item in knapsack 
        v.weight = u.weight; 
        v.cd = u.cd; 
        v.cf = get_cf(v, n, W, arr); 
        if (v.cf > maxProfit) :
            Q.append(v); 
    return maxProfit; 

print(knapsack(G, object, n))