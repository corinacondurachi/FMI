#include <iostream>
#include<fstream>
#include<vector>
using namespace std;
ifstream f("sortaret.in");
ofstream g("sortaret.out");
vector<int> graph[100005];
int viz[100005];
void DFS(int node)
{
    viz[node]=1;
    int lim=graph[node].size();
    for(int i=0;i<lim;i++)
    {
        int vecin=graph[node][i];
        if(!viz[vecin])
            DFS(vecin);
    }
    g<<node<<" ";
}
int main()
{
    int m,n,x,y,i;
    f>>n>>m;
    for(i=1;i<=m;i++)
    {
        f>>x>>y;
        graph[y].push_back(x);
    }
    for(i=1;i<=n;i++)
    {
        if(!viz[i])
          DFS(i);
    }
    return 0;
}
