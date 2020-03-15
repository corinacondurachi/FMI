#include <iostream>
#include<fstream>
#include<vector>
using namespace std;
ifstream f("dfs.in");
ofstream g("dfs.out");
int viz[100005];
vector<int> graph[100005];
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
}
int main()
{
    int n,m,i,answear=0,a,b;
    f>>n>>m;
    for( i=1;i<=m;i++)
    {
        f>>a>>b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    for(i=1;i<=n;i++)
        if(!viz[i])
    {
        answear++;
        DFS(i);
    }
    g<<answear;
    return 0;
}
