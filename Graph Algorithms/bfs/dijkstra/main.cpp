#include <iostream>
#include<fstream>
#include<vector>
using namespace std;
ifstream f("dijkstra.in");
ofstream g("dijkstra.out");
int sursa[100005],dist[100005];
vector<int> graph[100005];
void Dijkstra(int n)
{
    int i,minim=9999999;
    for(i=1; i<=n; i++)
        if(dist[i]<minim)
            minim=dist[i];
    index=i;
    int lim=graph[index].size();
    for(i=0; i<lim; i++)
    {
        int vecin=graph[index][i];
        int cost = graphC[index][i];
        if(dist[index]+cost <dist[vecin])
            dist[vecin]=dist[index]+cost;
    }
}
int main()
{
    int n,m,i,a,b,x;
    f>>n>>m;
    for(i=1; i<=n; i++)
    {
        sursa[i]=i;
        dist[i]=100000;
    }
    dist[1]=0;
    for(i=1; i<=m; i++)
    {
        f>>a>>b>>c;
        graph[a].push_back(b);
        // graph[b].push_back(a);
        graphC[a].push_back(c);

    }
    Dijkstra(n);
    for(i=1; i<=n; i++)
        g<<dist[i]<<" ";

    return 0;
}
