#include <iostream>
#include<fstream>
#include<vector>
using namespace std;
ifstream f("dag.in");
ofstream g("dag.out");
vector<int> graph[100005];
vector<int> gr[100005];
vector<int> graphC[100005];
int viz[100005],sort_top[100005],dist[100005],tata[100005],v[100005];
int indice;
void DFS(int node)
{
    viz[node]=1;
    int lim=graph[node].size();
    for(int i=0; i<lim; i++)
    {
        int vecin=graph[node][i];
        if(!viz[vecin])
            DFS(vecin);
    }
    indice++;
    sort_top[indice]=node;
}
int main()
{
    int m,n,x,y,i,c,s;
    f>>n>>m;
    f>>s;
    //s=1;
    for(i=1; i<=n; i++)
    {
        dist[i]=100000000;
        tata[i]=0;
    }
    dist[s]=0;
    for(i=1; i<=m; i++)
    {
        f>>x>>y>>c;
        graph[y].push_back(x);
        gr[x].push_back(y);
        graphC[x].push_back(c);
    }
    for(i=1; i<=n; i++)
    {
        if(!viz[i])
            DFS(i);
    }
    for(i=1; i<=indice; i++)
    {
        int u=sort_top[i];
        int lim=gr[u].size();
        for(int j=0; j<lim; j++)
        {
            int v=gr[u][j];
            int cost=graphC[u][j];
            if(dist[u]+cost<dist[v])
            {
                dist[v]=dist[u]+cost;
                tata[v]=u;
            }
        }
    }
    for(int k=1; k<=n; k++)
        g<<dist[k]<<" ";

    return 0;
}
