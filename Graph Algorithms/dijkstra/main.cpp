#include <iostream>
#include<fstream>
#include<vector>
using namespace std;
ifstream f("dijkstra.in");
ofstream g("dijkstra.out");
int viz[50005],dist[50005];
vector<int> graph[50005];
vector<int> graphC[50005];
void Dijkstra(int n)
{
    int i,minim,index;
    for(int t = 1; t <= n; t++)
    {
        minim = 1000000000;
        for(i=1; i<=n; i++)
            if(!viz[i] && dist[i]<minim)
            {
                minim=dist[i];
                index=i;
            }
        viz[index] = 1;
        int lim=graph[index].size();
        for(i=0; i<lim; i++)
        {
            int vecin=graph[index][i];
            int cost = graphC[index][i];
            if(dist[index]+cost <dist[vecin])
                dist[vecin]=dist[index]+cost;
        }
    }
}
int main()
{
    int n,m,i,a,b,c;
    f>>n>>m;
    for(i=1; i<=n; i++)
    {
        dist[i]=100000000;
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
    for(i=2; i<=n; i++)
        if(dist[i]==100000000)
            dist[i]=0;
    for(i=2; i<=n; i++)
        g<<dist[i]<<" ";
    return 0;
}
